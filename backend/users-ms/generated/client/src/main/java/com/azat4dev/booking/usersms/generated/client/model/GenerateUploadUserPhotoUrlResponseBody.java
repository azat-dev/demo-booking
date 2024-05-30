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
import com.azat4dev.booking.usersms.generated.client.model.UploadedFileDataDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * GenerateUploadUserPhotoUrlResponseBody
 */
@JsonPropertyOrder({
  GenerateUploadUserPhotoUrlResponseBody.JSON_PROPERTY_OBJECT_PATH,
  GenerateUploadUserPhotoUrlResponseBody.JSON_PROPERTY_FORM_DATA
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-05-30T09:25:21.113148+03:00[Europe/Moscow]", comments = "Generator version: 7.6.0")
public class GenerateUploadUserPhotoUrlResponseBody {
  public static final String JSON_PROPERTY_OBJECT_PATH = "objectPath";
  private UploadedFileDataDTO objectPath;

  public static final String JSON_PROPERTY_FORM_DATA = "formData";
  private Map<String, String> formData = new HashMap<>();

  public GenerateUploadUserPhotoUrlResponseBody() {
  }

  public GenerateUploadUserPhotoUrlResponseBody objectPath(UploadedFileDataDTO objectPath) {
    
    this.objectPath = objectPath;
    return this;
  }

   /**
   * Get objectPath
   * @return objectPath
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_OBJECT_PATH)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public UploadedFileDataDTO getObjectPath() {
    return objectPath;
  }


  @JsonProperty(JSON_PROPERTY_OBJECT_PATH)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setObjectPath(UploadedFileDataDTO objectPath) {
    this.objectPath = objectPath;
  }


  public GenerateUploadUserPhotoUrlResponseBody formData(Map<String, String> formData) {
    
    this.formData = formData;
    return this;
  }

  public GenerateUploadUserPhotoUrlResponseBody putFormDataItem(String key, String formDataItem) {
    this.formData.put(key, formDataItem);
    return this;
  }

   /**
   * Get formData
   * @return formData
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_FORM_DATA)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Map<String, String> getFormData() {
    return formData;
  }


  @JsonProperty(JSON_PROPERTY_FORM_DATA)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setFormData(Map<String, String> formData) {
    this.formData = formData;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GenerateUploadUserPhotoUrlResponseBody generateUploadUserPhotoUrlResponseBody = (GenerateUploadUserPhotoUrlResponseBody) o;
    return Objects.equals(this.objectPath, generateUploadUserPhotoUrlResponseBody.objectPath) &&
        Objects.equals(this.formData, generateUploadUserPhotoUrlResponseBody.formData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(objectPath, formData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GenerateUploadUserPhotoUrlResponseBody {\n");
    sb.append("    objectPath: ").append(toIndentedString(objectPath)).append("\n");
    sb.append("    formData: ").append(toIndentedString(formData)).append("\n");
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

