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

import type { ErrorDTO } from './ErrorDTO';
import {
    instanceOfErrorDTO,
    ErrorDTOFromJSON,
    ErrorDTOFromJSONTyped,
    ErrorDTOToJSON,
} from './ErrorDTO';
import type { ValidationErrorDTO } from './ValidationErrorDTO';
import {
    instanceOfValidationErrorDTO,
    ValidationErrorDTOFromJSON,
    ValidationErrorDTOFromJSONTyped,
    ValidationErrorDTOToJSON,
} from './ValidationErrorDTO';

/**
 * @type VerifyEmail400Response
 * 
 * @export
 */
export type VerifyEmail400Response = ErrorDTO | ValidationErrorDTO;

export function VerifyEmail400ResponseFromJSON(json: any): VerifyEmail400Response {
    return VerifyEmail400ResponseFromJSONTyped(json, false);
}

export function VerifyEmail400ResponseFromJSONTyped(json: any, ignoreDiscriminator: boolean): VerifyEmail400Response {
    if (json == null) {
        return json;
    }
    return ErrorDTOFromJSONTyped(json, true) || ValidationErrorDTOFromJSONTyped(json, true);
}

export function VerifyEmail400ResponseToJSON(value?: VerifyEmail400Response | null): any {
    if (value == null) {
        return value;
    }

    if (instanceOfErrorDTO(value)) {
        return ErrorDTOToJSON(value as ErrorDTO);
    }
    if (instanceOfValidationErrorDTO(value)) {
        return ValidationErrorDTOToJSON(value as ValidationErrorDTO);
    }

    return {};
}
