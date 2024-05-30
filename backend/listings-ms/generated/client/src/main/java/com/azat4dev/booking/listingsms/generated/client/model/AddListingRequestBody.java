/*
 * Listings API
 * Describes the API of Listings service
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.azat4dev.booking.listingsms.generated.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * AddListingRequestBody
 */
@JsonPropertyOrder({
  AddListingRequestBody.JSON_PROPERTY_OPERATION_ID,
  AddListingRequestBody.JSON_PROPERTY_TITLE
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-05-30T22:29:40.238241+03:00[Europe/Moscow]")
public class AddListingRequestBody {
  public static final String JSON_PROPERTY_OPERATION_ID = "operationId";
  private UUID operationId;

  public static final String JSON_PROPERTY_TITLE = "title";
  private String title;

  public AddListingRequestBody() {
  }

  public AddListingRequestBody operationId(UUID operationId) {
    
    this.operationId = operationId;
    return this;
  }

   /**
   * Get operationId
   * @return operationId
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_OPERATION_ID)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public UUID getOperationId() {
    return operationId;
  }


  @JsonProperty(JSON_PROPERTY_OPERATION_ID)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setOperationId(UUID operationId) {
    this.operationId = operationId;
  }


  public AddListingRequestBody title(String title) {
    
    this.title = title;
    return this;
  }

   /**
   * Get title
   * @return title
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_TITLE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getTitle() {
    return title;
  }


  @JsonProperty(JSON_PROPERTY_TITLE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddListingRequestBody addListingRequestBody = (AddListingRequestBody) o;
    return Objects.equals(this.operationId, addListingRequestBody.operationId) &&
        Objects.equals(this.title, addListingRequestBody.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operationId, title);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddListingRequestBody {\n");
    sb.append("    operationId: ").append(toIndentedString(operationId)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
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

