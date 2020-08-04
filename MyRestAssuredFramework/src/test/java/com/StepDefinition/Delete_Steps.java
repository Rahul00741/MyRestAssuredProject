package com.StepDefinition;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;


import org.junit.Assert;

import com.Base.BaseClass;
import com.UsingPojoClasses.DeleteUsing_DeletePojo;

import com.Utility.Api_Resources;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Header;
import io.restassured.http.Headers;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class Delete_Steps extends BaseClass{
	

	DeleteUsing_DeletePojo delete;
	Api_Resources method;
	Response response;
	JsonPath json;
	
	
	
	@Given("Initilise URI for DELETE Method")
	public void initilise_uri_for_delete_method() throws IOException {
		BaseSetup();
		
		RestAssured.baseURI = uri;
		logger.info("Inilizing Base URI");
	}

	@Then("Call The Pojo Class for DELETE Method")
	public void call_the_pojo_class_for_delete_method() {
		delete = new DeleteUsing_DeletePojo();
		logger.info("Calling The Pojo Class");
	    
	}
	
	@Then("Get the Response for DELETE Method")
	
	public void get_the_response_for_delete_method() throws FileNotFoundException  {
		method = Api_Resources.valueOf("DeleteResource");
		logger.info("Calling API_Resource for DeleteResource");
		
		PrintStream log = new PrintStream(new File("./Log File/DELETETLog.txt"));
		logger.info("Creating Object to Generate Log File");
		
		response = RestAssured.given()
				    .filters(RequestLoggingFilter.logRequestTo(log))
				    .filters(ResponseLoggingFilter.logResponseTo(log))
					.body(delete.deleteMethod())
					.when()
					.post(method.getresource())
					.then().statusCode(200).extract().response();
		logger.info("Getting Response for Delete Method");
		
	}
	   
	

	@Then("Validate DELETE Response")
	public void validate_delete_response() {
		//getallheaders
				logger.info("  *****************     Checking All headers    **********************");
				Headers allheaders = response.headers();
			    for (Header header:allheaders)
			    {
			    	System.out.println(header.getName()+"        " + header.getValue());
			 	 	   
			    }
			
			    		
				//Check Responce Body
				logger.info("  ******************     Checking Respose Body   *****************************");
				String ResponseBody = response.getBody().asString();
				System.out.println("Responce Body is: "+ResponseBody);
				Assert.assertTrue(ResponseBody!=null);
		//
				//checkStatuscode
				logger.info("  *******************    Checking Staus code   ***************************** ");
				int statuscode=response.getStatusCode();
				System.out.println("Status code is: " + statuscode);
				Assert.assertEquals(statuscode, 200);
	}

}
