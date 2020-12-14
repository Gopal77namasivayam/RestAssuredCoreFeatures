package com.rest.api.DELETE;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.api.POST.Users;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
public class DeleteAPIs {
	@Test
	public void delateApiTest() throws JsonProcessingException
	{
	Users user= new Users("Aasha Gopal","ashssdsdds@gmail.com","Female","Active");
	ObjectMapper mapper = new ObjectMapper();
	String userJson=mapper.writeValueAsString(user);
	System.out.println("Converted Json from POJO is: "+userJson);
	RestAssured.baseURI="https://gorest.co.in";
	Object userID=given().log().all()
	.contentType(ContentType.JSON)
		.header("Authorization","Bearer a2662a77b08f58d659932dffe3cb92b732aeca5f27dabfb5de4dbb3f3f9de038")
		.body(userJson)
	.when().log().all()
	.post("/public-api/users")
	.then().log().all()
	.assertThat()
	.contentType(ContentType.JSON)
	.extract().path("data.id");
	System.out.println("userID is: "+userID);
	
	//delate the created user
	given().log().all()
	.contentType(ContentType.JSON)
		.header("Authorization","Bearer a2662a77b08f58d659932dffe3cb92b732aeca5f27dabfb5de4dbb3f3f9de038")
		.body(userJson)
	.when().log().all()
	.delete("/public-api/users/"+userID)
	.then().log().all()
	.assertThat()
	.contentType(ContentType.JSON)
	.and()
	.body("data", equalTo(null));
	
			
	}
	

}
