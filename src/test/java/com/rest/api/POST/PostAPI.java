package com.rest.api.POST;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.hamcrest.Matcher.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostAPI {
	
	@Test
	public void postCall_tokenString_Test()
	{
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		given().log().all()
			.contentType(ContentType.JSON)
			.body("{\"username\" : \"admin\", \"password\" : \"password123\"}")
		.when().log().all()
			.post("/auth")
		.then().log().all()
		.assertThat()
			.statusCode(200);
		
	}

	@Test
	public void postCall_token_File_Test()
	{
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		String tokenId=given().log().all()
			.contentType(ContentType.JSON)
			.body(new File("C:\\Users\\Namasivayam\\eclipse-workspace\\G\\src\\test\\java\\DataFiles\\credentils.json"))
		.when().log().all()
			.post("/auth")
		.then().log().all()
		.extract().path("token");
		System.out.println("token id is: "+tokenId);
		Assert.assertNotNull(tokenId);
		 
		
	}
	
	@Test
	public void postCall_JsonString_Test()
	{
		RestAssured.baseURI="https://gorest.co.in";
		given()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer a2662a77b08f58d659932dffe3cb92b732aeca5f27dabfb5de4dbb3f3f9de038")
		.body("{\r\n"
				+ "    \"name\": \"Asha Ayyappan2001ww \",\r\n"
				+ "    \"email\": \"ashaAyyiiiwwuua.stag12188@gmail.com\",\r\n"
				+ "    \"gender\": \"Male\",\r\n"
				+ "    \"status\": \"Active\"\r\n"
				+ "}")
		.when()
		.post("/public-api/users")
		.then().log().all()
		.assertThat()
		.body("data.gender",equalTo("Male"))
		.and()
		.body("data.status",equalTo("Active"));
	}
	@Test
	public void postCall_user_create_Test()
	{
//		RestAssured.baseURI="https://gorest.co.in";
//		given()
//		.contentType(ContentType.JSON)
//		.header("Authorization", "Bearer a2662a77b08f58d659932dffe3cb92b732aeca5f27dabfb5de4dbb3f3f9de038")
//		.body( new File("C:\\Users\\Namasivayam\\eclipse-workspace\\G\\src\\test\\java\\DataFiles\\user.json"))
//		.when()
//		.post("/public-api/users")
//		.then().log().all()
//		.assertThat()
//		.body("data.gender",equalTo("Male"))
//		.and()
//		.body("data.status",equalTo("Active"));
		
//		RestAssured.baseURI="https://gorest.co.in";
//		RequestSpecification request=
//		given().log().all()
//		.contentType(ContentType.JSON)
//		.header("Authorization", "Bearer a2662a77b08f58d659932dffe3cb92b732aeca5f27dabfb5de4dbb3f3f9de038")
//		.body(new File("C:\\Users\\Namasivayam\\eclipse-workspace\\G\\src\\test\\java\\DataFiles\\user.json"));
//		Response res=request.post("/public-api/users");
//		String status = res.jsonPath().getString("status");
//		String gender = res.jsonPath().getString("gender");
//		Assert.assertEquals(status, "Active");
//		Assert.assertEquals(gender, "Male");//		
//		System.out.println(status);
//		System.out.println(gender);
//		RestAssured.baseURI="https://gorest.co.in";
		RequestSpecification req = RestAssured
				.given().log().all()
				.contentType(ContentType.JSON)
				.header("Authorization", "Bearer a2662a77b08f58d659932dffe3cb92b732aeca5f27dabfb5de4dbb3f3f9de038")
				.body(new File("C:\\Users\\Namasivayam\\eclipse-workspace\\G\\src\\test\\java\\DataFiles\\user.json"));
				
		Response response = req.get("https://gorest.co.in/public-api/users");		 
		JsonPath jsonPathEvaluator = response.jsonPath();
		String status = jsonPathEvaluator.get("status");
		String gender = jsonPathEvaluator.get("gender");
	     System.out.println("status received from Response " + status);
		 
		 Assert.assertEquals(status, "Active", "Correct city name received in the Response");
		
	}
	
	
	
}
