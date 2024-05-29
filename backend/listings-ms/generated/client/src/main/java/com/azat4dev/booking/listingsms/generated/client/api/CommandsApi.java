/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.azat4dev.booking.listingsms.generated.client.api;

import com.azat4dev.booking.listingsms.generated.client.model.AddListingRequestBody;
import com.azat4dev.booking.listingsms.generated.client.model.AddListingResponse;
import com.azat4dev.booking.listingsms.generated.client.model.GetPhotoUploadUrlForListingResponse;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-05-28T23:07:12.115195+03:00[Europe/Moscow]")
@Validated
@Tag(name = "commands", description = "the commands API")
public interface CommandsApi {

    /**
     * POST /api/private/listings : Add a new listing
     *
     * @param addListingRequestBody  (required)
     * @return Listing added successfully (status code 200)
     *         or Not valid Token (status code 401)
     *         or Not found (status code 404)
     */
    @Operation(
        operationId = "addListing",
        summary = "Add a new listing",
        tags = { "commands" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Listing added successfully", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = AddListingResponse.class))
            }),
            @ApiResponse(responseCode = "401", description = "Not valid Token"),
            @ApiResponse(responseCode = "404", description = "Not found")
        },
        security = {
            @SecurityRequirement(name = "BearerAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api/private/listings",
        produces = "application/json",
        consumes = "application/json"
    )
    ResponseEntity<AddListingResponse> addListing(
        @Parameter(name = "AddListingRequestBody", description = "", required = true) @Valid @RequestBody AddListingRequestBody addListingRequestBody
    );


    /**
     * POST /api/private/listings/{listingId}/get-photo-upload-url : Get upload URL for listing photo
     *
     * @param listingId Listing ID (required)
     * @param addListingRequestBody  (required)
     * @return Listing added successfully (status code 200)
     *         or Not valid Token (status code 401)
     *         or Not found (status code 404)
     */
    @Operation(
        operationId = "getPhotoUploadUrl",
        summary = "Get upload URL for listing photo",
        tags = { "commands" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Listing added successfully", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = GetPhotoUploadUrlForListingResponse.class))
            }),
            @ApiResponse(responseCode = "401", description = "Not valid Token"),
            @ApiResponse(responseCode = "404", description = "Not found")
        },
        security = {
            @SecurityRequirement(name = "BearerAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api/private/listings/{listingId}/get-photo-upload-url",
        produces = "application/json",
        consumes = "application/json"
    )
    ResponseEntity<GetPhotoUploadUrlForListingResponse> getPhotoUploadUrl(
        @Parameter(name = "listingId", description = "Listing ID", required = true, in = ParameterIn.PATH) @PathVariable("listingId") UUID listingId,
        @Parameter(name = "AddListingRequestBody", description = "", required = true) @Valid @RequestBody AddListingRequestBody addListingRequestBody
    );

}