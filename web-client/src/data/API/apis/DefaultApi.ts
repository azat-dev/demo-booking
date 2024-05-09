/* tslint:disable */
/* eslint-disable */
/**
 * Demo Booking API
 * Describes the API of Daily Tasks
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
  ApiPublicAuthSignUpPost400Response,
  ApiPublicAuthTokenVerifyPostRequest,
  AuthenticateByEmailRequest,
  GetTokenResponse,
  PersonalUserInfo,
  SignUpByEmailRequest,
  SignUpByEmailResponse,
  UserWithSameEmailAlreadyExistsError,
} from '../models/index';
import {
    ApiPublicAuthSignUpPost400ResponseFromJSON,
    ApiPublicAuthSignUpPost400ResponseToJSON,
    ApiPublicAuthTokenVerifyPostRequestFromJSON,
    ApiPublicAuthTokenVerifyPostRequestToJSON,
    AuthenticateByEmailRequestFromJSON,
    AuthenticateByEmailRequestToJSON,
    GetTokenResponseFromJSON,
    GetTokenResponseToJSON,
    PersonalUserInfoFromJSON,
    PersonalUserInfoToJSON,
    SignUpByEmailRequestFromJSON,
    SignUpByEmailRequestToJSON,
    SignUpByEmailResponseFromJSON,
    SignUpByEmailResponseToJSON,
    UserWithSameEmailAlreadyExistsErrorFromJSON,
    UserWithSameEmailAlreadyExistsErrorToJSON,
} from '../models/index';

export interface ApiPublicAuthSignUpPostRequest {
    signUpByEmailRequest: SignUpByEmailRequest;
}

export interface ApiPublicAuthTokenPostRequest {
    authenticateByEmailRequest: AuthenticateByEmailRequest;
}

export interface ApiPublicAuthTokenVerifyPostOperationRequest {
    apiPublicAuthTokenVerifyPostRequest: ApiPublicAuthTokenVerifyPostRequest;
}

/**
 * 
 */
export class DefaultApi extends runtime.BaseAPI {

    /**
     * Sign up a new user
     */
    async apiPublicAuthSignUpPostRaw(requestParameters: ApiPublicAuthSignUpPostRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<SignUpByEmailResponse>> {
        if (requestParameters['signUpByEmailRequest'] == null) {
            throw new runtime.RequiredError(
                'signUpByEmailRequest',
                'Required parameter "signUpByEmailRequest" was null or undefined when calling apiPublicAuthSignUpPost().'
            );
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        headerParameters['Content-Type'] = 'application/json';

        const response = await this.request({
            path: `/api/public/auth/sign-up`,
            method: 'POST',
            headers: headerParameters,
            query: queryParameters,
            body: SignUpByEmailRequestToJSON(requestParameters['signUpByEmailRequest']),
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => SignUpByEmailResponseFromJSON(jsonValue));
    }

    /**
     * Sign up a new user
     */
    async apiPublicAuthSignUpPost(requestParameters: ApiPublicAuthSignUpPostRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<SignUpByEmailResponse> {
        const response = await this.apiPublicAuthSignUpPostRaw(requestParameters, initOverrides);
        return await response.value();
    }

    /**
     * Get a new pair of tokens (access, refresh)
     */
    async apiPublicAuthTokenPostRaw(requestParameters: ApiPublicAuthTokenPostRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<GetTokenResponse>> {
        if (requestParameters['authenticateByEmailRequest'] == null) {
            throw new runtime.RequiredError(
                'authenticateByEmailRequest',
                'Required parameter "authenticateByEmailRequest" was null or undefined when calling apiPublicAuthTokenPost().'
            );
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        headerParameters['Content-Type'] = 'application/json';

        const response = await this.request({
            path: `/api/public/auth/token`,
            method: 'POST',
            headers: headerParameters,
            query: queryParameters,
            body: AuthenticateByEmailRequestToJSON(requestParameters['authenticateByEmailRequest']),
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => GetTokenResponseFromJSON(jsonValue));
    }

    /**
     * Get a new pair of tokens (access, refresh)
     */
    async apiPublicAuthTokenPost(requestParameters: ApiPublicAuthTokenPostRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<GetTokenResponse> {
        const response = await this.apiPublicAuthTokenPostRaw(requestParameters, initOverrides);
        return await response.value();
    }

    /**
     * Check if a token is valid
     */
    async apiPublicAuthTokenVerifyPostRaw(requestParameters: ApiPublicAuthTokenVerifyPostOperationRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<void>> {
        if (requestParameters['apiPublicAuthTokenVerifyPostRequest'] == null) {
            throw new runtime.RequiredError(
                'apiPublicAuthTokenVerifyPostRequest',
                'Required parameter "apiPublicAuthTokenVerifyPostRequest" was null or undefined when calling apiPublicAuthTokenVerifyPost().'
            );
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        headerParameters['Content-Type'] = 'application/json';

        const response = await this.request({
            path: `/api/public/auth/token/verify`,
            method: 'POST',
            headers: headerParameters,
            query: queryParameters,
            body: ApiPublicAuthTokenVerifyPostRequestToJSON(requestParameters['apiPublicAuthTokenVerifyPostRequest']),
        }, initOverrides);

        return new runtime.VoidApiResponse(response);
    }

    /**
     * Check if a token is valid
     */
    async apiPublicAuthTokenVerifyPost(requestParameters: ApiPublicAuthTokenVerifyPostOperationRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<void> {
        await this.apiPublicAuthTokenVerifyPostRaw(requestParameters, initOverrides);
    }

    /**
     * Gets current user info
     */
    async apiWithAuthUsersCurrentGetRaw(initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<PersonalUserInfo>> {
        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        if (this.configuration && this.configuration.accessToken) {
            const token = this.configuration.accessToken;
            const tokenString = await token("BearerAuth", []);

            if (tokenString) {
                headerParameters["Authorization"] = `Bearer ${tokenString}`;
            }
        }
        const response = await this.request({
            path: `/api/with-auth/users/current`,
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => PersonalUserInfoFromJSON(jsonValue));
    }

    /**
     * Gets current user info
     */
    async apiWithAuthUsersCurrentGet(initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<PersonalUserInfo> {
        const response = await this.apiWithAuthUsersCurrentGetRaw(initOverrides);
        return await response.value();
    }

}
