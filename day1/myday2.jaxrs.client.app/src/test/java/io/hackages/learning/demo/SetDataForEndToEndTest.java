package io.hackages.learning.demo;

import static io.restassured.RestAssured.given;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public abstract class SetDataForEndToEndTest {

	@BeforeTest
	public void setUp () {
		RestAssured.baseURI = "http://localhost:8080/myday2.jaxrs.server.app";
	}

	@BeforeMethod
	public void resetDB() {
		given()
				.accept(ContentType.JSON)
				.when()
				.get("/management/reset");
		getDefaultArticles().forEach(this::insertArticle);
	}

	public List<String> getDefaultArticles() {
		return Arrays.asList("{\n" +
						"\t\t\t\"body\" : \"New Article 1 in DB\"\n" +
						"\t\t}",
				"{\n" +
						"\t\t\t\"body\" : \"New Article 2 in DB\"\n" +
						"\t\t}",
				"{\n" +
						"\t\t\t\"body\" : \"New Article 3 in DB\"\n" +
						"\t\t}");


	}

	private void insertArticle(String article){
		given().contentType(ContentType.JSON)
				.body(article)
				.when()
				.post("/articles");
	}
}
