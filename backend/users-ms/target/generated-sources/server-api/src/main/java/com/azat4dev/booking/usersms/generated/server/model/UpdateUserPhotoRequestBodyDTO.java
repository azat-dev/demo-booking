package com.azat4dev.booking.usersms.generated.server.model;

import java.net.URI;
import java.util.Objects;
import com.azat4dev.booking.usersms.generated.server.model.UploadedFileDataDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.UUID;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * UpdateUserPhotoRequestBodyDTO
 */
@lombok.Builder(toBuilder = true)
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown=true)

@JsonTypeName("UpdateUserPhotoRequestBody")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.6.0")
public class UpdateUserPhotoRequestBodyDTO {

  private UUID operationId;

  private UploadedFileDataDTO uploadedFile;

  public UpdateUserPhotoRequestBodyDTO() {
    super();
  }

  public UpdateUserPhotoRequestBodyDTO operationId(UUID operationId) {
    this.operationId = operationId;
    return this;
  }

  /**
   * Get operationId
   * @return operationId
  */
  @NotNull @Valid 
  @Schema(name = "operationId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("operationId")
  public UUID getOperationId() {
    return operationId;
  }

  public void setOperationId(UUID operationId) {
    this.operationId = operationId;
  }

  public UpdateUserPhotoRequestBodyDTO uploadedFile(UploadedFileDataDTO uploadedFile) {
    this.uploadedFile = uploadedFile;
    return this;
  }

  /**
   * Get uploadedFile
   * @return uploadedFile
  */
  @NotNull @Valid 
  @Schema(name = "uploadedFile", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("uploadedFile")
  public UploadedFileDataDTO getUploadedFile() {
    return uploadedFile;
  }

  public void setUploadedFile(UploadedFileDataDTO uploadedFile) {
    this.uploadedFile = uploadedFile;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateUserPhotoRequestBodyDTO updateUserPhotoRequestBody = (UpdateUserPhotoRequestBodyDTO) o;
    return Objects.equals(this.operationId, updateUserPhotoRequestBody.operationId) &&
        Objects.equals(this.uploadedFile, updateUserPhotoRequestBody.uploadedFile);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operationId, uploadedFile);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateUserPhotoRequestBodyDTO {\n");
    sb.append("    operationId: ").append(toIndentedString(operationId)).append("\n");
    sb.append("    uploadedFile: ").append(toIndentedString(uploadedFile)).append("\n");
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

