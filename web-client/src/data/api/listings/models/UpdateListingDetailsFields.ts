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
import type { Address } from './Address';
import {
    AddressFromJSON,
    AddressFromJSONTyped,
    AddressToJSON,
} from './Address';
import type { GuestsCapacity } from './GuestsCapacity';
import {
    GuestsCapacityFromJSON,
    GuestsCapacityFromJSONTyped,
    GuestsCapacityToJSON,
} from './GuestsCapacity';
import type { PropertyType } from './PropertyType';
import {
    PropertyTypeFromJSON,
    PropertyTypeFromJSONTyped,
    PropertyTypeToJSON,
} from './PropertyType';
import type { RoomType } from './RoomType';
import {
    RoomTypeFromJSON,
    RoomTypeFromJSONTyped,
    RoomTypeToJSON,
} from './RoomType';

/**
 * 
 * @export
 * @interface UpdateListingDetailsFields
 */
export interface UpdateListingDetailsFields {
    /**
     * 
     * @type {string}
     * @memberof UpdateListingDetailsFields
     */
    title?: string;
    /**
     * 
     * @type {string}
     * @memberof UpdateListingDetailsFields
     */
    description?: string;
    /**
     * 
     * @type {GuestsCapacity}
     * @memberof UpdateListingDetailsFields
     */
    guestCapacity?: GuestsCapacity;
    /**
     * 
     * @type {PropertyType}
     * @memberof UpdateListingDetailsFields
     */
    propertyType?: PropertyType;
    /**
     * 
     * @type {RoomType}
     * @memberof UpdateListingDetailsFields
     */
    roomType?: RoomType;
    /**
     * 
     * @type {Address}
     * @memberof UpdateListingDetailsFields
     */
    address?: Address;
}

/**
 * Check if a given object implements the UpdateListingDetailsFields interface.
 */
export function instanceOfUpdateListingDetailsFields(value: object): boolean {
    return true;
}

export function UpdateListingDetailsFieldsFromJSON(json: any): UpdateListingDetailsFields {
    return UpdateListingDetailsFieldsFromJSONTyped(json, false);
}

export function UpdateListingDetailsFieldsFromJSONTyped(json: any, ignoreDiscriminator: boolean): UpdateListingDetailsFields {
    if (json == null) {
        return json;
    }
    return {
        
        'title': json['title'] == null ? undefined : json['title'],
        'description': json['description'] == null ? undefined : json['description'],
        'guestCapacity': json['guestCapacity'] == null ? undefined : GuestsCapacityFromJSON(json['guestCapacity']),
        'propertyType': json['propertyType'] == null ? undefined : PropertyTypeFromJSON(json['propertyType']),
        'roomType': json['roomType'] == null ? undefined : RoomTypeFromJSON(json['roomType']),
        'address': json['address'] == null ? undefined : AddressFromJSON(json['address']),
    };
}

export function UpdateListingDetailsFieldsToJSON(value?: UpdateListingDetailsFields | null): any {
    if (value == null) {
        return value;
    }
    return {
        
        'title': value['title'],
        'description': value['description'],
        'guestCapacity': GuestsCapacityToJSON(value['guestCapacity']),
        'propertyType': PropertyTypeToJSON(value['propertyType']),
        'roomType': RoomTypeToJSON(value['roomType']),
        'address': AddressToJSON(value['address']),
    };
}

