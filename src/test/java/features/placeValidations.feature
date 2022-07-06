

Feature: Validating Place API's

@AddPlace @Regression
Scenario Outline: Verify if Place is being successfully added using addPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user calls "addPlaceApi" with "Post" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_Id created maps to "<name>" using "getPlaceApi"
	
	
	
	
	Examples:
	| name  | language |     address       |
	|AAHouse| English  | World cross center|
	#|BBHouse| French   | European Union    |
	#|CCHouse| Spain    | European Union 2  |
	
	@DeletePlace @Regression
	Scenario: Verify if delete Place functionality is working
	Given DeletePlace PayLoad
	When user calls "deletePlaceApi" with "Delete" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	
	
	
	
	
	
	 
	
	
	
	
	