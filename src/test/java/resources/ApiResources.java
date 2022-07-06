package resources;


/** Enum is a special class in Java which has collection of
 *  constants or methods. 
 *  
 */
public enum ApiResources {
	
	
	addPlaceApi("/maps/api/place/add/json"),
	getPlaceApi("/maps/api/place/get/json"),
	deletePlaceApi("/maps/api/place/delete/json");
	
	private String resource;

	
	ApiResources(String resource) {
		this.resource = resource;
			
	}	
	
	public String getResource() {
		return resource;
		
	}
	
	
	
			
	
	

}
