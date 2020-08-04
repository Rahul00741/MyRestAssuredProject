package com.StepDefinition;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.Assert;

import com.Base.BaseClass;
import com.UsingPojoClasses.GetUsing_GetPojo;
import com.UsingPojoClasses.PostUsing_PostPojo;
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
import io.restassured.specification.RequestSpecification;


public class Get_Steps extends BaseClass {

//	RequestSpecification httprequest;
	GetUsing_GetPojo get;
	Api_Resources method;
	Response response;
	JsonPath json;
	
	

	@Given("Initilise Base URI")
	
	public void initilise_base_uri() {
		
		RestAssured.baseURI="https://www.change2testautomation.com";
		
	    
	}

	@Then("Call The Pojo Class")
	public void call_the_pojo_class() {
	    
		get = new GetUsing_GetPojo();
	}

	
	@Then("Get The Response for GET Method")
	public void get_the_response_for_get_method() throws FileNotFoundException {
		
		method = Api_Resources.valueOf("GetResource");
		PrintStream log = new PrintStream(new File("./Log File/GeTLog.txt"));
		
		response = RestAssured.given()
							.queryParam("id", "33")
							.filters(RequestLoggingFilter.logRequestTo(log))
							.filters(ResponseLoggingFilter.logResponseTo(log))
							.when().get(method.getresource())
							.then().extract().response();
	    
	}

	@Then("Validate Response")
	public void validate_response() {
		
		//checkStatuscode
  		logger.info("  *******************    Checking Staus code   ***************************** ");
  		int statuscode=response.getStatusCode();
  		System.out.println("Status code is: " + statuscode);
//  		Assert.assertEquals(statuscode, 200);
  		
		//Check Responce Body
			logger.info("  ******************     Checking Respose Body   *****************************");
			String ResponseBody = response.getBody().asString();
			System.out.println("Responce Body is: "+ResponseBody);
//			Assert.assertTrue(ResponseBody!=null);
		
		//getallheaders
		logger.info("  *****************     Checking All headers    **********************");
		Headers allheaders = response.headers();
	    for (Header header:allheaders)
	    {
	    	System.out.println(header.getName()+"        " + header.getValue());
	 	 	   
	    }
	    
//	    //checkServer
//  		logger.info("  *****************  Checking Server   **************************");
//  		String server=response.header("Server");
//  		System.out.println("Server Type is : " +server);
//  		Assert.assertEquals(server, "nginx/1.17.6");
//  		Assert.assertTrue(server!=null);
//  		
//  		//checkContenttype
//  		logger.info("  ******************   Checking Content Type   ************************ ");
//  		String contenttype=response.header("Content-Type");
//  		System.out.println("Content Type is : " +contenttype);
//  		Assert.assertEquals(contenttype, "application/json; charset=UTF-8");
//  		
//  		//checkContentLength
//  		logger.info("  ******************   Checking Content Length   ************************ ");
//  		String contentlength=response.header("Content-Length");
//  		System.out.println("Content Type is : " +contentlength);
//  		Assert.assertTrue(contentlength!=null);
//  		
//  		//checkContentEncoding
//  		logger.info("  *******************    Checking Content Encoding   **************************");
//  		String contentencoding=response.header("Content-Encoding");
//  		System.out.println("Content Encoding is: " +contentencoding);
//  		Assert.assertTrue(contentencoding!=null);
  			    
	}
}
