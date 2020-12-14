package com.rest.api.ResponseBuilder;

import static io.restassured.RestAssured.*;
import static io.restassured.builder.ResponseSpecBuilder.*;
import static io.restassured.http.ContentType.*;
import static io.restassured.specification.ResponseSpecification.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher.*;

public class ResponsesSpecBuilderTest {
	
	ResponseSpecBuilder res =new ResponseSpecBuilder();
	ResponseSpecification Resspec=res
			.expectContentType(ContentType.JSON)
			.expectStatusCode(200)
			.expectHeader("Server", "nginx")
			.build();
	
	ResponseSpecification BadReq404=res
			.expectContentType(ContentType.HTML)
			.expectStatusCode(404)
			.expectHeader("Server", "nginx")
			.build();
	
	@Test
	public void responseSpecTest()
	{
		RestAssured.baseURI="https://gorest.co.in";
		 
		given().log().all()
		.contentType("application/json")
		.header("Authorization", "a2662a77b08f58d659932dffe3cb92b732aeca5f27dabfb5de4dbb3f3f9de038")
		
		.when()
		.get("/public-api/users")
		.then().log().all()
		.assertThat()
		.spec(Resspec);
		
	}

	@Test
	public void BadReqTest()
	{
		RestAssured.baseURI="https://gorest.co.in";
		 
		given().log().all()
		.contentType("application/json")
		.header("Authorization", "a2662a77b08f58d659932dffe3cb92b732aeca5f27dabfb5de4dbb3f3f9de038")
		
		.when()
		.get("/public-api/users&&&&&")
		.then().log().all()
		.assertThat()
		.spec(BadReq404);
		
	}
			

}
