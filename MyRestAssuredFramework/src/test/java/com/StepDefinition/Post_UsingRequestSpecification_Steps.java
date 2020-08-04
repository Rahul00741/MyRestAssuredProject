package com.StepDefinition;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import org.json.simple.JSONObject;
import org.junit.Assert;


import com.Base.BaseClass;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Post_UsingRequestSpecification_Steps extends BaseClass {
	RequestSpecification httprequest;
	Response response;
	JsonPath json;
	
	@When("^Initilise The Base URI for Request Specification Approach$")
    public void initilise_the_base_uri_for_request_specification_approach() throws IOException {
		BaseSetup();
		
		RestAssured.baseURI = uri;
		
		
	}
	
	@Then("^Get Response For POST Method using Request Specification Approach$")
    public void get_response_for_post_method_using_request_specification_approach() throws FileNotFoundException {
		
		PrintStream log = new PrintStream(new File("./Log File/POST_RequestSpecificationLog.txt"));
		httprequest = RestAssured.given();
		
		//JSONObject will be required to the body along with the post request
		JSONObject requestParam = new JSONObject();
		requestParam.put("title", "API Resting");
		requestParam.put("body", "RestAssured");
		requestParam.put("author", "Rahul");
		
		//As we sending data in JSON format so Content type is application/json
		httprequest.header("Content-Type","application/json").filters(RequestLoggingFilter.logRequestTo(log))
	    .filters(ResponseLoggingFilter.logResponseTo(log));
		
		//Add JSON data to body of The POST Request
		httprequest.body(requestParam.toString());
		
		//Send POST request
		response = httprequest.request(Method.POST,"/api/insert.php");
	}
		
	    
	

	@Then("^Validate The Response Using Request Specification Approach$")
    public void validate_the_response_using_request_specification_approach() {
		json = new JsonPath(response.asString());
	    String actualmsg = json.getString("message");
	    System.out.println(actualmsg);
	    String expected_msg = "Data Inserted Successfully";
	    Assert.assertEquals(expected_msg, actualmsg);

	    
	    logger.info("  *****************     Checking All headers    **********************");
		Headers allheaders = response.headers();
	    for (Header header:allheaders)
	    {
	    	System.out.println(header.getName()+"        " + header.getValue());
	 	 	   
	    }
	    
	  //checkStatuscode
	  		logger.info("  *******************    Checking Staus code   ***************************** ");
	  		int statuscode=response.getStatusCode();
	  		System.out.println("Status code is: " + statuscode);
	  		Assert.assertEquals(statuscode, 200);
	}
}
