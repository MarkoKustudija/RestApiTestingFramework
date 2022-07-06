package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;
public class Hooks {
	
	

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		
	
		// default Place PayLoad
		
		StepDefinition m = new StepDefinition();
		
		
		if(StepDefinition.place_id == null) {
		
		m.add_Place_Payload_with("AAAA", "Serbian", "Futoska bb");
		m.user_calls_with_http_request("addPlaceApi", "POST");
		m.verify_place_Id_created_maps_to_using_getPlaceApi("AAAA", "getPlaceApi");
		
		
	}
}
	



}
