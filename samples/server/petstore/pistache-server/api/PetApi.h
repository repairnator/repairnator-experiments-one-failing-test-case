/**
* OpenAPI Petstore
* This is a sample server Petstore server. For this sample, you can use the api key `special-key` to test the authorization filters.
*
* OpenAPI spec version: 1.0.0
* 
*
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
/*
 * PetApi.h
 *
 * 
 */

#ifndef PetApi_H_
#define PetApi_H_


#include <pistache/endpoint.h>
#include <pistache/http.h>
#include <pistache/router.h>
#include <pistache/http_headers.h>

#include "ApiResponse.h"
#include "Pet.h"
#include <string>

namespace org {
namespace openapitools {
namespace server {
namespace api {

using namespace org::openapitools::server::model;

class  PetApi {
public:
    PetApi(Pistache::Address addr);
    virtual ~PetApi() {};
    void init(size_t thr);
    void start();
    void shutdown();

    const std::string base = "/v2";

private:
    void setupRoutes();

    void add_pet_handler(const Pistache::Rest::Request &request, Pistache::Http::ResponseWriter response);
    void delete_pet_handler(const Pistache::Rest::Request &request, Pistache::Http::ResponseWriter response);
    void find_pets_by_status_handler(const Pistache::Rest::Request &request, Pistache::Http::ResponseWriter response);
    void find_pets_by_tags_handler(const Pistache::Rest::Request &request, Pistache::Http::ResponseWriter response);
    void get_pet_by_id_handler(const Pistache::Rest::Request &request, Pistache::Http::ResponseWriter response);
    void update_pet_handler(const Pistache::Rest::Request &request, Pistache::Http::ResponseWriter response);
    void update_pet_with_form_handler(const Pistache::Rest::Request &request, Pistache::Http::ResponseWriter response);
    void upload_file_handler(const Pistache::Rest::Request &request, Pistache::Http::ResponseWriter response);
    void pet_api_default_handler(const Pistache::Rest::Request &request, Pistache::Http::ResponseWriter response);

    std::shared_ptr<Pistache::Http::Endpoint> httpEndpoint;
    Pistache::Rest::Router router;


    /// <summary>
    /// Add a new pet to the store
    /// </summary>
    /// <remarks>
    /// 
    /// </remarks>
    /// <param name="pet">Pet object that needs to be added to the store</param>
    virtual void add_pet(const std::shared_ptr<Pet> &pet, Pistache::Http::ResponseWriter &response) = 0;

    /// <summary>
    /// Deletes a pet
    /// </summary>
    /// <remarks>
    /// 
    /// </remarks>
    /// <param name="petId">Pet id to delete</param>
    /// <param name="apiKey"> (optional)</param>
    virtual void delete_pet(const int64_t &petId, const Optional<Net::Http::Header::Raw> &apiKey, Pistache::Http::ResponseWriter &response) = 0;

    /// <summary>
    /// Finds Pets by status
    /// </summary>
    /// <remarks>
    /// Multiple status values can be provided with comma separated strings
    /// </remarks>
    /// <param name="status">Status values that need to be considered for filter</param>
    virtual void find_pets_by_status(const Optional<std::string> &status, Pistache::Http::ResponseWriter &response) = 0;

    /// <summary>
    /// Finds Pets by tags
    /// </summary>
    /// <remarks>
    /// Multiple tags can be provided with comma separated strings. Use tag1, tag2, tag3 for testing.
    /// </remarks>
    /// <param name="tags">Tags to filter by</param>
    virtual void find_pets_by_tags(const Optional<std::string> &tags, Pistache::Http::ResponseWriter &response) = 0;

    /// <summary>
    /// Find pet by ID
    /// </summary>
    /// <remarks>
    /// Returns a single pet
    /// </remarks>
    /// <param name="petId">ID of pet to return</param>
    virtual void get_pet_by_id(const int64_t &petId, Pistache::Http::ResponseWriter &response) = 0;

    /// <summary>
    /// Update an existing pet
    /// </summary>
    /// <remarks>
    /// 
    /// </remarks>
    /// <param name="pet">Pet object that needs to be added to the store</param>
    virtual void update_pet(const std::shared_ptr<Pet> &pet, Pistache::Http::ResponseWriter &response) = 0;

    /// <summary>
    /// Updates a pet in the store with form data
    /// </summary>
    /// <remarks>
    /// 
    /// </remarks>
    virtual void update_pet_with_form(const Pistache::Rest::Request &request, Pistache::Http::ResponseWriter &response) = 0;

    /// <summary>
    /// uploads an image
    /// </summary>
    /// <remarks>
    /// 
    /// </remarks>
    virtual void upload_file(const Pistache::Rest::Request &request, Pistache::Http::ResponseWriter &response) = 0;

};

}
}
}
}

#endif /* PetApi_H_ */

