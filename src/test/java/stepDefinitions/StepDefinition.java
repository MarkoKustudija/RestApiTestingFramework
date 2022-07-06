package stepDefinitions;

import static io.restassured.RestAssured.given;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.ApiResources;
import resources.BaseURL;
import resources.Demo;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {
	
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	Demo demo = new Demo();
	TestDataBuild data = new TestDataBuild();
	
	static String place_id;
	

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException{
    
    	res = given().spec(requestSpecifications()) // prosledjujem metodu iz Utils clase koju nasledjujem
    	.body(data.addPlacePayLoad(name, language, address));
    
  
       }
    
   	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpMethod) {
    
    	
		ApiResources resourcesAPI = ApiResources.valueOf(resource);
		System.out.println(resourcesAPI.getResource());
		
		resspec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();
		
		if(httpMethod.equalsIgnoreCase("POST")) {
			
			response = res.when().post(resourcesAPI.getResource());
		
		} else if(httpMethod.equalsIgnoreCase("GET")) {
			
			response = res.when().get(resourcesAPI.getResource());
			 
		} else if(httpMethod.equalsIgnoreCase("DELETE")){
		    
			response = res.when().delete(resourcesAPI.getResource());
		}
		
			
//        response = res.when().post("/maps/api/place/add/json")
//        		.then().spec(resspec).extract().response();

    	
    }
    

    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1){
    	assertEquals(response.getStatusCode(),200);
    
    }
        

    @And("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String ExpectedValue) {
    	
//    	String resp = response.asString();
//    	js = new JsonPath(resp);
//    	assertEquals(js.get(keyValue).toString(), ExpectedValue);
    	
    	assertEquals(getJsonPath(response, keyValue), ExpectedValue);
    	
    
    } 
    
    
    @And("verify place_Id created maps to {string} using {string}")
    public void verify_place_Id_created_maps_to_using_getPlaceApi(String expectedName, String resource) throws IOException {
    	
       /** requestSpec */ 
    	
    	place_id = getJsonPath(response, "place_id");
    	res = given().spec(requestSpecifications()).queryParam("place_id", place_id);
    	
    	user_calls_with_http_request(resource, "GET");
    	String actualName = getJsonPath(response, "name");
    	
    	assertEquals(actualName, expectedName);
    	
    }
    	
    	
    @Given("DeletePlace PayLoad")
    public void deleteplace_PayLoad() throws IOException {
    	
    	res = given().spec(requestSpecifications())
    			.body(data.deletePlacePayload(place_id));
    	
    	
    }
    	
    	
 }
    

