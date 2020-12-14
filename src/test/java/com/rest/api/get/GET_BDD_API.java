package com.rest.api.get;

//  for rest assured Manual import is required hence you need to type it manually

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//rest assured BDD keywords
//given()
//when()
//then()
//and()

public class GET_BDD_API {

	@Test
	public void getAPITCirciuitest1() {
		given().log().all().when().log().all().get("http://ergast.com/api/f1/2017/circuits.json").then().log().all()
				.assertThat().body("MRData.CircuitTable.Circuits.circuitId", hasSize(20));

	}

	@Test
	public void getAPITCirciuitest2() {
		Response response = given().log().all().when().log().all().get("http://ergast.com/api/f1/2017/circuits.json");
		int statuscode = response.getStatusCode();
		System.out.println("response code is: " + statuscode);
		Assert.assertEquals(statuscode, 200);
		response.prettyPrint();
	}

//	@Test
//	public void getAPITCirciuitest3() {
//		RestAssured.baseURI = "http://ergast.com";
//		given().log().all()
//		.when().log().all()
//		.get("/api/f1/2017/circuits.json")
//		.then().log().all()
//		.assertThat()
//		.statusCode(200)
//		.and()
//			.contentType(ContentType.JSON)
//		.and()
//			.header("Content-Length", equalTo("4551"));
//	}
//	
	@Test
	public void getJsonApitest1()
	{
		given().log().all()
		.param("text", "test")
		.when().log().all()
		.get("http://md5.jsontest.com")   //http://md5.jsontest.com?text=test
		.then().log().all()
		.assertThat()
		.body("md5",equalTo("098f6bcd4621d373cade4e832627b4f6"));
		
		
		

	}
	
	@DataProvider(name="getCircuitYear")
	
	public Object[][] getCitcuitYearInfo()
	{
		 return new Object[][]
				{
			{"2017",20},
			{"2016",21},
			{"1966",9}
				};
	}
	
	@Test(dataProvider="getCircuitYear")
	public void getcircutePerYear(String Seasonyear,int circuitcount)
	{
		given().log().all()
		.pathParam("raceseason", Seasonyear)
		.when().log().all()
		.get("http://ergast.com/api/f1/{raceseason}/circuits.json")
		.then()
		.assertThat()
		.body("MRData.CircuitTable.Circuits.circuitId", hasSize(circuitcount));
	}
	

}
