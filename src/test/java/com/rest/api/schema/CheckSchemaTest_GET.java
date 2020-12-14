package com.rest.api.schema;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
public class CheckSchemaTest_GET {
	
	@Test
	public void getSchemaTest()
	{
		given().log().all()
		.contentType(ContentType.JSON)
		.queryParam("name", "gopal")
		.queryParam("status", "Active")
		.queryParam("gender", "Male")
		.headers("Authorization","Bearer a2662a77b08f58d659932dffe3cb92b732aeca5f27dabfb5de4dbb3f3f9de038")
		.when().log().all()
		.get("https://gorest.co.in/public-api/users")
		.then()
		.assertThat()
		.statusCode(200)
		.and()
		.body(matchesJsonSchemaInClasspath("getuser_gorest_queryparam_Schema.json"));
		
	}
	
	

}
