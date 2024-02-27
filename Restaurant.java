import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Restaurant {

	public static void main(String[] args) {
		HttpClient httpClient = HttpClient.newHttpClient();
		String justEatUrl = "https://uk.api.just-eat.io/discovery/uk/restaurants/enriched/bypostcode/SE1%202UP";
		HttpRequest	request = HttpRequest.newBuilder()	
		.uri(URI.create(justEatUrl))
		.GET()
		.build();
		
	try {
		HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
		
		
		if(response.statusCode() == 200) {
			Gson gson = new Gson();
			JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);
			JsonArray restaurants = jsonResponse.getAsJsonArray("restaurants");
			
			System.out.println("First 10 restaurants in SE1 2UP:" + "\n");
			
			int count = 0;
			for (JsonElement restaurantElement : restaurants) {
				if(count >= 10) {
					break;
				} 
				
				JsonObject restaurant = restaurantElement.getAsJsonObject();
				String name = restaurant.get("name").getAsString();
				
				   JsonArray cuisinesArray = restaurant.getAsJsonArray("cuisines");
				    StringBuilder cuisines = new StringBuilder();
				    String cuisine1 = "";
				    String cuisine2 = "";
				    String halal = "";
				    
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
				    
				    else if (cuisinesArray.size() > 2 && "Halal".equals(cuisinesArray.get(2).getAsString())) {
				        JsonObject halalObject = cuisinesArray.get(1).getAsJsonObject();
				        halal = halalObject.get("name").getAsString();
				        cuisines.append(", " + halal);
				    }
				
				    
				JsonObject ratingObject = restaurant.getAsJsonObject("rating");
				double rating = ratingObject.get("starRating").getAsDouble();
				
				JsonObject addressObject = restaurant.getAsJsonObject("address");
				String streetAddress = addressObject.get("firstLine").getAsString();
				String city = addressObject.get("city").getAsString();
				String postCode = addressObject.get("postalCode").getAsString();
				String fullAddress = streetAddress + ", " + city + ", " + postCode; 
				
				System.out.println("Restaurant name: " + name);
				System.out.println("Restaurant cuisine: " + cuisines);
				System.out.println("Restaurant rating: " + rating);
				System.out.println("Restaurant address " + fullAddress + "\n");
				
				
				count ++;
		}
	} else {
		System.out.println("HTTP Error: " + response.statusCode());
	}
} catch (Exception e) {
	System.out.println("Error: " + e.getMessage());
}}}
