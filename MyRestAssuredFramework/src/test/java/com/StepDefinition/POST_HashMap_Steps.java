package com.StepDefinition;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;


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

public class POST_HashMap_Steps extends BaseClass{
	
	RequestSpecification httprequest;
	Response response;
	JsonPath json;
	
	@When("Initilise The Base URI for HashMap Approach")
	public void initilise_the_base_uri_for_hash_map_approach() throws IOException {
		BaseSetup();
		
		
		RestAssured.baseURI = uri;
//		RestAssured.basePath="/api";
		logger.info("Inilizing Base URI");
	}

	@Then("Get Response For POST Method for HashMap Approach")
	public void get_response_for_post_method_for_hash_map_approach() throws FileNotFoundException {
		
		PrintStream log = new PrintStream(new File("./Log File/POST_HashMapLog.txt"));
		logger.info("Creating Object to Generate Log File");
		
		httprequest = RestAssured.given();
		
				    //JSONObject will be required to the body along with the post request
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("title", "value");
		map.put("body", "new things");
		map.put("author", "Online Training");
					
					//As we sending data in JSON format so Content type is application/json
					httprequest.header("Content-Type","application/json").filters(RequestLoggingFilter.logRequestTo(log))
				    .filters(ResponseLoggingFilter.logResponseTo(log)).log().all();
					
					//Add JSON data to body of The POST Request
					httprequest.body(map);
				
					
					//Send POST request
					response = httprequest.request(Method.POST,"/api/insert.php");
	}

	@Then("Validate The Response For HashMap Approach")
	public void validate_the_response_for_hash_map_approach() {

	    
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
		
				//checkStatuscode
				logger.info("  *******************    Checking Staus code   ***************************** ");
				int statuscode=response.getStatusCode();
				System.out.println("Status code is: " + statuscode);
				Assert.assertEquals(statuscode, 200);
				
				//checkStatusline
				logger.info("  ****************************   Checking Staus Line    ********************************* ");
				String statusline=response.statusLine();
				System.out.println("Status line is: " + statusline);
				Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
//				Assert.assertTrue(statusline!=null);
			    
			
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



