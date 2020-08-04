package com.StepDefinition;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Base.BaseClass;
//import com.Utility.DataProvider;

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

public class POST_DataDriven_Step extends BaseClass{
	
	RequestSpecification httprequest;
	Response response;
	JsonPath json;
	
	@When("Initilise The Base URI")
	public void initilise_the_base_uri() throws IOException {
		BaseSetup();
		
		RestAssured.baseURI = uri;
		
		
	}
	
	@Then("Get Response For POST Method")
	
	@Test(dataProvider="newdataprovider")
	public void get_response_for_post_method(String title1, String body1,String author1) throws FileNotFoundException {
		
		
		{
			PrintStream log = new PrintStream(new File("./Log File/POST_DataDrivenLog.txt"));
			httprequest = RestAssured.given();
			
			//JSONObject will be required to the body along with the post request
			JSONObject requestParam = new JSONObject();
			requestParam.put("title", title1);
			requestParam.put("body", body1);
			requestParam.put("author", author1);
			
			//As we sending data in JSON format so Content type is application/json
			httprequest.header("Content-Type","application/json").filters(RequestLoggingFilter.logRequestTo(log))
		    .filters(ResponseLoggingFilter.logResponseTo(log)).log().all();
			
//			httprequest.header("Content-Type","application/json").log().all();
			
			//Add JSON data to body of The POST Request
			httprequest.body(requestParam.toString());
			
			//Send POST request
			response = httprequest.request(Method.POST,"/api/insert.php");}
			
			
	}
	
	@DataProvider 
	
	public String [] []newdataprovider()
	{
		String newData [][]= {{"API Resting","Using Eclise","Mark"},{"Rest Assured","Using PostMan","Ian"}};
		return (newData);
	}
	

	@Then("Validate The Response For Data Driver Approach")
	public void validate_the_response_for_data_driver_approach() {
		
}
}