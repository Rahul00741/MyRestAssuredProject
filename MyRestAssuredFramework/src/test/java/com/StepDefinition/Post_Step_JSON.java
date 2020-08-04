package com.StepDefinition;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Assert;

import com.Base.BaseClass;
import com.UsingPojoClasses.PostUsing_PostPojo;
import com.Utility.Api_Resources;

//import io.cucumber.core.resource.Resource;
//import io.cucumber.gherkin.internal.com.eclipsesource.json.Json;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
//import io.restassured.path.json.JsonPath;
//import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Post_Step_JSON extends BaseClass{
	
	PostUsing_PostPojo post;
	Api_Resources method;
	Response response;
	JsonPath json;
	
	@Given("^Initilise Base URI and Base Path for JSON$")
	public void initilise_base_uri_and_base_path() throws IOException {
		BaseSetup();
		
		
		RestAssured.baseURI = uri;

		logger.info("Inilizing Base URI");
	}

	@Then("^Call the Pojo Class for JSON$")
	public void call_the_pojo_class() {
		
		post = new PostUsing_PostPojo();
		logger.info("Calling The Pojo Class");
	    
	}

	@Then("Get The Response for POST Method for JSON with {string} {string} and {string}")
	public void get_the_response_for_post_method_for_json_with_and(String body, String title, String author) throws FileNotFoundException {
	    
		method = Api_Resources.valueOf("PostResource");
		logger.info("Calling API_Resource for PostResource");
		PrintStream log = new PrintStream(new File("./Log File/POST_JOSNLog.txt"));
		logger.info("Creating Object to Generate Log File");

		response = RestAssured.given()
					.contentType(ContentType.JSON)
				    .filters(RequestLoggingFilter.logRequestTo(log))
				    .filters(ResponseLoggingFilter.logResponseTo(log))
					.body(post.PostMethod(body,title,author))
					.when()
					.post(method.getresource())
					.then().statusCode(200).extract().response();
		logger.info("Getting Response for Post Method Using JSON");
		
	}

	 @Then("^Validate The Response Using JSON$")
	public void validate_the_response() {
		 logger.info("Vadliate Using JSON");
	    json = new JsonPath(response.asString());
	    String actualmsg = json.getString("message");
	    System.out.println(actualmsg);
	    String expected_msg = "Data Inserted Successfully";
	    Assert.assertEquals(expected_msg, actualmsg);
		
					
		
	}


}
