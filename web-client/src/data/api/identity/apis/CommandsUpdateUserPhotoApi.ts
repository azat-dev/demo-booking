/* tslint:disable */
/* eslint-disable */
/**
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


import * as runtime from '../runtime';
import type {
  GenerateUploadUserPhotoUrlRequestBody,
  GenerateUploadUserPhotoUrlResponseBody,
  UpdateUserPhoto200Response,
  UpdateUserPhotoRequestBody,
} from '../models/index';
import {
    GenerateUploadUserPhotoUrlRequestBodyFromJSON,
    GenerateUploadUserPhotoUrlRequestBodyToJSON,
    GenerateUploadUserPhotoUrlResponseBodyFromJSON,
    GenerateUploadUserPhotoUrlResponseBodyToJSON,
    UpdateUserPhoto200ResponseFromJSON,
    UpdateUserPhoto200ResponseToJSON,
    UpdateUserPhotoRequestBodyFromJSON,
    UpdateUserPhotoRequestBodyToJSON,
} from '../models/index';

export interface GenerateUploadUserPhotoUrlRequest {
    generateUploadUserPhotoUrlRequestBody: GenerateUploadUserPhotoUrlRequestBody;
}

export interface UpdateUserPhotoRequest {
    updateUserPhotoRequestBody: UpdateUserPhotoRequestBody;
}

/**
 * 
 */
export class CommandsUpdateUserPhotoApi extends runtime.BaseAPI {

    /**
     * Generate upload form for user photo
     */
    async generateUploadUserPhotoUrlRaw(requestParameters: GenerateUploadUserPhotoUrlRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<GenerateUploadUserPhotoUrlResponseBody>> {
        if (requestParameters['generateUploadUserPhotoUrlRequestBody'] == null) {
            throw new runtime.RequiredError(
                'generateUploadUserPhotoUrlRequestBody',
                'Required parameter "generateUploadUserPhotoUrlRequestBody" was null or undefined when calling generateUploadUserPhotoUrl().'
            );
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        headerParameters['Content-Type'] = 'application/json';

        if (this.configuration && this.configuration.accessToken) {
            const token = this.configuration.accessToken;
            const tokenString = await token("BearerAuth", []);

            if (tokenString) {
                headerParameters["Authorization"] = `Bearer ${tokenString}`;
            }
        }
        const response = await this.request({
            path: `/api/private/identity/users/current/photo/get-upload-url`,
            method: 'POST',
            headers: headerParameters,
            query: queryParameters,
            body: GenerateUploadUserPhotoUrlRequestBodyToJSON(requestParameters['generateUploadUserPhotoUrlRequestBody']),
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => GenerateUploadUserPhotoUrlResponseBodyFromJSON(jsonValue));
    }

    /**
     * Generate upload form for user photo
     */
    async generateUploadUserPhotoUrl(requestParameters: GenerateUploadUserPhotoUrlRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<GenerateUploadUserPhotoUrlResponseBody> {
        const response = await this.generateUploadUserPhotoUrlRaw(requestParameters, initOverrides);
        return await response.value();
    }

    /**
     * Attach uploaded photo to the user
     */
    async updateUserPhotoRaw(requestParameters: UpdateUserPhotoRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<UpdateUserPhoto200Response>> {
        if (requestParameters['updateUserPhotoRequestBody'] == null) {
            throw new runtime.RequiredError(
                'updateUserPhotoRequestBody',
                'Required parameter "updateUserPhotoRequestBody" was null or undefined when calling updateUserPhoto().'
            );
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        headerParameters['Content-Type'] = 'application/json';

        if (this.configuration && this.configuration.accessToken) {
            const token = this.configuration.accessToken;
            const tokenString = await token("BearerAuth", []);

            if (tokenString) {
                headerParameters["Authorization"] = `Bearer ${tokenString}`;
            }
        }
        const response = await this.request({
            path: `/api/private/identity/users/current/photo/update`,
            method: 'POST',
            headers: headerParameters,
            query: queryParameters,
            body: UpdateUserPhotoRequestBodyToJSON(requestParameters['updateUserPhotoRequestBody']),
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => UpdateUserPhoto200ResponseFromJSON(jsonValue));
    }

    /**
     * Attach uploaded photo to the user
     */
    async updateUserPhoto(requestParameters: UpdateUserPhotoRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<UpdateUserPhoto200Response> {
        const response = await this.updateUserPhotoRaw(requestParameters, initOverrides);
        return await response.value();
    }

}
