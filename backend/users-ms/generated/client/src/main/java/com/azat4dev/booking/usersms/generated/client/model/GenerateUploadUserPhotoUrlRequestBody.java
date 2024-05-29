/*
 * Users  API
 * Describes the API of Users Endpoint
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.azat4dev.booking.usersms.generated.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * GenerateUploadUserPhotoUrlRequestBody
 */
@JsonPropertyOrder({
  GenerateUploadUserPhotoUrlRequestBody.JSON_PROPERTY_IDEMPOTENT_OPERATION_ID,
  GenerateUploadUserPhotoUrlRequestBody.JSON_PROPERTY_FILE_NAME,
  GenerateUploadUserPhotoUrlRequestBody.JSON_PROPERTY_FILE_EXTENSION,
  GenerateUploadUserPhotoUrlRequestBody.JSON_PROPERTY_FILE_SIZE
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-05-29T10:57:01.921409+03:00[Europe/Moscow]", comments = "Generator version: 7.6.0")
public class GenerateUploadUserPhotoUrlRequestBody {
  public static final String JSON_PROPERTY_IDEMPOTENT_OPERATION_ID = "idempotentOperationId";
  private String idempotentOperationId;

  public static final String JSON_PROPERTY_FILE_NAME = "fileName";
  private String fileName;

  public static final String JSON_PROPERTY_FILE_EXTENSION = "fileExtension";
  private String fileExtension;

  public static final String JSON_PROPERTY_FILE_SIZE = "fileSize";
  private Integer fileSize;

  public GenerateUploadUserPhotoUrlRequestBody() {
  }

  public GenerateUploadUserPhotoUrlRequestBody idempotentOperationId(String idempotentOperationId) {
    
    this.idempotentOperationId = idempotentOperationId;
    return this;
  }

   /**
   * Get idempotentOperationId
   * @return idempotentOperationId
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_IDEMPOTENT_OPERATION_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getIdempotentOperationId() {
    return idempotentOperationId;
  }


  @JsonProperty(JSON_PROPERTY_IDEMPOTENT_OPERATION_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setIdempotentOperationId(String idempotentOperationId) {
    this.idempotentOperationId = idempotentOperationId;
  }


  public GenerateUploadUserPhotoUrlRequestBody fileName(String fileName) {
    
    this.fileName = fileName;
    return this;
  }

   /**
   * Get fileName
   * @return fileName
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_FILE_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getFileName() {
    return fileName;
  }


  @JsonProperty(JSON_PROPERTY_FILE_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }


  public GenerateUploadUserPhotoUrlRequestBody fileExtension(String fileExtension) {
    
    this.fileExtension = fileExtension;
    return this;
  }

   /**
   * Get fileExtension
   * @return fileExtension
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_FILE_EXTENSION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getFileExtension() {
    return fileExtension;
  }


  @JsonProperty(JSON_PROPERTY_FILE_EXTENSION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setFileExtension(String fileExtension) {
    this.fileExtension = fileExtension;
  }


  public GenerateUploadUserPhotoUrlRequestBody fileSize(Integer fileSize) {
    
    this.fileSize = fileSize;
    return this;
  }

   /**
   * Get fileSize
   * @return fileSize
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_FILE_SIZE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getFileSize() {
    return fileSize;
  }


  @JsonProperty(JSON_PROPERTY_FILE_SIZE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setFileSize(Integer fileSize) {
    this.fileSize = fileSize;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GenerateUploadUserPhotoUrlRequestBody generateUploadUserPhotoUrlRequestBody = (GenerateUploadUserPhotoUrlRequestBody) o;
    return Objects.equals(this.idempotentOperationId, generateUploadUserPhotoUrlRequestBody.idempotentOperationId) &&
        Objects.equals(this.fileName, generateUploadUserPhotoUrlRequestBody.fileName) &&
        Objects.equals(this.fileExtension, generateUploadUserPhotoUrlRequestBody.fileExtension) &&
        Objects.equals(this.fileSize, generateUploadUserPhotoUrlRequestBody.fileSize);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idempotentOperationId, fileName, fileExtension, fileSize);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GenerateUploadUserPhotoUrlRequestBody {\n");
    sb.append("    idempotentOperationId: ").append(toIndentedString(idempotentOperationId)).append("\n");
    sb.append("    fileName: ").append(toIndentedString(fileName)).append("\n");
    sb.append("    fileExtension: ").append(toIndentedString(fileExtension)).append("\n");
    sb.append("    fileSize: ").append(toIndentedString(fileSize)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

