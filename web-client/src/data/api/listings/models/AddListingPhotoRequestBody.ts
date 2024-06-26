/* tslint:disable */
/* eslint-disable */
/**
 * Listings API
 * Describes the API of Listings service
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

import { mapValues } from '../runtime';
import type { UploadedFileData } from './UploadedFileData';
import {
    UploadedFileDataFromJSON,
    UploadedFileDataFromJSONTyped,
    UploadedFileDataToJSON,
} from './UploadedFileData';

/**
 * 
 * @export
 * @interface AddListingPhotoRequestBody
 */
export interface AddListingPhotoRequestBody {
    /**
     * 
     * @type {string}
     * @memberof AddListingPhotoRequestBody
     */
    operationId: string;
    /**
     * 
     * @type {UploadedFileData}
     * @memberof AddListingPhotoRequestBody
     */
    uploadedFile: UploadedFileData;
}

/**
 * Check if a given object implements the AddListingPhotoRequestBody interface.
 */
export function instanceOfAddListingPhotoRequestBody(value: object): boolean {
    if (!('operationId' in value)) return false;
    if (!('uploadedFile' in value)) return false;
    return true;
}

export function AddListingPhotoRequestBodyFromJSON(json: any): AddListingPhotoRequestBody {
    return AddListingPhotoRequestBodyFromJSONTyped(json, false);
}

export function AddListingPhotoRequestBodyFromJSONTyped(json: any, ignoreDiscriminator: boolean): AddListingPhotoRequestBody {
    if (json == null) {
        return json;
    }
    return {
        
        'operationId': json['operationId'],
        'uploadedFile': UploadedFileDataFromJSON(json['uploadedFile']),
    };
}

export function AddListingPhotoRequestBodyToJSON(value?: AddListingPhotoRequestBody | null): any {
    if (value == null) {
        return value;
    }
    return {
        
        'operationId': value['operationId'],
        'uploadedFile': UploadedFileDataToJSON(value['uploadedFile']),
    };
}

