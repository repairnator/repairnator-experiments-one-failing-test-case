//
// PlaceCard.swift
//
// Generated by openapi-generator
// https://openapi-generator.tech
//

import Foundation


/** This is an card object for a Person derived from BaseCard. */

public struct PlaceCard: Codable {

    public var cardType: String
    public var placeName: String?
    public var placeAddress: String?

    public init(cardType: String, placeName: String?, placeAddress: String?) {
        self.cardType = cardType
        self.placeName = placeName
        self.placeAddress = placeAddress
    }


}

