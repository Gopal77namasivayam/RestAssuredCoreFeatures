package com.api.rest.PUT;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class NonBddApi {
	//prepare the Request
	//hit the API
	//get the response
	//fetch response body values
	// validate the values
	// no need static imports
	
	
	@Test
	public void getNonBddTest1()
	{
		RestAssured.baseURI="https://gorest.co.in";
		RequestSpecification request=RestAssured.given();
		request.contentType(ContentType.JSON);
		request.header("Authorization","Bearer a2662a77b08f58d659932dffe3cb92b732aeca5f27dabfb5de4dbb3f3f9de038");
		Response response=request.get("public-api/users/");
		System.out.println("statusCode:  "+response.getContentType());
		System.out.println("statuscode:	"+response.statusCode());
		System.out.println("Header:	"+response.header("Server"));
		System.out.println("pretty print :	"+response.prettyPrint());
		
	}
	
	@Test
	public void getNonBddTestwithQueryParam()
	{
		RestAssured.baseURI="https://gorest.co.in/";
		RequestSpecification request=RestAssured.given();
		request.contentType(ContentType.JSON);
		request.header("Authorization","Bearer a2662a77b08f58d659932dffe3cb92b732aeca5f27dabfb5de4dbb3f3f9de038");
		request.queryParam("name", "Siva");
		request.queryParam("gender", "Male");
		Response response=request.get("/public-api/users/");
		
		System.out.println("statusCode:  "+response.getContentType());
		System.out.println("statuscode:	"+response.statusCode());
		System.out.println("Header:	"+response.header("Server"));
		System.out.println("pretty print :	"+response.prettyPrint());
		
	}
	
	@Test
	public void getNonBddQueryWithHasMap()
	{
		RestAssured.baseURI="https://gorest.co.in/";
		RequestSpecification request=RestAssured.given();
		request.contentType(ContentType.JSON);
		request.header("Authorization","Bearer a2662a77b08f58d659932dffe3cb92b732aeca5f27dabfb5de4dbb3f3f9de038");
		Map<String,String> param=new HashMap<String,String>();//Hashmap class implements MapInterface and this is call top cast//stored null values//non syncronised means we can add multiple thread
		param.put("name", "siva");
		param.put("gender", "Male");
		request.queryParams(param);
		
		Response response=request.get("/public-api/users/");
		
		JsonPath js=response.jsonPath();
		System.out.println("meta info are: "+js.getString("meta"));
		System.out.println("Pages: "+js.getString("meta.pagination"));
		System.out.println("Page: "+js.getString("meta.pagination.page"));
		System.out.println("Total number of id: "+js.getString("meta.pagination.total"));
		System.out.println("limit is pages are: "+js.getString("meta.pagination.limit"));
		
		
		System.out.println("id is"+js.get("data[0].id"));
		System.out.println("name is"+js.get("data[0].name"));
		System.out.println("email is"+js.get("data[0].email"));
		System.out.println("gender is"+js.get("data[0].gender"));
		System.out.println("status"+js.get("data[0].status"));
		
		System.out.println("statusCode:  "+response.getContentType());
		System.out.println("statuscode:	"+response.statusCode());
		System.out.println("Header:	"+response.header("Server"));
		System.out.println("Cookies:	"+response.cookie(""));
		System.out.println("pretty print :	"+response.prettyPrint());
		Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.header("Server"), "nginx");
		
		
	}



}
