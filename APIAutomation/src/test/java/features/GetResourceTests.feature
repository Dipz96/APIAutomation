Feature: Validation of Get Resources API
  
  
  Scenario: Validate Getting All Resources
  When call Resources API
  Then status code is 200

  Scenario: Validate Resource details using Get Resources API
  When call Resources API with id as "2"
  Then status code is 200
  And Name of user is "fuchsia rose", id is 2 and year is 2001
  

	Scenario: Validate Resource not found for Invalid Resource id
	When call Resources API with id as "0"
  Then status code is 404