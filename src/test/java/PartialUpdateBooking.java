import io.restassured.response.Response;
import org.testng.annotations.Test;
import utility.AllureLogger;
import utility.BaseTest;

import static io.restassured.RestAssured.given;

public class PartialUpdateBooking extends BaseTest {
	
	@Test(description="To update the details of the booking IDs") 
	public void updateBooking(){
		
		AllureLogger.logToAllure("Starting the test to update details");
		/*******************************************************
		 * Send a PUT request to /booking/{id}
		 * and check that the response has HTTP status code 200
		 ******************************************************/
		
		//Sending the PUT request for a specific booking id and receiving the response after updating the detals
		AllureLogger.logToAllure("PUT update booking detail");
		
		//To get the auth token
//		String newAuthToken = AuthToken.post_CreateAuth();
//		AllureLogger.logToAllure("Auth token is : "+newAuthToken);
		
		//Created a new booking
		CreateBooking createBooking = new CreateBooking();
		createBooking.createNewBooking("Varys", "114", "2018-01-03","null");
		String IDtoUpdate = createBooking.newID;
		AllureLogger.logToAllure("New Booking ID created is : "+IDtoUpdate);
		
		//Setting the alue to be sent in the patch request
		String setValue = "{\"title\":\"sam\"}";
		
		//String cookieValue = "token="+newAuthToken;
		//System.out.println("cookieValue is :"+cookieValue);
		
		//Sending the PATCH request
		AllureLogger.logToAllure("Sending the PATCH request to partially update the booking detail of booking id : "+IDtoUpdate);
		Response response = given().
			spec(requestSpec).
			header("Content-Type", "application/json").
			header("Accept", "application/json").
		//	header("Cookie", cookieValue).
	        pathParam("id", IDtoUpdate).
	        body(setValue).log().body().
	    when().
			patch("/posts/{id}");

		//Verify the response code
		AllureLogger.logToAllure("Asserting the response if the status code returned is 200");
		response.then().spec(responseSpec);		

		//To log the response to report
		logResponseAsString(response);
		
	}
	@Test(description="To update the details of the booking IDs")
	public void updateExistingBooking(){

		AllureLogger.logToAllure("Starting the test to update details");
		/*******************************************************
		 * Send a PUT request to /booking/{id}
		 * and check that the response has HTTP status code 200
		 ******************************************************/

		//Sending the PUT request for a specific booking id and receiving the response after updating the detals
		AllureLogger.logToAllure("PUT update booking detail");

		//To get the auth token
//		String newAuthToken = AuthToken.post_CreateAuth();
//		AllureLogger.logToAllure("Auth token is : "+newAuthToken);

		//Created a new booking
		//CreateBooking createBooking = new CreateBooking();
		//createBooking.createNewBooking("Varys", "114", "2018-01-03","null");
		//String IDtoUpdate = createBooking.newID;
		//AllureLogger.logToAllure("New Booking ID created is : "+IDtoUpdate);

		//Setting the alue to be sent in the patch request
		String setValue = "{\"author\":\"Curran\"}";

		//String cookieValue = "token="+newAuthToken;
		//System.out.println("cookieValue is :"+cookieValue);

		//Sending the PATCH request
		AllureLogger.logToAllure("Sending the PATCH request to partially update the booking detail of booking id : "+"38");
		Response response = given().
				spec(requestSpec).
				header("Content-Type", "application/json").
				header("Accept", "application/json").
				//	header("Cookie", cookieValue).
						pathParam("id", 38).
				body(setValue).log().body().
				when().
				patch("/posts/{id}");

		//Verify the response code
		AllureLogger.logToAllure("Asserting the response if the status code returned is 200");
		response.then().spec(responseSpec);

		//To log the response to report
		logResponseAsString(response);

	}

}
