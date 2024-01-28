Feature: CRUD Operations using API

Scenario: Validate user Creation
	Given Name as "morpheus" and job as "leader"
  When Post Users API
  Then status code is 201 
  And Name is "morpheus" and job is "leader"
  And Store User id
  
  Scenario: Validate user updation using PUT
	Given Name as "murphy" and job as "president"
  When Update Users API using "PUT"
  Then status code is 200 
  And Name is "murphy" and job is "president"
  
  Scenario: Validate user updation using PATCH
	Given Name as "morpheus" and job as "leader"
  When Update Users API using "PATCH"
  Then status code is 200 
  And Name is "morpheus" and job is "leader"
  
  
  Scenario: Validate user deletion
  When Delete Users API
  Then status code is 204
  
