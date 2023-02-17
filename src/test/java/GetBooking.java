import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pojoClasses.BookingDetails;
import utility.AllureLogger;
import utility.BaseTest;
import utility.ExcelLib;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
public class GetBooking extends BaseTest {
	Response resp;
	@DataProvider (name="DataFromExcel")
	public Object[][] data() throws Exception {
		ExcelLib xl = new ExcelLib("Booking", this.getClass().getSimpleName());
		return xl.getTestdata();
	}

	@Test(enabled = true,dataProvider="DataFromExcel", description="To retrieve the details of the booking IDs")
	public void getBookingIDs(String title,
							  String author,
							  String createdat,String dontUseThis
							  ){
		
		AllureLogger.logToAllure("Starting the test to get booking details");
		/*******************************************************
		 * Send a GET request to /booking/{id}
		 * and check that the response has HTTP status code 200
		 ******************************************************/
		
		//Sending the GET request for a specific booking id and receiving the response
		AllureLogger.logToAllure("Getting the response for the Booking IDs from test data excel");
		Response response = given().
				spec(requestSpec).
				pathParam("id", 7).
			when().
				get("/posts/{id}");
		      //   get("/api/Tourist/?page={id}");

		System.out.println("response:::::   "+response);
		//Verify the response code
		System.out.println("response:::::   "+responseSpec);
		AllureLogger.logToAllure("Asserting the response if the status code returned is 200");
		response.then().spec(responseSpec);		

		//To log the response to report
		logResponseAsString(response);
		
		
		//Using the POJO class
		AllureLogger.logToAllure("Asserting of response body with the data from test data excel");
		
		BookingDetails bookingDetails = response.as(BookingDetails.class);
		
		Assert.assertEquals(bookingDetails.gettitle(title), title);
		Assert.assertEquals(bookingDetails.getAuthor(author), author);
	//	System.out.println("d  "+bookingDetails.getTotalprice());
		//System.out.println("   "+totalprice);
	//	Assert.assertEquals(bookingDetails.getlocation(tourist_location), tourist_location);
		Assert.assertEquals(bookingDetails.getCreatedat(createdat), createdat);
	//	Assert.assertEquals(bookingDetails.getBookingdates().getCheckin(), checkin);
	//	Assert.assertEquals(bookingDetails.getBookingdates().getCheckout(), checkout);
		
	}

@Test(enabled = true)
public void ValidStatusCode() throws IOException {

	resp= RestAssured.given().
		  spec(requestSpec).
	      header("Accept","application/json").

			pathParam("id","7").
			when().
			get("/posts/{id}");

	int respCode=resp.getStatusCode();
	Assert.assertEquals(respCode, 200);
}
@Test(enabled = true)
public void ValidStatusCodeWithPage() throws IOException {
	Integer pageNumber= Integer.parseInt(readPropertyValue("page"));
		resp= RestAssured.given().
				spec(requestSpec).
				header("Accept","application/json").
			//	pathParam("page", pageNumber).
				when().
				get("/posts");
		int respCode=resp.getStatusCode();
		Assert.assertEquals(respCode, 200);
	}

@Test(enabled = true)
public void ValidStatusCode_page_number() throws IOException {
		Integer pageNumber= Integer.parseInt(readPropertyValue("page"));
		String emailid = readPropertyValue("emailid");

		resp=RestAssured.given().
				spec(requestSpec).
				header("Accept","application/json").

				pathParam("id", 1).
				when().
				get("/posts/{id}").
				then().
				contentType("application/json").
				extract().response();
		System.out.println("Response3333  "+resp.path("title"));
		//resp.getBody();
		String email=resp.path("title");
		Assert.assertEquals(email, emailid);
		int respCode=resp.getStatusCode();
		Assert.assertEquals(respCode, 200);
	}

	@Test(enabled = true)
	public void JSONSchemaValidation() throws IOException {
		File schema = new File(System.getProperty("user.dir")+"\\src\\test\\java\\config\\"+"schema.JSON");
		//base URL
		//RestAssured.baseURI = "https://jsonplaceholder.typicode.com/posts/2";

		//RestAssured.baseURI=readPropertyValue("baseUrl");
		//set the basepath
		//RestAssured.basePath=readPropertyValue("gettSingleUserEndPoint");

		RestAssured.given().
				spec(requestSpec).
				header("Accept","application/json").

				pathParam("id", 14).
				when().
				get("/posts/{id}")

						//verify JSON Schema
				.then().assertThat()
				.body(matchesJsonSchemaInClasspath("schema.JSON"));



	}


}
