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

@TC_GET_Method
Scenario: Validate the GET Method
Given Initilise Base URI 
Then Call The Pojo Class
Then Get The Response for GET Method
Then Validate Response

@TC_PUT_Method
Scenario: Validate The PUT Method
Given Initilise URI for PUT Method
Then Call The Pojo Class for PUT Method
Then Get the Response for PUT method
Then Validate PUT Response

@TC_DELETE_Method
Scenario: Validate The DELETE Method
Given Initilise URI for DELETE Method
Then Call The Pojo Class for DELETE Method
Then Get the Response for DELETE Method 
Then Validate DELETE Response



