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

import org.testng.Assert;

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
		String id= Utilities.getupdatedValues("UserID");
		if(httpMethod.equals("PUT"))
			res=given().spec(reqspec).when().put("/"+id).then().spec(resspec).log().all().extract().response();
		else
			res=given().spec(reqspec).when().patch("/"+id).then().spec(resspec).log().all().extract().response();
	}
	
	@When("Delete Users API")
	public void delete_users_api()
	{
		reqspec=new RequestSpecBuilder().setBaseUri(baseURL+"/users").build();	
		String id= Utilities.getupdatedValues("UserID");
		res=given().spec(reqspec).when().delete("/"+id).then().log().all().extract().response();
	}
	
	@When("call Resources API")
	public void call_resources_api() {
		 reqspec=new RequestSpecBuilder().setBaseUri(baseURL+"/unknown").build();	
	     resspec=new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
			
	     res=given().spec(reqspec).when().get().then().spec(resspec).extract().response();
	}

	@When("call Resources API with id as {string}")
	public void call_resources_api_with_id_as(String id) {
		reqspec=new RequestSpecBuilder().setBaseUri(baseURL+"/unknown/"+id).build();
	     resspec=new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
			
	     res=given().spec(reqspec).when().get().then().spec(resspec).extract().response();
	}

	@Then("Name of user is {string}, id is {int} and year is {int}")
	public void name_of_user_is_id_is_and_year_is(String name, int id, int year) {
		String response=res.asString();
		JsonPath js=new JsonPath(response);
		int actualID=js.getInt("data.id");
		String actualName=js.get("data.name");
		int actualYear=js.getInt("data.year");
		assertEquals(actualID, id,"ID is not as expected");
		assertEquals(actualName, name,"Name is not as expected");
		assertEquals(actualYear, year,"Year is not as expected");
	}

	
	@Given("Email as {string} and Password as {string}")
	public void email_as_and_password_as(String email, String pwd) {
		reqspec=new RequestSpecBuilder().setBaseUri(baseURL).setContentType(ContentType.JSON).setBody(Utilities.getRegisterJson(email,pwd)).build();	
		resspec=new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
    }

	@When("call Register API")
	public void call_register_api() {
		res=given().log().all().spec(reqspec).when().post("/register").then().log().all().spec(resspec).extract().response();
	}


	@Then("error as {string}")
	public void error_as(String errorMsg) {
		String response=res.asString();
		JsonPath js=new JsonPath(response);
		String actualError=js.getString("error");
		Assert.assertEquals(actualError, errorMsg,"Expected Error Message is not returned by API");
	}


	@When("call Login API")
	public void call_login_api() {

		res=given().log().all().spec(reqspec).when().post("/login").then().log().all().spec(resspec).extract().response();
	}

	

	
}
