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
 * @interface GenerateUploadUserPhotoUrlRequestBody
 */
export interface GenerateUploadUserPhotoUrlRequestBody {
    /**
     * 
     * @type {string}
     * @memberof GenerateUploadUserPhotoUrlRequestBody
     */
    operationId?: string;
    /**
     * 
     * @type {string}
     * @memberof GenerateUploadUserPhotoUrlRequestBody
     */
    fileName?: string;
    /**
     * 
     * @type {string}
     * @memberof GenerateUploadUserPhotoUrlRequestBody
     */
    fileExtension?: string;
    /**
     * 
     * @type {number}
     * @memberof GenerateUploadUserPhotoUrlRequestBody
     */
    fileSize?: number;
}

/**
 * Check if a given object implements the GenerateUploadUserPhotoUrlRequestBody interface.
 */
export function instanceOfGenerateUploadUserPhotoUrlRequestBody(value: object): boolean {
    return true;
}

export function GenerateUploadUserPhotoUrlRequestBodyFromJSON(json: any): GenerateUploadUserPhotoUrlRequestBody {
    return GenerateUploadUserPhotoUrlRequestBodyFromJSONTyped(json, false);
}

export function GenerateUploadUserPhotoUrlRequestBodyFromJSONTyped(json: any, ignoreDiscriminator: boolean): GenerateUploadUserPhotoUrlRequestBody {
    if (json == null) {
        return json;
    }
    return {
        
        'operationId': json['operationId'] == null ? undefined : json['operationId'],
        'fileName': json['fileName'] == null ? undefined : json['fileName'],
        'fileExtension': json['fileExtension'] == null ? undefined : json['fileExtension'],
        'fileSize': json['fileSize'] == null ? undefined : json['fileSize'],
    };
}

export function GenerateUploadUserPhotoUrlRequestBodyToJSON(value?: GenerateUploadUserPhotoUrlRequestBody | null): any {
    if (value == null) {
        return value;
    }
    return {
        
        'operationId': value['operationId'],
        'fileName': value['fileName'],
        'fileExtension': value['fileExtension'],
        'fileSize': value['fileSize'],
    };
}

