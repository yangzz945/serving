/*
 * Tencent is pleased to support the open source community by making Angel available.
 *
 * Copyright (C) 2017-2018 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/Apache-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 */
package com.tencent.angel.serving.service

import io.grpc.ServerBuilder
import java.io.{File, FileNotFoundException, FileReader, IOException}

import com.google.protobuf.{StringValue, TextFormat}
import org.slf4j.{Logger, LoggerFactory}
import com.tencent.angel.config.{Entry, Resource, ResourceAllocation}
import com.tencent.angel.config.ModelServerConfigProtos.{ModelConfig, ModelConfigList, ModelServerConfig}
import com.tencent.angel.config.PlatformConfigProtos.PlatformConfigMap
import com.tencent.angel.serving.core._
import com.tencent.angel.serving.serving.ModelServerConfig
import org.eclipse.jetty.servlet.ServletContextHandler
import com.sun.jersey.spi.container.servlet.ServletContainer
import com.tencent.angel.config.MonitoringConfigProtos.MonitoringConfig
import com.tencent.angel.servable.SessionBundleConfigProtos.{BatchingParameters, SessionBundleConfig}
import com.tencent.angel.serving.service.ModelServer.hadoopConf
import com.tencent.angel.serving.service.common.{ModelServiceImpl, PredictionServiceImpl}
import com.tencent.angel.serving.service.util.{Options, PlatformConfigUtil}
import io.grpc.services.ChannelzService
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.security.UserGroupInformation
import org.eclipse.jetty.servlet.ServletContextHandler.NO_SESSIONS


class ModelServer {

  import ModelServer.{readModelConfigFile, readPlatformConfigFile,
    defaultResourceAllocation, readBatchingParametersFile, readMonitoringConfigFile}

  private val LOG: Logger = LoggerFactory.getLogger(classOf[ModelServer])

  var serverCore: ServerCore = _
  var predictionServiceImpl: PredictionServiceImpl = _
  var modelServiceImpl: ModelServiceImpl = _
  var grpcServer: io.grpc.Server = _
  var httpServer: org.eclipse.jetty.server.Server = _

  def buildSingleModelConfig(modelName : String, platform: String, modelBasePath: String): ModelServerConfig ={
    LOG.info("Building single model file config: " +
      "model_name: " + modelName + " model_base_path: " + modelBasePath + " model_platform: " + platform)
    val singelModel: ModelConfig = ModelConfig.newBuilder().setName(modelName)
      .setBasePath(modelBasePath).setModelPlatform(platform).build()
    val modelServerConfig = ModelServerConfig.newBuilder().setModelConfigList(ModelConfigList.newBuilder().addConfig(singelModel)).build()
    modelServerConfig
  }

  def buildAndStart(serverOptions: Options): Unit = {
    val useSavedModel: Boolean = true

    if(serverOptions.grpc_port == 0) {
      LOG.info("serverOptions.grpc_port is not set.")
      return
    }

    if(serverOptions.model_base_path.isEmpty && serverOptions.model_config_file.isEmpty) {
      LOG.info("Both serverOptions.model_base_path and serverOptions.model_config_file are empty!")
      return
    }

    println(System.getenv("SERVING_HOME"))
    if (serverOptions.hadoop_home.nonEmpty){
      import org.apache.hadoop.fs.Path
      hadoopConf.addResource(new Path(serverOptions.hadoop_home + "/hdfs-site.xml"))
      hadoopConf.addResource(new Path(serverOptions.hadoop_home + "/core-site.xml"))
      println(hadoopConf.get("fs.defaultFS"))
      if(serverOptions.hadoop_job_ugi.nonEmpty) {
        hadoopConf.set("hadoop.job.ugi", serverOptions.hadoop_job_ugi)
      }
      UserGroupInformation.setConfiguration(hadoopConf)
      if (hadoopConf.get("hadoop.security.authentication").equals("kerberos")
        && hadoopConf.get("hadoop.security.authorization").equals("true")) {
        hadoopConf.addResource(new Path(System.getenv("SERVING_HOME") + "/conf/angel-site.xml"))
        if (serverOptions.principal == null || serverOptions.principal.equals("")) {
          val keytab = hadoopConf.get("angel.serving.kerberos.keytab")
          val principal = hadoopConf.get("angel.serving.kerberos.principal")
          val loginFromKeytab = principal != null
          if (loginFromKeytab) {
            if (!new File(keytab).exists) throw new FileNotFoundException("Keytab file: " + keytab + " does not exist")
            else {
              LOG.info("Kerberos credentials: principal = " + principal + ", keytab = " + keytab)
              UserGroupInformation.setConfiguration(hadoopConf)
              UserGroupInformation.loginUserFromKeytab(principal, keytab)
            }
          } else {
            LOG.info("principal is null for kerberos auth.")
          }
        } else if (!new File(serverOptions.keytab).exists) throw new FileNotFoundException("Keytab file: " + serverOptions.keytab + " does not exist")
        else {
          LOG.info("Kerberos credentials: principal = " + serverOptions.principal + ", keytab = " + serverOptions.keytab)
          UserGroupInformation.setConfiguration(hadoopConf)
          UserGroupInformation.loginUserFromKeytab(serverOptions.principal, serverOptions.keytab)
        }
      }
    }
    var modelServerConfig: ModelServerConfig = null
    val modelPlatformSet = scala.collection.mutable.Set[String]()
    if(serverOptions.model_config_file.isEmpty){
      modelServerConfig = buildSingleModelConfig(serverOptions.model_name, serverOptions.model_platform,
        serverOptions.model_base_path)
      modelPlatformSet.add(serverOptions.model_platform)
    } else {
      modelServerConfig = readModelConfigFile(serverOptions.model_config_file)
      (0 until modelServerConfig.getModelConfigList.getConfigCount).map { idx =>
        modelPlatformSet.add(modelServerConfig.getModelConfigList.getConfig(idx).getModelPlatform)
      }
    }

    var platformConfigMap: PlatformConfigMap = null
    if(serverOptions.platform_config_file.isEmpty) {
      val sessionBundleConfigBuilder = SessionBundleConfig.newBuilder()
      if(serverOptions.enable_batching) {
        val batchingParametersOrBuilder = BatchingParameters.newBuilder()
        if(serverOptions.batching_parameters_file.isEmpty) {
          batchingParametersOrBuilder.setThreadPoolName(StringValue.newBuilder().setValue("model_server_batch_threads"))
          sessionBundleConfigBuilder.setBatchingParameters(batchingParametersOrBuilder.build())
        } else {
          sessionBundleConfigBuilder.setBatchingParameters(readBatchingParametersFile(serverOptions.batching_parameters_file))
        }
      } else if(!serverOptions.batching_parameters_file.isEmpty) {
        LOG.info("serverOptions.batching_parameters_file is set without setting " +
          "serverOptions.enable_batching to true.")
      }
      val tags = serverOptions.saved_model_tags.split(",")
      for(tag <- tags) {
        sessionBundleConfigBuilder.addSavedModelTags(tag)
      }
      platformConfigMap = PlatformConfigUtil
        .createPlatformConfigMap(sessionBundleConfigBuilder.build(), useSavedModel, modelPlatformSet.toSet)
    } else {
      platformConfigMap = readPlatformConfigFile(serverOptions.platform_config_file)
    }

    val eventBus = new EventBus[ServableState]()
    val monitor = new ServableStateMonitor(eventBus, 1000)

    //val modelServerConfig: ModelServerConfig = readModelConfigFile(serverOptions.model_config_file)
    //val platformConfigMap: PlatformConfigMap = readPlatformConfigFile(serverOptions.platform_config_file)
    val totalResources: ResourceAllocation = defaultResourceAllocation()
    val servingContext: ServingContext = new ServingContext(eventBus, monitor, totalResources, platformConfigMap)

    servingContext.maxNumLoadRetries = serverOptions.max_num_load_retries
    servingContext.loadRetryIntervalMicros = serverOptions.load_retry_interval_micros
    servingContext.fileSystemPollWaitSeconds = serverOptions.file_system_poll_wait_seconds
    servingContext.flushFilesystemCaches = serverOptions.flush_filesystem_caches
    servingContext.targetPublishingMetric = serverOptions.target_publishing_metric
    servingContext.enableMetricSummary = serverOptions.enable_metric_summary
    servingContext.metricSummaryWaitSeconds = serverOptions.metric_summary_wait_seconds
    servingContext.countDistributionBucket = serverOptions.count_distribution_bucket

    serverCore = new ServerCore(servingContext)
    serverCore.reloadConfig(modelServerConfig)

    predictionServiceImpl = new PredictionServiceImpl(serverCore)
    modelServiceImpl = new ModelServiceImpl(serverCore)
    val serverBuilder: ServerBuilder[_ <: ServerBuilder[_]] = ServerBuilder.forPort(serverOptions.grpc_port)
    serverBuilder.addService(predictionServiceImpl)
    serverBuilder.addService(modelServiceImpl)
    serverBuilder.addService(ChannelzService.newInstance(100))
    grpcServer = serverBuilder.build()
    if(grpcServer == null) {
      LOG.info("Failed to BuildAndStart gRPC server.")
      return
    }
    try {
      grpcServerStart()
    } catch {
      case ex: Exception =>
        ex.printStackTrace()
        System.exit(-1)
    }
    val addr = "127.0.0.1"
    LOG.info("Running gRPC ModelServer at " + addr + ":" + serverOptions.grpc_port)

    if(serverOptions.http_port != 0) {
      if(serverOptions.http_port != serverOptions.grpc_port) {
        var monitoringConfig: MonitoringConfig = null
        if(!serverOptions.monitoring_config_file.isEmpty) {
          monitoringConfig = readMonitoringConfigFile(serverOptions.monitoring_config_file)
        }
        import org.eclipse.jetty.server.Server
        httpServer = new Server(serverOptions.http_port)
        val servletContextHandler = new ServletContextHandler(NO_SESSIONS)
        servletContextHandler.setContextPath("/")
        httpServer.setHandler(servletContextHandler)
        val servletHolder = servletContextHandler.addServlet(classOf[ServletContainer], "/*")
        servletHolder.setInitOrder(0)
        servletHolder.setInitParameter("com.sun.jersey.config.property.resourceConfigClass", "com.sun.jersey.api.core.PackagesResourceConfig")
        servletHolder.setInitParameter("com.sun.jersey.config.property.packages", "com.tencent.angel.serving.service.jersey")
        servletHolder.setInitParameter("jersey.config.server.provider.packages", "com.tencent.angel.serving.service.jersey.resources")
        // 自动将对象映射成json返回
        servletHolder.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true")
        if(httpServer != null) {
          try {
            httpServer.start()
            LOG.info("Exporting HTTP/REST API at: " + addr + ":" + serverOptions.http_port)
            httpServer.join()
          }
          catch {
            case ex: Exception =>
              ex.printStackTrace()
              LOG.info("Error occurred while starting Jetty")
              System.exit(-1)
          }
        } else {
          LOG.info("Failed to start HTTP Server at " + addr + ":" + serverOptions.http_port)
        }
      } else {
        LOG.info("server_options.http_port cannot be same as grpc_port. " +
          "Please use a different port for HTTP/REST API. " +
          "Skipped exporting HTTP/REST API.")
      }
    }

  }

  def waitForTermination(): Unit = {
    blockUntilShutdown()
  }

  /** Start serving requests. */
  @throws[IOException]
  private def grpcServerStart(): Unit = {
    grpcServer.start
    Runtime.getRuntime.addShutdownHook(new Thread() {
      override def run(): Unit = { // Use stderr here since the logger may has been reset by its JVM shutdown hook.
        LOG.info("*** shutting down gRPC server since JVM is shutting down")
        ModelServer.this.stop()
        LOG.info("*** server shut down")
      }
    })
  }

  /** Stop serving requests and shutdown resources. */
  private def stop(): Unit = {
    if (grpcServer != null) grpcServer.shutdown
  }

  /**
    * Await termination on the main thread since the grpc library uses daemon threads.
    */
  @throws[InterruptedException]
  private def blockUntilShutdown(): Unit = {
    if (grpcServer != null) grpcServer.awaitTermination()
    if(httpServer != null) httpServer.wait()
  }
}

object ModelServer {

  var server: ModelServer = _
  var hadoopConf: Configuration = new Configuration()

  @throws[IOException]
  def readPlatformConfigFile(platformConfigFile: String): PlatformConfigMap = {
    val fileReader = new FileReader(platformConfigFile)
    val platformConfigMapBuilder = PlatformConfigMap.newBuilder()
    TextFormat.merge(fileReader, platformConfigMapBuilder)
    platformConfigMapBuilder.build()
  }

  @throws[IOException]
  def readModelConfigFile(modelConfigFile: String): ModelServerConfig = {
    val fileReader = new FileReader(modelConfigFile)
    val modelServerConfigBuilder = ModelServerConfig.newBuilder()
    TextFormat.merge(fileReader, modelServerConfigBuilder)
    modelServerConfigBuilder.build()
  }

  @throws[IOException]
  def readBatchingParametersFile(batchingParametersFile: String): BatchingParameters = {
    val fileReader = new FileReader(batchingParametersFile)
    val batchingParametersBuilder = BatchingParameters.newBuilder()
    TextFormat.merge(fileReader, batchingParametersBuilder)
    batchingParametersBuilder.build()
  }

  @throws[IOException]
  def readMonitoringConfigFile(monitoringConfigFile: String): MonitoringConfig = {
    val fileReader = new FileReader(monitoringConfigFile)
    val monitoringConfigBuilder = MonitoringConfig.newBuilder()
    TextFormat.merge(fileReader, monitoringConfigBuilder)
    monitoringConfigBuilder.build()
  }

  def defaultResourceAllocation(): ResourceAllocation = {
    val run = Runtime.getRuntime
    val available = (run.totalMemory() * 0.8).toLong
    ResourceAllocation(List(Entry(Resource("CPU", 0, "Memmory"), available)))
  }

  def getServerCore: ServerCore = {
    server.serverCore
  }
}

