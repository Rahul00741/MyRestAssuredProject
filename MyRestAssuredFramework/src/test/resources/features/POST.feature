Feature: To Validate the Change2TestAutomation API

@TC_POST_Method
Scenario Outline: Validate the POST Method
Given Initilise Base URI and Base Path
Then Call the Pojo Class 
Then Get The Response for POST Method with "<body>" "<title>" and "<author>"
Then Validate The Response

Examples:
|body|title|author|
|API Test|Automation|Franklin Joshi|
|Rest Assured|Automation|Rahul Suryawanshi|
|Web Services|Automation|Virat|

@TC_POST_Method_UsingJSON
Scenario Outline: Validate the POST Method for JSON
Given Initilise Base URI and Base Path for JSON
Then Call the Pojo Class for JSON
Then Get The Response for POST Method for JSON with "<body>" "<title>" and "<author>"
Then Validate The Response Using JSON

Examples:

|body|title|author|
|API Test|Automation|Franklin Joshi|
|Rest Assured|Automation|Rahul Suryawanshi|
|Web Services|Automation|Virat|

@TC_Post_RequestSpecification_Method
Scenario: Validate POST Method Using Request Specification Approach
When Initilise The Base URI for Request Specification Approach
Then Get Response For POST Method using Request Specification Approach
Then Validate The Response Using Request Specification Approach

#@TC_Post_DataDrivern_Method
#Scenario: Validate POST Method Using Data Driven Approach
#When Initilise The Base URI
#Then Get Response For POST Method
#Then Validate The Response For Data Driver Approach

@TC_POST_HASHMAP_Method
Scenario: Validate POST Method Using HashMap Approach
When Initilise The Base URI for HashMap Approach
Then Get Response For POST Method for HashMap Approach
Then Validate The Response For HashMap Approach