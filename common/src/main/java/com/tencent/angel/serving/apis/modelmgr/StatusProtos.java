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
// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: apis/modelmgr/status.proto

package com.tencent.angel.serving.apis.modelmgr;

public final class StatusProtos {
  private StatusProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface StatusProtoOrBuilder extends
      // @@protoc_insertion_point(interface_extends:angel.serving.StatusProto)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * Error code.
     * </pre>
     *
     * <code>.angel.serving.Code error_code = 1[json_name = "error_code"];</code>
     */
    int getErrorCodeValue();
    /**
     * <pre>
     * Error code.
     * </pre>
     *
     * <code>.angel.serving.Code error_code = 1[json_name = "error_code"];</code>
     */
    com.tencent.angel.serving.apis.modelmgr.ErrorCodesProtos.Code getErrorCode();

    /**
     * <pre>
     * Error message. Will only be set if an error was encountered.
     * </pre>
     *
     * <code>string error_message = 2[json_name = "error_message"];</code>
     */
    java.lang.String getErrorMessage();
    /**
     * <pre>
     * Error message. Will only be set if an error was encountered.
     * </pre>
     *
     * <code>string error_message = 2[json_name = "error_message"];</code>
     */
    com.google.protobuf.ByteString
        getErrorMessageBytes();
  }
  /**
   * <pre>
   * Status that corresponds to Status in
   * third_party/tensorflow/core/lib/core/status.h.
   * </pre>
   *
   * Protobuf type {@code angel.serving.StatusProto}
   */
  public  static final class StatusProto extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:angel.serving.StatusProto)
      StatusProtoOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use StatusProto.newBuilder() to construct.
    private StatusProto(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private StatusProto() {
      errorCode_ = 0;
      errorMessage_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private StatusProto(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownFieldProto3(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              int rawValue = input.readEnum();

              errorCode_ = rawValue;
              break;
            }
            case 18: {
              java.lang.String s = input.readStringRequireUtf8();

              errorMessage_ = s;
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.tencent.angel.serving.apis.modelmgr.StatusProtos.internal_static_angel_serving_StatusProto_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.tencent.angel.serving.apis.modelmgr.StatusProtos.internal_static_angel_serving_StatusProto_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto.class, com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto.Builder.class);
    }

    public static final int ERROR_CODE_FIELD_NUMBER = 1;
    private int errorCode_;
    /**
     * <pre>
     * Error code.
     * </pre>
     *
     * <code>.angel.serving.Code error_code = 1[json_name = "error_code"];</code>
     */
    public int getErrorCodeValue() {
      return errorCode_;
    }
    /**
     * <pre>
     * Error code.
     * </pre>
     *
     * <code>.angel.serving.Code error_code = 1[json_name = "error_code"];</code>
     */
    public com.tencent.angel.serving.apis.modelmgr.ErrorCodesProtos.Code getErrorCode() {
      com.tencent.angel.serving.apis.modelmgr.ErrorCodesProtos.Code result = com.tencent.angel.serving.apis.modelmgr.ErrorCodesProtos.Code.valueOf(errorCode_);
      return result == null ? com.tencent.angel.serving.apis.modelmgr.ErrorCodesProtos.Code.UNRECOGNIZED : result;
    }

    public static final int ERROR_MESSAGE_FIELD_NUMBER = 2;
    private volatile java.lang.Object errorMessage_;
    /**
     * <pre>
     * Error message. Will only be set if an error was encountered.
     * </pre>
     *
     * <code>string error_message = 2[json_name = "error_message"];</code>
     */
    public java.lang.String getErrorMessage() {
      java.lang.Object ref = errorMessage_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        errorMessage_ = s;
        return s;
      }
    }
    /**
     * <pre>
     * Error message. Will only be set if an error was encountered.
     * </pre>
     *
     * <code>string error_message = 2[json_name = "error_message"];</code>
     */
    public com.google.protobuf.ByteString
        getErrorMessageBytes() {
      java.lang.Object ref = errorMessage_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        errorMessage_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (errorCode_ != com.tencent.angel.serving.apis.modelmgr.ErrorCodesProtos.Code.OK.getNumber()) {
        output.writeEnum(1, errorCode_);
      }
      if (!getErrorMessageBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, errorMessage_);
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (errorCode_ != com.tencent.angel.serving.apis.modelmgr.ErrorCodesProtos.Code.OK.getNumber()) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(1, errorCode_);
      }
      if (!getErrorMessageBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, errorMessage_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto)) {
        return super.equals(obj);
      }
      com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto other = (com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto) obj;

      boolean result = true;
      result = result && errorCode_ == other.errorCode_;
      result = result && getErrorMessage()
          .equals(other.getErrorMessage());
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + ERROR_CODE_FIELD_NUMBER;
      hash = (53 * hash) + errorCode_;
      hash = (37 * hash) + ERROR_MESSAGE_FIELD_NUMBER;
      hash = (53 * hash) + getErrorMessage().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * <pre>
     * Status that corresponds to Status in
     * third_party/tensorflow/core/lib/core/status.h.
     * </pre>
     *
     * Protobuf type {@code angel.serving.StatusProto}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:angel.serving.StatusProto)
        com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.tencent.angel.serving.apis.modelmgr.StatusProtos.internal_static_angel_serving_StatusProto_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.tencent.angel.serving.apis.modelmgr.StatusProtos.internal_static_angel_serving_StatusProto_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto.class, com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto.Builder.class);
      }

      // Construct using com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        errorCode_ = 0;

        errorMessage_ = "";

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.tencent.angel.serving.apis.modelmgr.StatusProtos.internal_static_angel_serving_StatusProto_descriptor;
      }

      public com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto getDefaultInstanceForType() {
        return com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto.getDefaultInstance();
      }

      public com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto build() {
        com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto buildPartial() {
        com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto result = new com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto(this);
        result.errorCode_ = errorCode_;
        result.errorMessage_ = errorMessage_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto) {
          return mergeFrom((com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto other) {
        if (other == com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto.getDefaultInstance()) return this;
        if (other.errorCode_ != 0) {
          setErrorCodeValue(other.getErrorCodeValue());
        }
        if (!other.getErrorMessage().isEmpty()) {
          errorMessage_ = other.errorMessage_;
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int errorCode_ = 0;
      /**
       * <pre>
       * Error code.
       * </pre>
       *
       * <code>.angel.serving.Code error_code = 1[json_name = "error_code"];</code>
       */
      public int getErrorCodeValue() {
        return errorCode_;
      }
      /**
       * <pre>
       * Error code.
       * </pre>
       *
       * <code>.angel.serving.Code error_code = 1[json_name = "error_code"];</code>
       */
      public Builder setErrorCodeValue(int value) {
        errorCode_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Error code.
       * </pre>
       *
       * <code>.angel.serving.Code error_code = 1[json_name = "error_code"];</code>
       */
      public com.tencent.angel.serving.apis.modelmgr.ErrorCodesProtos.Code getErrorCode() {
        com.tencent.angel.serving.apis.modelmgr.ErrorCodesProtos.Code result = com.tencent.angel.serving.apis.modelmgr.ErrorCodesProtos.Code.valueOf(errorCode_);
        return result == null ? com.tencent.angel.serving.apis.modelmgr.ErrorCodesProtos.Code.UNRECOGNIZED : result;
      }
      /**
       * <pre>
       * Error code.
       * </pre>
       *
       * <code>.angel.serving.Code error_code = 1[json_name = "error_code"];</code>
       */
      public Builder setErrorCode(com.tencent.angel.serving.apis.modelmgr.ErrorCodesProtos.Code value) {
        if (value == null) {
          throw new NullPointerException();
        }
        
        errorCode_ = value.getNumber();
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Error code.
       * </pre>
       *
       * <code>.angel.serving.Code error_code = 1[json_name = "error_code"];</code>
       */
      public Builder clearErrorCode() {
        
        errorCode_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object errorMessage_ = "";
      /**
       * <pre>
       * Error message. Will only be set if an error was encountered.
       * </pre>
       *
       * <code>string error_message = 2[json_name = "error_message"];</code>
       */
      public java.lang.String getErrorMessage() {
        java.lang.Object ref = errorMessage_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          errorMessage_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       * Error message. Will only be set if an error was encountered.
       * </pre>
       *
       * <code>string error_message = 2[json_name = "error_message"];</code>
       */
      public com.google.protobuf.ByteString
          getErrorMessageBytes() {
        java.lang.Object ref = errorMessage_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          errorMessage_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       * Error message. Will only be set if an error was encountered.
       * </pre>
       *
       * <code>string error_message = 2[json_name = "error_message"];</code>
       */
      public Builder setErrorMessage(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        errorMessage_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Error message. Will only be set if an error was encountered.
       * </pre>
       *
       * <code>string error_message = 2[json_name = "error_message"];</code>
       */
      public Builder clearErrorMessage() {
        
        errorMessage_ = getDefaultInstance().getErrorMessage();
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Error message. Will only be set if an error was encountered.
       * </pre>
       *
       * <code>string error_message = 2[json_name = "error_message"];</code>
       */
      public Builder setErrorMessageBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        errorMessage_ = value;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFieldsProto3(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:angel.serving.StatusProto)
    }

    // @@protoc_insertion_point(class_scope:angel.serving.StatusProto)
    private static final com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto();
    }

    public static com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<StatusProto>
        PARSER = new com.google.protobuf.AbstractParser<StatusProto>() {
      public StatusProto parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new StatusProto(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<StatusProto> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<StatusProto> getParserForType() {
      return PARSER;
    }

    public com.tencent.angel.serving.apis.modelmgr.StatusProtos.StatusProto getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_angel_serving_StatusProto_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_angel_serving_StatusProto_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\032apis/modelmgr/status.proto\022\rangel.serv" +
      "ing\032\037apis/modelmgr/error_codes.proto\"h\n\013" +
      "StatusProto\0223\n\nerror_code\030\001 \001(\0162\023.angel." +
      "serving.CodeR\nerror_code\022$\n\rerror_messag" +
      "e\030\002 \001(\tR\rerror_messageB9\n\'com.tencent.an" +
      "gel.serving.apis.modelmgrB\014StatusProtosP" +
      "\000b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.tencent.angel.serving.apis.modelmgr.ErrorCodesProtos.getDescriptor(),
        }, assigner);
    internal_static_angel_serving_StatusProto_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_angel_serving_StatusProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_angel_serving_StatusProto_descriptor,
        new java.lang.String[] { "ErrorCode", "ErrorMessage", });
    com.tencent.angel.serving.apis.modelmgr.ErrorCodesProtos.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
