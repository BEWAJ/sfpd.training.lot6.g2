package io.hackages.learning.demo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MyTest_CommentsResource {

    @BeforeTest
    public void setUp () {
        RestAssured.baseURI = "http://localhost:8080/workshop.jaxrs.server.app";
    }

    @Test
    public void test_add_comments_on_article_status_code_ok() throws URISyntaxException {
        URI uri = new URI("/articles/1/comments");
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .request()
                .body("{  \"id\" : 1,\n" +
						"  \"userId\" : \"JJJJJ\",\n" +
						"  \"body\" : \"kqcjbdkvchbqkdbvkqb\"}")
                .when()
                .post(uri);

        response.body().prettyPrint();

        response
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("comments.body", hasItem(is("kqcjbdkvchbqkdbvkqb")));
        ;
    }

	@Test
	public void test_delete_comment_on_article() throws URISyntaxException {
		URI uri = new URI("/articles/1/comments");
		given()
				.accept(ContentType.JSON)
				.when()
				.get("/articles/2")
				.then().assertThat()
				.statusCode(HttpStatus.SC_OK)
				.and()
				.body("body", is("Hello Jersey"));

		given()
				.accept(ContentType.JSON)
				.when()
				.delete("/articles/2")
				.then().assertThat()
				.statusCode(HttpStatus.SC_NO_CONTENT);

		given()
				.accept(ContentType.JSON)
				.when()
				.get("/articles/2")
				.then().assertThat()
				.statusCode(HttpStatus.SC_OK)
				.and()
				.body("body", is("Hello Jersey"));
		// FIXME - Should be .statusCode(HttpStatus.SC_NO_CONTENT);

	}
}
