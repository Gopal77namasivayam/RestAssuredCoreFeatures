package com.rest.api.schema;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.File;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
public class CheckSchemaTest_POST {
	
	@Test
	public void booking_Schema_Test()
	{
		//RestAssured.baseURI="https://gorest.co.in/";
		given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer a2662a77b08f58d659932dffe3cb92b732aeca5f27dabfb5de4dbb3f3f9de038")			
			.body(new File("C:\\Users\\Namasivayam\\eclipse-workspace\\G\\src\\test\\ressources\\BookingJson.json"))
		.when().log().all()		
			.post("https://gorest.co.in/public-api/users")
		.then().log().all()
			.assertThat()
			.statusCode(200)
			.and()
			.body(matchesJsonSchemaInClasspath("BookingSchema.json"));
	}

}
