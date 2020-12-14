package com.api.rest.PUT;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.api.POST.Users;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PutAPI  {
	
	//sequence - create the user with post API
	// update user data with  put API
	//  verify all the user data with get api
	
	@Test
	public void createUserWithPojoTest11() throws JsonProcessingException
	{
	//create POJO class
	Users users=new Users("Aasha Gopal","ashagopalll@gmail.com","Female","Active");
	
	//convert a POJO Object into Json----called serialation of data---use JACKSON api for this
	//String usersJson=null;
	ObjectMapper mapper=new ObjectMapper();
	String usersJson=mapper.writeValueAsString(users);
	System.out.println(usersJson);
	
	RestAssured.baseURI="https://gorest.co.in";
	Object userid=given().log().all()
	.contentType(ContentType.JSON)
	.header("Authorization","Bearer a2662a77b08f58d659932dffe3cb92b732aeca5f27dabfb5de4dbb3f3f9de038")
	.body(usersJson)
	.when().log().all()
	.post("/public-api/users")
	.then().log().all()
	.assertThat()
	.statusCode(200)
	.and()
	.contentType(ContentType.JSON)
	.and()
	.body("data.name",equalTo(users.getName()))
	.extract().path("data.id");
	System.out.println("userId"+userid);
	
	//Update the user email and make the put call
	
	
	users.setName("SivaG");
	users.setEmail("siva1111@gmail.com");
	users.setGender("Male");
	users.setStatus("Inactive");
	
	//String updateduser=null;
	String updateduser=mapper.writeValueAsString(users);
	
	
	given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization","Bearer a2662a77b08f58d659932dffe3cb92b732aeca5f27dabfb5de4dbb3f3f9de038")
		.body(updateduser)
	.when().given()
		.put("/public-api/users/"+userid)
	.then().log().all()
		.assertThat()
			.contentType(ContentType.JSON)
			.body("data.email", equalTo(users.getEmail()))
	.and()
			.body("data.id",equalTo(userid))
	.and()
			.body("data.name",equalTo(users.getName()))
	.and()
		.body("data.status",equalTo(users.getStatus()))
	.and()
		.body("data.gender",equalTo(users.getGender()));
	
	
	given().log().all()
	.contentType(ContentType.JSON)
	.header("Authorization","Bearer a2662a77b08f58d659932dffe3cb92b732aeca5f27dabfb5de4dbb3f3f9de038")
	 .when().given()
	.get("/public-api/users/"+userid)
.then().log().all()
	.assertThat()
		.contentType(ContentType.JSON)
		.and()
		.body("data.email", equalTo(users.getEmail()))
.and()
		.body("data.id",equalTo(userid))
.and()
		.body("data.name",equalTo(users.getName()))
.and()
	.body("data.status",equalTo(users.getStatus()))
.and()
	.body("data.gender",equalTo(users.getGender()));

	
	
	
	
	}
	
	
	
	
	
}
