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

import { mapValues } from '../runtime';
import type { SignUpResponseTokens } from './SignUpResponseTokens';
import {
    SignUpResponseTokensFromJSON,
    SignUpResponseTokensFromJSONTyped,
    SignUpResponseTokensToJSON,
} from './SignUpResponseTokens';

/**
 * 
 * @export
 * @interface SignUpResponse
 */
export interface SignUpResponse {
    /**
     * 
     * @type {string}
     * @memberof SignUpResponse
     */
    userId: string;
    /**
     * 
     * @type {SignUpResponseTokens}
     * @memberof SignUpResponse
     */
    tokens: SignUpResponseTokens;
}

/**
 * Check if a given object implements the SignUpResponse interface.
 */
export function instanceOfSignUpResponse(value: object): boolean {
    if (!('userId' in value)) return false;
    if (!('tokens' in value)) return false;
    return true;
}

export function SignUpResponseFromJSON(json: any): SignUpResponse {
    return SignUpResponseFromJSONTyped(json, false);
}

export function SignUpResponseFromJSONTyped(json: any, ignoreDiscriminator: boolean): SignUpResponse {
    if (json == null) {
        return json;
    }
    return {
        
        'userId': json['userId'],
        'tokens': SignUpResponseTokensFromJSON(json['tokens']),
    };
}

export function SignUpResponseToJSON(value?: SignUpResponse | null): any {
    if (value == null) {
        return value;
    }
    return {
        
        'userId': value['userId'],
        'tokens': SignUpResponseTokensToJSON(value['tokens']),
    };
}

