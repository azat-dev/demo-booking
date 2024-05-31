/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.azat4dev.booking.listingsms.generated.server.api;

import com.azat4dev.booking.listingsms.generated.server.model.GetListingPrivateDetailsResponse;
import com.azat4dev.booking.listingsms.generated.server.model.ListingPrivateDetailsDTO;
import java.util.UUID;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-05-31T01:10:19.057492+03:00[Europe/Moscow]")
@Validated
@Tag(name = "queriesPrivate", description = "the queriesPrivate API")
public interface QueriesPrivateApi {

    default QueriesPrivateApiDelegate getDelegate() {
        return new QueriesPrivateApiDelegate() {};
    }

    /**
     * GET /api/private/users/currrent/listings/{listingId} : Get private details for listing
     *
     * @param listingId Listing ID (required)
     * @return Private details of listing (status code 200)
     *         or Not valid Token (status code 401)
     *         or Not found (status code 404)
     */
    @Operation(
        operationId = "getListingPrivateDetails",
        summary = "Get private details for listing",
        tags = { "queriesPrivate" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Private details of listing", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = GetListingPrivateDetailsResponse.class))
            }),
            @ApiResponse(responseCode = "401", description = "Not valid Token"),
            @ApiResponse(responseCode = "404", description = "Not found")
        },
        security = {
            @SecurityRequirement(name = "BearerAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/private/users/currrent/listings/{listingId}",
        produces = { "application/json" }
    )
    default ResponseEntity<GetListingPrivateDetailsResponse> getListingPrivateDetails(
        @Parameter(name = "listingId", description = "Listing ID", required = true, in = ParameterIn.PATH) @PathVariable("listingId") UUID listingId
    ) {
        return getDelegate().getListingPrivateDetails(listingId);
    }


    /**
     * GET /api/private/users/currrent/listings : List own listings
     *
     * @return Private details of listing (status code 200)
     *         or Forbidden (status code 403)
     */
    @Operation(
        operationId = "getOwnListings",
        summary = "List own listings",
        tags = { "queriesPrivate" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Private details of listing", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ListingPrivateDetailsDTO.class)))
            }),
            @ApiResponse(responseCode = "403", description = "Forbidden")
        },
        security = {
            @SecurityRequirement(name = "BearerAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/private/users/currrent/listings",
        produces = { "application/json" }
    )
    default ResponseEntity<List<ListingPrivateDetailsDTO>> getOwnListings(
        
    ) {
        return getDelegate().getOwnListings();
    }

}
