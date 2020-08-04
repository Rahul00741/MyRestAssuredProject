package com.StepDefinition;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Base.BaseClass;
import com.Utility.ExcelUtils;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class POST_DataDrivenThroughExcel_Step extends BaseClass{
	
	RequestSpecification httprequest;
	Response response;
	JsonPath json;
	
	
	@When("Initilise The Base URI for Data Driven Approach Through Excel")
	public void initilise_the_base_uri_for_data_driven_approach_through_excel() throws IOException {
		
		BaseSetup();
		
		RestAssured.baseURI = uri;
		
	    
	}

	@Then("Get Response For POST Method using Data Driven Approach Through Excel")
	@Test(dataProvider="newdataprovider")
	public void get_response_for_post_method_using_data_driven_approach_through_excel(String title1, String body1,String author1) throws FileNotFoundException {
	    
		 
			
			PrintStream log = new PrintStream(new File("./Log File/POST_DataDriverThroughExcel.txt"));
			httprequest = RestAssured.given();
			
			//JSONObject will be required to the body along with the post request
			JSONObject requestParam = new JSONObject();
			requestParam.put("title", title1);
			requestParam.put("body", body1);
			requestParam.put("author", author1);
			
			//As we sending data in JSON format so Content type is application/json
			httprequest.header("Content-Type","application/json").filters(RequestLoggingFilter.logRequestTo(log))
		    .filters(ResponseLoggingFilter.logResponseTo(log));
			
			//Add JSON data to body of The POST Request
			httprequest.body(requestParam.toString());
			
			//Send POST request
			response = httprequest.request(Method.POST,"/api/insert.php");
		}
			
		 	@DataProvider(name="newdataprovider")
			
			
			String [] []getNewData() throws IOException
			{
				//Read data from excel
				
				String path = System.getProperty("user.dir")+"/src/main/java/com/Utility/example.xls";
				
				int rownum = ExcelUtils.gerRowCount(path, "Sheet1");
				int colnum = ExcelUtils.getCellCount(path, "Sheet1", 1);
				
				String newData [] [] = new String [rownum][colnum];
				for(int i=1; i <=rownum; i++) {
					for(int j= 0; j<colnum; j++) {
						newData [i-1][j]= ExcelUtils.getCellData(path, "Sheet1", i, j);
					}
				}
				
//				String newData [][]= {{"API Resting","Using Eclise" ,"Mark"},{"Rest Assured","Using PostMan","Ian"}};
				return (newData);
			}
			
		    
	}


