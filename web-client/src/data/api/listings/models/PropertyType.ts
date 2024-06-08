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


/**
 * 
 * @export
 */
export const PropertyType = {
    Apartment: 'APARTMENT',
    House: 'HOUSE',
    Villa: 'VILLA',
    Cabin: 'CABIN',
    Cottage: 'COTTAGE',
    Chalet: 'CHALET',
    Bungalow: 'BUNGALOW',
    Townhouse: 'TOWNHOUSE',
    Guesthouse: 'GUESTHOUSE',
    Loft: 'LOFT',
    Hostel: 'HOSTEL',
    Boat: 'BOAT',
    BedAndBreakfast: 'BED_AND_BREAKFAST',
    Other: 'OTHER'
} as const;
export type PropertyType = typeof PropertyType[keyof typeof PropertyType];


export function instanceOfPropertyType(value: any): boolean {
    return Object.values(PropertyType).includes(value);
}

export function PropertyTypeFromJSON(json: any): PropertyType {
    return PropertyTypeFromJSONTyped(json, false);
}

export function PropertyTypeFromJSONTyped(json: any, ignoreDiscriminator: boolean): PropertyType {
    return json as PropertyType;
}

export function PropertyTypeToJSON(value?: PropertyType | null): any {
    return value as any;
}
