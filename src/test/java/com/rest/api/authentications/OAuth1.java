package com.rest.api.authentications;

import static io.restassured.RestAssured.*;
import org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class OAuth1 {
	
	@Test
	public void getOAuthApiTest1()
	{
	
		RequestSpecification req= RestAssured.given().param("oauth_callback", "http:s//example.com");
		  
		 Response res=req.get("https://www.flickr.com/services/api/auth.oauth.html");
		 
		 res.jsonPath().getString("oauth_token");
		  
		 
		
		
		
		
	
	}

}
