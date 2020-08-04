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

public class Post_Step extends BaseClass{
	
	PostUsing_PostPojo post;
	Api_Resources method;
	Response response;
	JsonPath json;
	
	@Given("Initilise Base URI and Base Path")
	public void initilise_base_uri_and_base_path() throws IOException {
		BaseSetup();
		
		
		RestAssured.baseURI = uri;
//		RestAssured.basePath="/api";
		logger.info("Inilizing Base URI");
	}

	@Then("Call the Pojo Class")
	public void call_the_pojo_class() {
		
		post = new PostUsing_PostPojo();
		logger.info("Calling The Pojo Class");
	    
	}

	@Then("Get The Response for POST Method with {string} {string} and {string}")
	public void get_the_response_for_post_method_with_and(String body, String title, String author) throws FileNotFoundException {
	    
		method = Api_Resources.valueOf("PostResource");
		logger.info("Calling API_Resource for PostResource");
		PrintStream log = new PrintStream(new File("./Log File/POSTLog.txt"));
		logger.info("Creating Object to Generate Log File");
		
		response = RestAssured.given()
				    .filters(RequestLoggingFilter.logRequestTo(log))
				    .filters(ResponseLoggingFilter.logResponseTo(log))
					.body(post.PostMethod(body,title,author))
					.when()
					.post(method.getresource())
					.then().statusCode(200).extract().response();
		logger.info("Getting Response for Post Method");
		
	}

	@Then
	("Validate The Response")
	public void validate_the_response() {
		
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
//		
//	
		//checkStatusline
		logger.info("  ****************************   Checking Staus Line    ********************************* ");
		String statusline=response.statusLine();
		System.out.println("Status line is: " + statusline);
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
//		Assert.assertTrue(statusline!=null);
	    
	
		//checkContentEncoding
		logger.info("  *******************    Checking Content Encoding   **************************");
		String contentencoding=response.header("Content-Encoding");
		System.out.println("Content Encoding is: " +contentencoding);
//		
		//checkContenttype
		logger.info("  ******************   Checking Content Type   ************************ ");
		String contenttype=response.header("Content-Type");
		System.out.println("Content Type is : " +contenttype);
		Assert.assertEquals(contenttype, "application/json; charset=UTF-8");
		
		//checkServertype

		logger.info("  *****************  Checking Server type  **************************");
		String servertype=response.header("Server");
		System.out.println("Server Type is : " +servertype);
		Assert.assertEquals(servertype, "Apache");
//		
		//checkcookies
		logger.info("  ****************   Checking Cookies    *************************");
		String cookies=response.getCookie("PHPSESSID");
		System.out.println("Cookies Are: " +cookies);
//		
		//checkResponsetime
		logger.info("  *****************    Checking response time    *********************");
		long responsetime = response.getTime();
		System.out.println("Response Times is : " +responsetime);
		

	    
	
	}	
		
	}



