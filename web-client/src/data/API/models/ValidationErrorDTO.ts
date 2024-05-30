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
import type { ValidationErrorDetailsDTO } from './ValidationErrorDetailsDTO';
import {
    ValidationErrorDetailsDTOFromJSON,
    ValidationErrorDetailsDTOFromJSONTyped,
    ValidationErrorDetailsDTOToJSON,
} from './ValidationErrorDetailsDTO';

/**
 * 
 * @export
 * @interface ValidationErrorDTO
 */
export interface ValidationErrorDTO {
    /**
     * 
     * @type {string}
     * @memberof ValidationErrorDTO
     */
    type: string;
    /**
     * 
     * @type {Array<ValidationErrorDetailsDTO>}
     * @memberof ValidationErrorDTO
     */
    errors: Array<ValidationErrorDetailsDTO>;
}

/**
 * Check if a given object implements the ValidationErrorDTO interface.
 */
export function instanceOfValidationErrorDTO(value: object): boolean {
    if (!('type' in value)) return false;
    if (!('errors' in value)) return false;
    return true;
}

export function ValidationErrorDTOFromJSON(json: any): ValidationErrorDTO {
    return ValidationErrorDTOFromJSONTyped(json, false);
}

export function ValidationErrorDTOFromJSONTyped(json: any, ignoreDiscriminator: boolean): ValidationErrorDTO {
    if (json == null) {
        return json;
    }
    return {
        
        'type': json['type'],
        'errors': ((json['errors'] as Array<any>).map(ValidationErrorDetailsDTOFromJSON)),
    };
}

export function ValidationErrorDTOToJSON(value?: ValidationErrorDTO | null): any {
    if (value == null) {
        return value;
    }
    return {
        
        'type': value['type'],
        'errors': ((value['errors'] as Array<any>).map(ValidationErrorDetailsDTOToJSON)),
    };
}

