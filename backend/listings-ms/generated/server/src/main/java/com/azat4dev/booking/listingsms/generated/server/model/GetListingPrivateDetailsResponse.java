package com.azat4dev.booking.listingsms.generated.server.model;

import java.net.URI;
import java.util.Objects;
import com.azat4dev.booking.listingsms.generated.server.model.ListingPrivateDetailsDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * GetListingPrivateDetailsResponse
 */
@lombok.Builder(toBuilder = true)
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown=true)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-05-30T22:29:40.500087+03:00[Europe/Moscow]")
public class GetListingPrivateDetailsResponse {

  private ListingPrivateDetailsDTO listing;

  public GetListingPrivateDetailsResponse listing(ListingPrivateDetailsDTO listing) {
    this.listing = listing;
    return this;
  }

  /**
   * Get listing
   * @return listing
  */
  @NotNull @Valid 
  @Schema(name = "listing", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("listing")
  public ListingPrivateDetailsDTO getListing() {
    return listing;
  }

  public void setListing(ListingPrivateDetailsDTO listing) {
    this.listing = listing;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetListingPrivateDetailsResponse getListingPrivateDetailsResponse = (GetListingPrivateDetailsResponse) o;
    return Objects.equals(this.listing, getListingPrivateDetailsResponse.listing);
  }

  @Override
  public int hashCode() {
    return Objects.hash(listing);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetListingPrivateDetailsResponse {\n");
    sb.append("    listing: ").append(toIndentedString(listing)).append("\n");
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

