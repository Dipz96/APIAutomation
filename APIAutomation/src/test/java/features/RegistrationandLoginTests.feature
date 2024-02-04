
Feature: Validation of Registration and Login of Users using API
  
  Scenario: Validate Registration of User without password
  Given Email as "eve.holt@reqres.in" and Password as ""
  When call Register API
  Then status code is 400
  And error as "Missing password"
  
  Scenario: Validate Registration of User
  Given Email as "eve.holt@reqres.in" and Password as "abc@123"
  When call Register API
  Then status code is 200
  
  
	Scenario: Validate Login of User
  Given Email as "eve.holt@reqres.in" and Password as "abc@123"
  When call Login API
  Then status code is 200
  
 	Scenario: Validate Login of User without password
 	Given Email as "eve.holt@reqres.in" and Password as ""
  When call Login API
  Then status code is 400
  And error as "Missing password"

