package stepdefinations;

import io.cucumber.java.en.Given;
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
import APIUtilities.Utilities;


public class TestMethods {
	
	String baseURL="https://reqres.in/api";
	RequestSpecification reqspec;
	ResponseSpecification resspec;
	Response res;
	
	@When("call Users API, for Page {string}")
	public void call_users_api(String pageNo) {
				
	    reqspec=new RequestSpecBuilder().setBaseUri(baseURL+"/users").addQueryParam("page", pageNo).build();	
		resspec=new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		
		res=given().spec(reqspec).when().get().then().spec(resspec).extract().response();
	}
	
	@When("call Users API with user id as {string}")
	public void call_users_api_with_user_id_as(String id) {
				
	    reqspec=new RequestSpecBuilder().setBaseUri(baseURL+"/users").build();	
		resspec=new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		
		res=given().spec(reqspec).when().get("/"+id).then().spec(resspec).extract().response();
	}

	@Then("first-name of user is {string} and email is {string}")
	public void status_code_is_first_name_of_user_is_and_email_is(String name, String email) {
		
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
	
	@Then("Store User id")
	public void store_user_id() {
		String response=res.asString();
		JsonPath js=new JsonPath(response);
		String id=js.get("id");
		Utilities.updateVariables(id);
	}
	
	@Then("Name is {string} and job is {string}")
	public void name_is_and_job_is(String name, String job) {
		String response=res.asString();
		JsonPath js=new JsonPath(response);
		String actualName=js.get("name");
		String actualJob=js.get("job");
		assertEquals(actualName, name,"Name is not as expected");
		assertEquals(actualJob, job,"Job is not as expected");
	}
	
	@Given("Name as {string} and job as {string}")
	public void name_as_and_job_as(String name, String job) {
		reqspec=new RequestSpecBuilder().setBaseUri(baseURL+"/users").setContentType("application/json").setBody(Utilities.getUserJson(name,job)).build();	
		resspec=new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
	}

	@When("Post Users API")
	public void post_users_api() {
		res=given().spec(reqspec).when().post().then().spec(resspec).log().all().extract().response();
	}
	
	@When("Update Users API using {string}")
	public void update_users_api(String httpMethod) {
		if(httpMethod.equals("PUT"))
			res=given().spec(reqspec).when().put().then().spec(resspec).log().all().extract().response();
		else
			res=given().spec(reqspec).when().patch().then().spec(resspec).log().all().extract().response();
	}
	
	@When("Delete Users API")
	public void delete_users_api()
	{
		reqspec=new RequestSpecBuilder().setBaseUri(baseURL+"/users").build();	
		String id= Utilities.getupdatedValues("UserID");
		res=given().spec(reqspec).when().delete("/"+id).then().log().all().extract().response();
	}
	
	

	
}
