package com.azat4dev.booking.listingsms.generated.client.api;

import com.azat4dev.booking.listingsms.generated.client.base.ApiClient;
import com.azat4dev.booking.listingsms.generated.client.base.EncodingUtils;
import com.azat4dev.booking.listingsms.generated.client.model.ApiResponse;

import com.azat4dev.booking.listingsms.generated.client.model.GetListingPrivateDetailsResponse;
import com.azat4dev.booking.listingsms.generated.client.model.ListingPrivateDetailsDTO;
import java.util.UUID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import feign.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-05-30T22:29:40.238241+03:00[Europe/Moscow]")
public interface QueriesPrivateApi extends ApiClient.Api {


  /**
   * Get private details for listing
   * 
   * @param listingId Listing ID (required)
   * @return GetListingPrivateDetailsResponse
   */
  @RequestLine("GET /api/private/users/currrent/listings/{listingId}")
  @Headers({
    "Accept: application/json",
  })
  GetListingPrivateDetailsResponse getListingPrivateDetails(@Param("listingId") UUID listingId);

  /**
   * Get private details for listing
   * Similar to <code>getListingPrivateDetails</code> but it also returns the http response headers .
   * 
   * @param listingId Listing ID (required)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("GET /api/private/users/currrent/listings/{listingId}")
  @Headers({
    "Accept: application/json",
  })
  ApiResponse<GetListingPrivateDetailsResponse> getListingPrivateDetailsWithHttpInfo(@Param("listingId") UUID listingId);



  /**
   * List own listings
   * 
   * @return List&lt;ListingPrivateDetailsDTO&gt;
   */
  @RequestLine("GET /api/private/users/currrent/listings")
  @Headers({
    "Accept: application/json",
  })
  List<ListingPrivateDetailsDTO> getOwnListings();

  /**
   * List own listings
   * Similar to <code>getOwnListings</code> but it also returns the http response headers .
   * 
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("GET /api/private/users/currrent/listings")
  @Headers({
    "Accept: application/json",
  })
  ApiResponse<List<ListingPrivateDetailsDTO>> getOwnListingsWithHttpInfo();


}
