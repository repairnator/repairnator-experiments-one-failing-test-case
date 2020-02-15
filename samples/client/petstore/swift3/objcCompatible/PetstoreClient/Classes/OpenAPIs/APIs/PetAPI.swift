//
// PetAPI.swift
//
// Generated by openapi-generator
// https://openapi-generator.tech
//

import Foundation
import Alamofire


open class PetAPI: APIBase {
    /**
     Add a new pet to the store
     - parameter pet: (body) Pet object that needs to be added to the store 
     - parameter completion: completion handler to receive the data and the error objects
     */
    open class func addPet(pet: Pet, completion: @escaping ((_ error: ErrorResponse?) -> Void)) {
        addPetWithRequestBuilder(pet: pet).execute { (response, error) -> Void in
            completion(error)
        }
    }


    /**
     Add a new pet to the store
     - POST /pet
     - OAuth:
       - type: oauth2
       - name: petstore_auth
     - parameter pet: (body) Pet object that needs to be added to the store 
     - returns: RequestBuilder<Void> 
     */
    open class func addPetWithRequestBuilder(pet: Pet) -> RequestBuilder<Void> {
        let path = "/pet"
        let URLString = PetstoreClientAPI.basePath + path
        let parameters = pet.encodeToJSON()

        let url = URLComponents(string: URLString)

        let requestBuilder: RequestBuilder<Void>.Type = PetstoreClientAPI.requestBuilderFactory.getBuilder()

        return requestBuilder.init(method: "POST", URLString: (url?.string ?? URLString), parameters: parameters, isBody: true)
    }

    /**
     Deletes a pet
     - parameter petId: (path) Pet id to delete 
     - parameter apiKey: (header)  (optional)
     - parameter completion: completion handler to receive the data and the error objects
     */
    open class func deletePet(petId: Int64, apiKey: String? = nil, completion: @escaping ((_ error: ErrorResponse?) -> Void)) {
        deletePetWithRequestBuilder(petId: petId, apiKey: apiKey).execute { (response, error) -> Void in
            completion(error)
        }
    }


    /**
     Deletes a pet
     - DELETE /pet/{petId}
     - OAuth:
       - type: oauth2
       - name: petstore_auth
     - parameter petId: (path) Pet id to delete 
     - parameter apiKey: (header)  (optional)
     - returns: RequestBuilder<Void> 
     */
    open class func deletePetWithRequestBuilder(petId: Int64, apiKey: String? = nil) -> RequestBuilder<Void> {
        var path = "/pet/{petId}"
        let petIdPreEscape = "\(petId)"
        let petIdPostEscape = petIdPreEscape.addingPercentEncoding(withAllowedCharacters: .urlPathAllowed) ?? ""
        path = path.replacingOccurrences(of: "{petId}", with: petIdPostEscape, options: .literal, range: nil)
        let URLString = PetstoreClientAPI.basePath + path
        let parameters: [String:Any]? = nil
        
        let url = URLComponents(string: URLString)
        let nillableHeaders: [String: Any?] = [
            "api_key": apiKey
        ]
        let headerParameters = APIHelper.rejectNilHeaders(nillableHeaders)

        let requestBuilder: RequestBuilder<Void>.Type = PetstoreClientAPI.requestBuilderFactory.getBuilder()

        return requestBuilder.init(method: "DELETE", URLString: (url?.string ?? URLString), parameters: parameters, isBody: false, headers: headerParameters)
    }

    /**
     * enum for parameter status
     */
    public enum Status_findPetsByStatus: String { 
        case available = "available"
        case pending = "pending"
        case sold = "sold"
    }

    /**
     Finds Pets by status
     - parameter status: (query) Status values that need to be considered for filter 
     - parameter completion: completion handler to receive the data and the error objects
     */
    open class func findPetsByStatus(status: [String], completion: @escaping ((_ data: [Pet]?, _ error: ErrorResponse?) -> Void)) {
        findPetsByStatusWithRequestBuilder(status: status).execute { (response, error) -> Void in
            completion(response?.body, error)
        }
    }


    /**
     Finds Pets by status
     - GET /pet/findByStatus
     - Multiple status values can be provided with comma separated strings
     - OAuth:
       - type: oauth2
       - name: petstore_auth
     - examples: [{contentType=application/json, example={
  "photoUrls" : [ "photoUrls", "photoUrls" ],
  "name" : "doggie",
  "id" : 0,
  "category" : {
    "name" : "name",
    "id" : 6
  },
  "tags" : [ {
    "name" : "name",
    "id" : 1
  }, {
    "name" : "name",
    "id" : 1
  } ],
  "status" : "available"
}}, {contentType=application/xml, example=<Pet>
  <id>123456789</id>
  <name>doggie</name>
  <photoUrls>
    <photoUrls>aeiou</photoUrls>
  </photoUrls>
  <tags>
  </tags>
  <status>aeiou</status>
</Pet>}]
     - examples: [{contentType=application/json, example={
  "photoUrls" : [ "photoUrls", "photoUrls" ],
  "name" : "doggie",
  "id" : 0,
  "category" : {
    "name" : "name",
    "id" : 6
  },
  "tags" : [ {
    "name" : "name",
    "id" : 1
  }, {
    "name" : "name",
    "id" : 1
  } ],
  "status" : "available"
}}, {contentType=application/xml, example=<Pet>
  <id>123456789</id>
  <name>doggie</name>
  <photoUrls>
    <photoUrls>aeiou</photoUrls>
  </photoUrls>
  <tags>
  </tags>
  <status>aeiou</status>
</Pet>}]
     - parameter status: (query) Status values that need to be considered for filter 
     - returns: RequestBuilder<[Pet]> 
     */
    open class func findPetsByStatusWithRequestBuilder(status: [String]) -> RequestBuilder<[Pet]> {
        let path = "/pet/findByStatus"
        let URLString = PetstoreClientAPI.basePath + path
        let parameters: [String:Any]? = nil
        
        var url = URLComponents(string: URLString)
        url?.queryItems = APIHelper.mapValuesToQueryItems(values:[
            "status": status
        ])

        let requestBuilder: RequestBuilder<[Pet]>.Type = PetstoreClientAPI.requestBuilderFactory.getBuilder()

        return requestBuilder.init(method: "GET", URLString: (url?.string ?? URLString), parameters: parameters, isBody: false)
    }

    /**
     Finds Pets by tags
     - parameter tags: (query) Tags to filter by 
     - parameter completion: completion handler to receive the data and the error objects
     */
    open class func findPetsByTags(tags: [String], completion: @escaping ((_ data: [Pet]?, _ error: ErrorResponse?) -> Void)) {
        findPetsByTagsWithRequestBuilder(tags: tags).execute { (response, error) -> Void in
            completion(response?.body, error)
        }
    }


    /**
     Finds Pets by tags
     - GET /pet/findByTags
     - Multiple tags can be provided with comma separated strings. Use tag1, tag2, tag3 for testing.
     - OAuth:
       - type: oauth2
       - name: petstore_auth
     - examples: [{contentType=application/json, example={
  "photoUrls" : [ "photoUrls", "photoUrls" ],
  "name" : "doggie",
  "id" : 0,
  "category" : {
    "name" : "name",
    "id" : 6
  },
  "tags" : [ {
    "name" : "name",
    "id" : 1
  }, {
    "name" : "name",
    "id" : 1
  } ],
  "status" : "available"
}}, {contentType=application/xml, example=<Pet>
  <id>123456789</id>
  <name>doggie</name>
  <photoUrls>
    <photoUrls>aeiou</photoUrls>
  </photoUrls>
  <tags>
  </tags>
  <status>aeiou</status>
</Pet>}]
     - examples: [{contentType=application/json, example={
  "photoUrls" : [ "photoUrls", "photoUrls" ],
  "name" : "doggie",
  "id" : 0,
  "category" : {
    "name" : "name",
    "id" : 6
  },
  "tags" : [ {
    "name" : "name",
    "id" : 1
  }, {
    "name" : "name",
    "id" : 1
  } ],
  "status" : "available"
}}, {contentType=application/xml, example=<Pet>
  <id>123456789</id>
  <name>doggie</name>
  <photoUrls>
    <photoUrls>aeiou</photoUrls>
  </photoUrls>
  <tags>
  </tags>
  <status>aeiou</status>
</Pet>}]
     - parameter tags: (query) Tags to filter by 
     - returns: RequestBuilder<[Pet]> 
     */
    open class func findPetsByTagsWithRequestBuilder(tags: [String]) -> RequestBuilder<[Pet]> {
        let path = "/pet/findByTags"
        let URLString = PetstoreClientAPI.basePath + path
        let parameters: [String:Any]? = nil
        
        var url = URLComponents(string: URLString)
        url?.queryItems = APIHelper.mapValuesToQueryItems(values:[
            "tags": tags
        ])

        let requestBuilder: RequestBuilder<[Pet]>.Type = PetstoreClientAPI.requestBuilderFactory.getBuilder()

        return requestBuilder.init(method: "GET", URLString: (url?.string ?? URLString), parameters: parameters, isBody: false)
    }

    /**
     Find pet by ID
     - parameter petId: (path) ID of pet to return 
     - parameter completion: completion handler to receive the data and the error objects
     */
    open class func getPetById(petId: Int64, completion: @escaping ((_ data: Pet?, _ error: ErrorResponse?) -> Void)) {
        getPetByIdWithRequestBuilder(petId: petId).execute { (response, error) -> Void in
            completion(response?.body, error)
        }
    }


    /**
     Find pet by ID
     - GET /pet/{petId}
     - Returns a single pet
     - API Key:
       - type: apiKey api_key 
       - name: api_key
     - examples: [{contentType=application/json, example={
  "photoUrls" : [ "photoUrls", "photoUrls" ],
  "name" : "doggie",
  "id" : 0,
  "category" : {
    "name" : "name",
    "id" : 6
  },
  "tags" : [ {
    "name" : "name",
    "id" : 1
  }, {
    "name" : "name",
    "id" : 1
  } ],
  "status" : "available"
}}, {contentType=application/xml, example=<Pet>
  <id>123456789</id>
  <name>doggie</name>
  <photoUrls>
    <photoUrls>aeiou</photoUrls>
  </photoUrls>
  <tags>
  </tags>
  <status>aeiou</status>
</Pet>}]
     - examples: [{contentType=application/json, example={
  "photoUrls" : [ "photoUrls", "photoUrls" ],
  "name" : "doggie",
  "id" : 0,
  "category" : {
    "name" : "name",
    "id" : 6
  },
  "tags" : [ {
    "name" : "name",
    "id" : 1
  }, {
    "name" : "name",
    "id" : 1
  } ],
  "status" : "available"
}}, {contentType=application/xml, example=<Pet>
  <id>123456789</id>
  <name>doggie</name>
  <photoUrls>
    <photoUrls>aeiou</photoUrls>
  </photoUrls>
  <tags>
  </tags>
  <status>aeiou</status>
</Pet>}]
     - parameter petId: (path) ID of pet to return 
     - returns: RequestBuilder<Pet> 
     */
    open class func getPetByIdWithRequestBuilder(petId: Int64) -> RequestBuilder<Pet> {
        var path = "/pet/{petId}"
        let petIdPreEscape = "\(petId)"
        let petIdPostEscape = petIdPreEscape.addingPercentEncoding(withAllowedCharacters: .urlPathAllowed) ?? ""
        path = path.replacingOccurrences(of: "{petId}", with: petIdPostEscape, options: .literal, range: nil)
        let URLString = PetstoreClientAPI.basePath + path
        let parameters: [String:Any]? = nil
        
        let url = URLComponents(string: URLString)

        let requestBuilder: RequestBuilder<Pet>.Type = PetstoreClientAPI.requestBuilderFactory.getBuilder()

        return requestBuilder.init(method: "GET", URLString: (url?.string ?? URLString), parameters: parameters, isBody: false)
    }

    /**
     Update an existing pet
     - parameter pet: (body) Pet object that needs to be added to the store 
     - parameter completion: completion handler to receive the data and the error objects
     */
    open class func updatePet(pet: Pet, completion: @escaping ((_ error: ErrorResponse?) -> Void)) {
        updatePetWithRequestBuilder(pet: pet).execute { (response, error) -> Void in
            completion(error)
        }
    }


    /**
     Update an existing pet
     - PUT /pet
     - OAuth:
       - type: oauth2
       - name: petstore_auth
     - parameter pet: (body) Pet object that needs to be added to the store 
     - returns: RequestBuilder<Void> 
     */
    open class func updatePetWithRequestBuilder(pet: Pet) -> RequestBuilder<Void> {
        let path = "/pet"
        let URLString = PetstoreClientAPI.basePath + path
        let parameters = pet.encodeToJSON()

        let url = URLComponents(string: URLString)

        let requestBuilder: RequestBuilder<Void>.Type = PetstoreClientAPI.requestBuilderFactory.getBuilder()

        return requestBuilder.init(method: "PUT", URLString: (url?.string ?? URLString), parameters: parameters, isBody: true)
    }

    /**
     Updates a pet in the store with form data
     - parameter petId: (path) ID of pet that needs to be updated 
     - parameter name: (form) Updated name of the pet (optional)
     - parameter status: (form) Updated status of the pet (optional)
     - parameter completion: completion handler to receive the data and the error objects
     */
    open class func updatePetWithForm(petId: Int64, name: String? = nil, status: String? = nil, completion: @escaping ((_ error: ErrorResponse?) -> Void)) {
        updatePetWithFormWithRequestBuilder(petId: petId, name: name, status: status).execute { (response, error) -> Void in
            completion(error)
        }
    }


    /**
     Updates a pet in the store with form data
     - POST /pet/{petId}
     - OAuth:
       - type: oauth2
       - name: petstore_auth
     - parameter petId: (path) ID of pet that needs to be updated 
     - parameter name: (form) Updated name of the pet (optional)
     - parameter status: (form) Updated status of the pet (optional)
     - returns: RequestBuilder<Void> 
     */
    open class func updatePetWithFormWithRequestBuilder(petId: Int64, name: String? = nil, status: String? = nil) -> RequestBuilder<Void> {
        var path = "/pet/{petId}"
        let petIdPreEscape = "\(petId)"
        let petIdPostEscape = petIdPreEscape.addingPercentEncoding(withAllowedCharacters: .urlPathAllowed) ?? ""
        path = path.replacingOccurrences(of: "{petId}", with: petIdPostEscape, options: .literal, range: nil)
        let URLString = PetstoreClientAPI.basePath + path
        let formParams: [String:Any?] = [
            "name": name,
            "status": status
        ]

        let nonNullParameters = APIHelper.rejectNil(formParams)
        let parameters = APIHelper.convertBoolToString(nonNullParameters)
        
        let url = URLComponents(string: URLString)

        let requestBuilder: RequestBuilder<Void>.Type = PetstoreClientAPI.requestBuilderFactory.getBuilder()

        return requestBuilder.init(method: "POST", URLString: (url?.string ?? URLString), parameters: parameters, isBody: false)
    }

    /**
     uploads an image
     - parameter petId: (path) ID of pet to update 
     - parameter additionalMetadata: (form) Additional data to pass to server (optional)
     - parameter file: (form) file to upload (optional)
     - parameter completion: completion handler to receive the data and the error objects
     */
    open class func uploadFile(petId: Int64, additionalMetadata: String? = nil, file: URL? = nil, completion: @escaping ((_ data: ApiResponse?, _ error: ErrorResponse?) -> Void)) {
        uploadFileWithRequestBuilder(petId: petId, additionalMetadata: additionalMetadata, file: file).execute { (response, error) -> Void in
            completion(response?.body, error)
        }
    }


    /**
     uploads an image
     - POST /pet/{petId}/uploadImage
     - OAuth:
       - type: oauth2
       - name: petstore_auth
     - examples: [{contentType=application/json, example={
  "code" : 0,
  "type" : "type",
  "message" : "message"
}}]
     - parameter petId: (path) ID of pet to update 
     - parameter additionalMetadata: (form) Additional data to pass to server (optional)
     - parameter file: (form) file to upload (optional)
     - returns: RequestBuilder<ApiResponse> 
     */
    open class func uploadFileWithRequestBuilder(petId: Int64, additionalMetadata: String? = nil, file: URL? = nil) -> RequestBuilder<ApiResponse> {
        var path = "/pet/{petId}/uploadImage"
        let petIdPreEscape = "\(petId)"
        let petIdPostEscape = petIdPreEscape.addingPercentEncoding(withAllowedCharacters: .urlPathAllowed) ?? ""
        path = path.replacingOccurrences(of: "{petId}", with: petIdPostEscape, options: .literal, range: nil)
        let URLString = PetstoreClientAPI.basePath + path
        let formParams: [String:Any?] = [
            "additionalMetadata": additionalMetadata,
            "file": file
        ]

        let nonNullParameters = APIHelper.rejectNil(formParams)
        let parameters = APIHelper.convertBoolToString(nonNullParameters)
        
        let url = URLComponents(string: URLString)

        let requestBuilder: RequestBuilder<ApiResponse>.Type = PetstoreClientAPI.requestBuilderFactory.getBuilder()

        return requestBuilder.init(method: "POST", URLString: (url?.string ?? URLString), parameters: parameters, isBody: false)
    }

}
