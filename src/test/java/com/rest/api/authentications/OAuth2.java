package com.rest.api.authentications;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Assert;
import org.junit.runner.Request;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class OAuth2 {
	@Test
	public void OAuth2APITEst()
	{
		RequestSpecification request =RestAssured.given().auth().oauth2("b2135d9eaf8ec13d302c8bbec597f4f1d5e1c824");
		Response response=request.post("http://coop.apps.symfonycasts.com/api/1558/chickens-feed");
		int stcode=response.getStatusCode();
		response.prettyPrint();
		Assert.assertEquals(stcode, 200);
	}
	//Generate the token at run time by using token API
	//String tokenId from the response
	//hit the next API with this token
	
	@Test
	public void OAuth2GetToken()
	{
		RequestSpecification request1=
				RestAssured.given().log().all()
				.formParam("client_id", "GopalOuath2")
				.formParam("client_secret", "aa5ff1d4b0ed1c5f6736191b9ac6663a")
				.formParam("grant_type", "client_credentials");
		Response response1=request1.post("http://coop.apps.symfonycasts.com/token");
		
//		Response response1= given()				 
//				.formParam("client_id", "GopalOuath2")
//				.formParam("client_secret", "aa5ff1d4b0ed1c5f6736191b9ac6663a")
//				.formParam("grant_type", "client_credentials").post("http://coop.apps.symfonycasts.com/token");
		System.out.println(response1.statusCode());
		System.out.println(response1.prettyPrint());
		
		
		String AccessToken=response1.jsonPath().getString("access_token");
		System.out.println("token is: "+AccessToken);
		// feed the chicken API
		
		RequestSpecification req=given().auth().oauth2(AccessToken);
		Response res=req.post("http://coop.apps.symfonycasts.com/api/1558/chickens-feed");
		System.out.println("response code is"+res.statusCode());		
		System.out.println("response code is"+res.prettyPrint());
		
		
		
		 
	}

}
