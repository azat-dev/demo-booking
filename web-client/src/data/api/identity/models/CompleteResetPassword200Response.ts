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

import { mapValues } from '../runtime';
/**
 * 
 * @export
 * @interface CompleteResetPassword200Response
 */
export interface CompleteResetPassword200Response {
    /**
     * 
     * @type {string}
     * @memberof CompleteResetPassword200Response
     */
    message?: string;
}

/**
 * Check if a given object implements the CompleteResetPassword200Response interface.
 */
export function instanceOfCompleteResetPassword200Response(value: object): boolean {
    return true;
}

export function CompleteResetPassword200ResponseFromJSON(json: any): CompleteResetPassword200Response {
    return CompleteResetPassword200ResponseFromJSONTyped(json, false);
}

export function CompleteResetPassword200ResponseFromJSONTyped(json: any, ignoreDiscriminator: boolean): CompleteResetPassword200Response {
    if (json == null) {
        return json;
    }
    return {
        
        'message': json['message'] == null ? undefined : json['message'],
    };
}

export function CompleteResetPassword200ResponseToJSON(value?: CompleteResetPassword200Response | null): any {
    if (value == null) {
        return value;
    }
    return {
        
        'message': value['message'],
    };
}

