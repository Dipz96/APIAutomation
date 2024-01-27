package stepdefinations;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;


public class GetUserTestMethods {
	
	String baseURL="https://reqres.in/api/users";
	RequestSpecification reqspec;
	ResponseSpecification resspec;
	Response res;
	
	@When("call Users API, for Page {string}")
	public void call_users_api(String pageNo) {
				
	    reqspec=new RequestSpecBuilder().setBaseUri(baseURL).addQueryParam("page", pageNo).build();	
		resspec=new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		
		res=given().spec(reqspec).when().get().then().spec(resspec).extract().response();
	}
	
	@When("call Users API with user id as {string}")
	public void call_users_api_with_user_id_as(String id) {
				
	    reqspec=new RequestSpecBuilder().setBaseUri(baseURL).build();	
		resspec=new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		
		res=given().spec(reqspec).when().get("/"+id).then().spec(resspec).extract().response();
	}

	@Then("status code is {int}, first-name of user is {string} and email is {string}")
	public void status_code_is_first_name_of_user_is_and_email_is(int statusCode, String name, String email) {
		
			assertEquals(res.getStatusCode(), statusCode,"Status code is not as expected");
			String response=res.asString();
			JsonPath js=new JsonPath(response);
			String actualName=js.get("data.first_name");
			String actualEmail=js.get("data.email");
			assertEquals(actualName, name,"First Name is not as expected");
			assertEquals(actualEmail, email,"Email is not as expected");
			
	}
	
	
	@Then("status code is {int}")
	public void status_code_is(int statusCode) {
		
			assertEquals(res.getStatusCode(), statusCode,"Status code is not as expected");			
	}
}
