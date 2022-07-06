package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	/** Ako hocemo da nam ispise 2. vrednost svaki put, tj. sve dostupne vrednosti
	 *  a ne poslednju (last) vrednost, onda mora da bude static! 
	 */
	
	public static RequestSpecification req; 
		
	public RequestSpecification requestSpecifications() throws IOException {
	
		if(req == null) {

			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
	
			req = new RequestSpecBuilder()
			 .setBaseUri(getGlobalValue("baseUrl"))
			 .addQueryParam("key", "qaclick123")
			 .addFilter(RequestLoggingFilter.logRequestTo(log)) // request
			 .addFilter(ResponseLoggingFilter.logResponseTo(log)) // response
			 .setContentType(ContentType.JSON).build();
			
			return req;
	
		} 
		
		return req; // else 
	 
   }	
	
	public static String getGlobalValue(String key) throws IOException {
		
		Properties prop = new Properties();
		
		FileInputStream f = new FileInputStream("/Users/markokustudija/Documents/MyAPIFramework/src/test/java/resources/global.properties");
		
		prop.load(f);
		
		return prop.getProperty(key);
			
	}
	
	
	
	public String getJsonPath(Response response, String key) {
		
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		
		return js.get(key).toString();
		
		
		
	}
}
