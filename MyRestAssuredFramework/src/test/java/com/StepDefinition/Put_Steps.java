package com.StepDefinition;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Assert;

import com.Base.BaseClass;
import com.UsingPojoClasses.PutUsing_PutPojo;
import com.Utility.Api_Resources;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Put_Steps extends BaseClass{

	PutUsing_PutPojo put;
	Api_Resources method;
	Response response;
//	JsonPath json;
	
	@Given("Initilise URI for PUT Method")
	public void initilise_URI_for_PUT_Method() throws IOException {
		
	   BaseSetup();
	   RestAssured.baseURI = "https://www.change2testautomation.com";
	}

	@Then("Call The Pojo Class for PUT Method")
	public void call_the_pojo_class_for_put_method() {
	    put = new PutUsing_PutPojo();
	}

	@Then("Get the Response for PUT method")
	public void get_the_response_for_put_method() throws FileNotFoundException {
		
		method = Api_Resources.valueOf("PutResource");
		PrintStream log = new PrintStream(new File("./Log File/PutLog.txt"));
		
		response = RestAssured.given()
							.filters(RequestLoggingFilter.logRequestTo(log))
							.filters(ResponseLoggingFilter.logResponseTo(log))
							.body(put.PutMethod())
							.when().put(method.getresource())
							.then().statusCode(200).extract().response();
	}

	@Then("Validate PUT Response")
	public void validate_put_response() {
		
				//checkStatuscode
				logger.info("  *******************    Checking Staus code   ***************************** ");
				int statuscode=response.getStatusCode();
				System.out.println("Status code is: " + statuscode);
				Assert.assertEquals(statuscode, 200);
		
				//Check Responce Body
				logger.info("  ******************     Checking Respose Body   *****************************");
				String ResponseBody = response.getBody().asString();
				System.out.println("Responce Body is: "+ResponseBody);
				Assert.assertTrue(ResponseBody!=null);
	}

}
