
Feature: Validation of Get Users API
  
  
  Scenario: Validate Getting All Users as per Page Number
  When call Users API, for Page "1"
  Then status code is 200

  Scenario: Validate User details using Get Users API
  When call Users API with user id as "2"
  Then status code is 200, first-name of user is "Janet" and email is "janet.weaver@reqres.in"
  

	Scenario: Validate User not found for Invalid user
	When call Users API with user id as "0"
  Then status code is 404