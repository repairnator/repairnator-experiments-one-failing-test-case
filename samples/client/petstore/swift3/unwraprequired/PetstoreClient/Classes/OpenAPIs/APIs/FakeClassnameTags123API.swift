//
// FakeClassnameTags123API.swift
//
// Generated by openapi-generator
// https://openapi-generator.tech
//

import Foundation
import Alamofire


open class FakeClassnameTags123API: APIBase {
    /**
     To test class name in snake case
     - parameter client: (body) client model 
     - parameter completion: completion handler to receive the data and the error objects
     */
    open class func testClassname(client: Client, completion: @escaping ((_ data: Client?, _ error: ErrorResponse?) -> Void)) {
        testClassnameWithRequestBuilder(client: client).execute { (response, error) -> Void in
            completion(response?.body, error)
        }
    }


    /**
     To test class name in snake case
     - PATCH /fake_classname_test
     - API Key:
       - type: apiKey api_key_query (QUERY)
       - name: api_key_query
     - examples: [{contentType=application/json, example={
  "client" : "client"
}}]
     - parameter client: (body) client model 
     - returns: RequestBuilder<Client> 
     */
    open class func testClassnameWithRequestBuilder(client: Client) -> RequestBuilder<Client> {
        let path = "/fake_classname_test"
        let URLString = PetstoreClientAPI.basePath + path
        let parameters = client.encodeToJSON()

        let url = URLComponents(string: URLString)

        let requestBuilder: RequestBuilder<Client>.Type = PetstoreClientAPI.requestBuilderFactory.getBuilder()

        return requestBuilder.init(method: "PATCH", URLString: (url?.string ?? URLString), parameters: parameters, isBody: true)
    }

}
