package com.rest.api.XML;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import XmlParserr.XmlParser;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
public class GetResponseXML {
	@Test
	public void getResponseXML()
	{
		RestAssured.baseURI="https://gorest.co.in";
		Response response=given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization","Bearer a2662a77b08f58d659932dffe3cb92b732aeca5f27dabfb5de4dbb3f3f9de038")
		.header("Accept","APPLICATION/XML")
		.when().log().all()
		.get("/public-api/users.xml?name=Balagopal Khan");
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
		String Xmlresponsecontent=response.prettyPrint();
		XmlParser xp= new XmlParser(Xmlresponsecontent);
		String id=xp.getTextContent("//data//id");
		String name=xp.getTextContent("//data//name");
		String email=xp.getTextContent("//data//email");
		String gender=xp.getTextContent("//data//gender");
		String status=xp.getTextContent("//data//status");
		System.out.println(id);
		System.out.println(name);
		 
		Assert.assertEquals(id, "93");
		Assert.assertEquals(name, "Balagopal Khan");
		Assert.assertEquals(email, "khan_balagopal@hamill-witting.net");
		Assert.assertEquals(gender, "Female");
		Assert.assertEquals(status, "Inactive");
		
		
		
	}

}