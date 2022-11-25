import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojoClasses.BookingDetails;
import utility.AllureLogger;
import utility.BaseTest;

import static io.restassured.RestAssured.given;

public class UpdateBooking extends BaseTest {
	
	@Test(description="To update the details of the booking IDs") 
	public void updateBooking(){
		
		AllureLogger.logToAllure("Starting the test to update details");
		//Sending the PUT request for a specific booking id and receiving the response after updating the detals
		AllureLogger.logToAllure("PUT update booking detail");
		//Created a new booking
		CreateBooking createBooking = new CreateBooking();
		createBooking.createNewBooking("time", "psos", "2018-01-03", "null");
		String IDtoUpdate = createBooking.newID;
		AllureLogger.logToAllure("New Booking ID created is : "+IDtoUpdate);
		System.out.println("IDtoUpdate:::::::   "+IDtoUpdate);
		//Update the booking with new first name
		Response getResponse = given().
				spec(requestSpec).
				pathParam("id", IDtoUpdate).
			when().
				get("/posts/{id}");

		BookingDetails bookingDetails = getResponse.as(BookingDetails.class);
		bookingDetails.settitle("employee");
		Response response = given().
			spec(requestSpec).
			header("Content-Type", "application/json").
			header("Accept", "application/json").
//			header("Cookie", cookieValue).
	        pathParam("id", IDtoUpdate).
//			pathParam("id", 3).
	        body(bookingDetails).log().body().
	    when().
			put("/posts/{id}");
		
		//Verify the response code
		AllureLogger.logToAllure("Asserting the response if the status code returned is 201");
		response.then().spec(responseSpec);		

		//To log the response to report
		logResponseAsString(response);
		
	}
}