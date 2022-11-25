package utility;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public abstract class BaseTest extends FrameworkUtility {
	
	protected static RequestSpecification requestSpec;
	protected static ResponseSpecification responseSpec;



	public static String readPropertyValue(String key) throws IOException {
		//Step1-create object for Properties class
		Properties prop=new Properties();
		//Step2:Read the properties file using FileInputStream class
		File f=new File(System.getProperty("user.dir")+"\\Test_Configuration\\config.properties");
		try {
			FileInputStream fis=new FileInputStream(f);
			//step3 - load all the properties
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prop.getProperty(key);
	}



	@BeforeSuite
	public void setBaseURI() {
		
        AllureLogger.logToAllure("The base URI is : "+readConfigurationFile("Base_URI"));
		requestSpec = new RequestSpecBuilder().
                		setBaseUri(readConfigurationFile("Base_URI")).
                		build();
        
	}

	/*****************************************************************************************************************/
//	@AfterSuite
	public void afterSuite() {

	}
	
	/****************************************************************************************************************/
//	@BeforeClass
	public void beforeClass() {
	}
	
	/****************************************************************************************************************/	
//	@AfterClass
	public void afterClass(){

	}
	
	/************************************************************************************************************************/
	@BeforeMethod
	public void beforeMethod() {
    	responseSpec = new ResponseSpecBuilder().build();
	}

//	@AfterMethod
	public void afterMethod() {

	}

}
/*****************************************************************************************************************/