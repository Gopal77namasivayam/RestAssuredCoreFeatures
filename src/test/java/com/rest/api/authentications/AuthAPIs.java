package com.rest.api.authentications;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
public class AuthAPIs {
	//Auth Type
	//Basic Auth username and pwd
	//Preemptive Auth username and pwd like basic auth but with more information
	//Digest Auth -username and password converted into hashcode, provide more secured connections
	//formbased// information come fromhtml page
	//OAuth1 - consumerKey,secretconsumerkey,Accesstoken and secret access token
	//OAuth2 -Client id,client secert,grant type and access token or url
	@Test
	//Preemptive 
	public void basicAuthApiTest1()
	{
		given().log().all()
		.auth()
		.preemptive()
		.basic("admin", "admin")
		.when().log().all()
		.get("https://the-internet.herokuapp.com/basic_auth")
		.then().log().all()
		.assertThat()
			.statusCode(200); 
		
	}
	
	//BasicAuth
	@Test
	//Preemptive 
	public void basicAuthApiTest4()
	{
		given().log().all()
		.auth()
		.basic("admin", "admin")
		.when().log().all()
		.get("https://the-internet.herokuapp.com/basic_auth")
		.then().log().all()
		.assertThat()
			.statusCode(200); 
		
	}
	// digest Auth
	@Test
	public void basicAuthApiTest2()
	{
		given().log().all()
		.auth()
		.digest("admin", "admin")
		.when().log().all()
		.get("https://the-internet.herokuapp.com/basic_auth")
		.then().log().all()
		.assertThat()
			.statusCode(200); 
		
	}
	//formbased
	@Test
	public void basicAuthApiTest5()
	{
		given().log().all()
		.auth()
		.form("batchautonation", "test@1233",new FormAuthConfig("https://classic.freecrm.com/system/authenticate.cfm","username","password"))
		.when().log().all()
		.get("https://classic.freecrm.com/system/authenticate.cfm")
		.then().log().all()
		.assertThat()
			.statusCode(200); 
		
	}
	// OAuth 2= bearer token - client id,client secret,grant type,access token
	@Test
	public void OAuth2_Api_Test1()
	{
		given().log().all()
		.auth()
			.preemptive()
			.oauth2("a2662a77b08f58d659932dffe3cb92b732aeca5f27dabfb5de4dbb3f3f9de038")
		.when().log().all()
			.get("https://gorest.co.in/public-api/users?name=gopal")
		.then().log().all()
			.assertThat()
			.statusCode(200);
		
	}
	
	@Test
	public void oAuth2ApiWithHeaderTest()
	{
		RestAssured.baseURI="https://gorest.co.in";
				
		given().log().all()
			.contentType("application/json")
			.header("Authorization", "Bearer a2662a77b08f58d659932dffe3cb92b732aeca5f27dabfb5de4dbb3f3f9de038")
			.param("gender", "male")
		.when().log().all()
		.get("/public-api/users")
		.then().log().all()
		.assertThat()
			.statusCode(200)
		.and()
			.header("Server", "nginx")
		.and()
			.header("Content-Type", "application/json; charset=utf-8");
		
	}
	
	@Test
	public void oAuth2APIWithQueryParam()
	{
		RestAssured.baseURI="https://gorest.co.in";
		given().log().all()
			.contentType("application/json")
			.header("Authorization", "Bearer a2662a77b08f58d659932dffe3cb92b732aeca5f27dabfb5de4dbb3f3f9de038")
			.queryParam("gender", "Male")
			.queryParam("name", "gopal")
			
		.when().log().all()
			.get("/public-api/users/")
		.then().log().all()
		.assertThat()
			.statusCode(200);
	}

}
//if you are using
//1.with header user Bearer with token
//2.with auth() method no need to add Bearer just add token