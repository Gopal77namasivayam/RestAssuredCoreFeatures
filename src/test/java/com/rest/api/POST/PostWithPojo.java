package com.rest.api.POST;

import org.apache.tools.ant.types.Mapper;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PostWithPojo {
	
	//Create User
	//post url
	// request json body
	// user java class[pojo]---convert this into json object
	//encapsulation -- private varaible--public(getter and setters)
	//POJO -PlainOldJavaObject--private varaible--public(getter and setters)
	//POJO - converting java oblect in to json
	
	@Test
	public void createUserWithPojoTest() throws JsonProcessingException
	{
	//create POJO class
	Users users=new Users("Aasha Gopal","ashagopal200@gmail.com","Female","Active");
	
	//convert a POJO Object into Json----called serialation of data---use JACKSON api for this
	//String usersJson=null;
	ObjectMapper mapper=new ObjectMapper();
	String usersJson=mapper.writeValueAsString(users);
	System.out.println(usersJson);
	
	//RestAssured.baseURI="https://gorest.co.in";
	given().log().all()
	.contentType(ContentType.JSON)
	.header("Authorization","Bearer a2662a77b08f58d659932dffe3cb92b732aeca5f27dabfb5de4dbb3f3f9de038")
	.body(usersJson)
	.when().log().all()
	.post("https://gorest.co.in/public-api/users")
	.then().log().all()
	.assertThat()
	.statusCode(200)
	.and()
	.contentType(ContentType.JSON)
	.and()
	.body("data.name",  equalTo(users.getName()))
	 
	.body("data.email",  equalTo(users.getEmail()))
	 
	.body("data.gender",  equalTo(users.getGender()))
	 
	.body("data.status",  equalTo(users.getStatus()));
	
	}
	
	
	
	
	
}
