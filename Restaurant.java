//import statements to import everything required for Java to work with HTTP requests
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

//import statements for different classes from the Gson library used 
import com.google.gson.Gson; //contains methods to convert JSON strings to Java objects, and Java objects to JSON strings
import com.google.gson.JsonArray; //needed to work with JSON arrays
import com.google.gson.JsonElement; //needed to work with JSON elements
import com.google.gson.JsonObject; //needed to work with JSON objects

public class Restaurant {

	public static void main(String[] args) {
		//the below block is standard grammar when sending a GET request for retrieving specific data
		
		HttpClient httpClient = HttpClient.newHttpClient(); //creates httpClient object and is used for sending HTTP requests and receiving HTTP responses
		String justEatUrl = "https://uk.api.just-eat.io/discovery/uk/restaurants/enriched/bypostcode/SE1%202UP"; //String variable for the URL for the postcode I chose for the activity
		HttpRequest request = HttpRequest.newBuilder()	
		.uri(URI.create(justEatUrl))
		.GET()
		.build();
		
	try { // start of try-catch block for error handling

		//below standard grammar for a similar request, uses HttpRespose to send a HTTP request and 'handles' response as a String
		HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
		
		if(response.statusCode() == 200) { //status code 200 means the response was successful
			Gson gson = new Gson(); //new GSON object
			JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class); //Gson library used to parse the response from a JSON string to a JsonObject.
			JsonArray restaurants = jsonResponse.getAsJsonArray("restaurants"); //restaurants array extracted from JSON object 
			
			System.out.println("First 10 restaurants in SE1 2UP:" + "\n");
			
			int count = 0;
			for (JsonElement restaurantElement : restaurants) { //for loop to iterate over elements in 'restaurants' array, set to break once 10 have been iterated over
				if(count >= 10) {
					break;
				} 
				//if the loop has not yet reached 10, it is to create a new JSON object titled 'restaurant' and retrieve the elements requested in the assignment brief and assign these to appropriately named variables
				
				JsonObject restaurant = restaurantElement.getAsJsonObject();
				String name = restaurant.get("name").getAsString();
			
				   JsonArray cuisinesArray = restaurant.getAsJsonArray("cuisines");
				    StringBuilder cuisines = new StringBuilder();
				    String cuisine1 = "";
				    String cuisine2 = "";
				    String halal = "";

				//3 if statements rather than else if to ensure all below situations will be executed, creation of a separate cuisine option for each cuisine, and addition to 'cuisines' string
				
				    if (cuisinesArray.size() > 0) {
				    	JsonObject cuisineObject1 = cuisinesArray.get(0).getAsJsonObject();
				    	cuisine1 = cuisineObject1.get("name").getAsString();
				    	cuisines.append(cuisine1);
				    }
				    
				    if (cuisinesArray.size() > 1) {
				    	JsonObject cuisineObject2 = cuisinesArray.get(1).getAsJsonObject();
				    	cuisine2 = cuisineObject2.get("name").getAsString();
				    	cuisines.append(", " + cuisine2);
				    }
				    
				   if (cuisinesArray.size() > 2 && "Halal".equals(cuisinesArray.get(2).getAsString())) {
				        JsonObject halalObject = cuisinesArray.get(1).getAsJsonObject();
				        halal = halalObject.get("name").getAsString();
				        cuisines.append(", " + halal);
				    }
				
				   
				JsonObject ratingObject = restaurant.getAsJsonObject("rating");
				double rating = ratingObject.get("starRating").getAsDouble();
				//create rating object and assign Star Rating to rating variable
				
				JsonObject addressObject = restaurant.getAsJsonObject("address");
				String streetAddress = addressObject.get("firstLine").getAsString();
				String city = addressObject.get("city").getAsString();
				String postCode = addressObject.get("postalCode").getAsString();
				String fullAddress = streetAddress + ", " + city + ", " + postCode; 
				//create address object and assign all elements of an address to their own variables and concatenate as a string
				
				System.out.println("Restaurant name: " + name);
				System.out.println("Restaurant cuisine: " + cuisines);
				System.out.println("Restaurant rating: " + rating);
				System.out.println("Restaurant address " + fullAddress + "\n");
				//print all statements as requested in assignment brief
				
				count ++; //increment by 1 to ensure there is no infinite loop
		}
	} else {
		System.out.println("HTTP Error: " + response.statusCode()); //if status is not 200, prints out the status code
	}
} catch (Exception e) {
	System.out.println("Error: " + e.getMessage()); //end of try-catch error handling block, prints out error message if programme does not run correctly
}}}
