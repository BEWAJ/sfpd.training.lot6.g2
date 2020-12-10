package io.hackages.learning.demo;

import be.sfpd.blog.model.Article;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;


import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class MyLocalTest {

    @Test
    public void test_endpoint_name_expect_status_code_ok() throws URISyntaxException {
        /*
         * Given Accept the response in JSON format
         * When I perform the GET request
         * Then Assert That the status code is OK
         */
        URI uri = new URI("http://localhost:8080/myjaxrs.server.app/webapi/myresource");
        given()
            .accept(ContentType.TEXT)
        .when()
            .get(uri)
        .then()
            .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

	@Test
	public void test_get_expect_hello_world() throws URISyntaxException {

		URI uri = new URI("http://localhost:8080/myjaxrs.server.app/webapi/myresource");
		given()
				.accept(ContentType.TEXT)
				.when()
				.get("/myresource")
				.then()
				.assertThat()
				.equals("Hello World!");
	}

	@Test
	public void test_endpoint_articles_expect_status_code_ok() throws URISyntaxException {

		URI uri = new URI("http://localhost:8080/myjaxrs.server.app/webapi/articles");
		given()
				.accept(ContentType.JSON) //ContentType.XML
				.when()
				.get(uri)
				.then()
				.assertThat()
				.statusCode(HttpStatus.SC_OK);
	}

	@Test
	public void test_get_all_articles() throws URISyntaxException {

		URI uri = new URI("http://localhost:8080/myjaxrs.server.app/webapi/articles");
		/*XML
		given()
				.accept(ContentType.XML)
				.when()
				.get(uri)
				.then()
				.assertThat()
				.body("articles.article.size()", is(2))
				.and()
				.body("articles.article.body", hasItem(is("Hello world")));
		*/
		Response response = given()
				.accept(ContentType.JSON)
				.when()
				.get(uri);
		System.out.println(response.body().prettyPrint());

		response
				.then()
				.assertThat()
				.body("", hasSize(2))
				.and()
				.body("body", hasItem("Hello world"));
	}

	@Test
	public void test_get_article() throws URISyntaxException {

		URI uri = new URI("http://localhost:8080/myjaxrs.server.app/webapi/articles/1");
		/*
		given()
				.accept(ContentType.XML)
				.when()
				.get(uri)
				.then()
				.assertThat()
				.body("article.body", is("Hello world"));
		 */
		given()
				.accept(ContentType.JSON)
				.when()
				.get(uri)
				.then()
				.assertThat()
				.body("body", is("Hello world"));

	}

	@Test
	public void test_add_article() throws URISyntaxException {

		URI uri = new URI("http://localhost:8080/myjaxrs.server.app/webapi/articles/add");
		given()
				.accept(ContentType.TEXT)
				.and()
				.contentType(ContentType.JSON)
				.and()
				.request()
				.body("{\n" +
						"  \"body\": \"test\"\n" +
						"}")
				.when()
				.post(uri)
				.then()
				.statusCode(200)
				.and()
				.body(is("article added"));

		uri = new URI("http://localhost:8080/myjaxrs.server.app/webapi/articles");
		given()
				.accept(ContentType.JSON)
				.when()
				.get(uri)
				.then()
				.assertThat()
				.body("", hasSize(3));

	}

	@Test
	public void test_update_article() throws URISyntaxException {

    	// Before update

		URI getUri = new URI("http://localhost:8080/myjaxrs.server.app/webapi/articles/1");
		given()
				.accept(ContentType.JSON)
				.when()
				.get(getUri)
				.then()
				.assertThat()
				.body("body", is("Hello world"));

		// Update
		URI uri = new URI("http://localhost:8080/myjaxrs.server.app/webapi/articles/update");
		Response putRequest = given()
				.accept(ContentType.TEXT)
				.and()
				.contentType(ContentType.JSON)
				.and()
				.request()
				.body("{\n" +
						"  \"id\" : 1,\n" +
						"  \"body\" : \"kqcjbdkvchbqkdbvkqb\"\n" +
						"}")
				.when()
				.put(uri);


		putRequest
				.then()
				.statusCode(200)
				.and()
				.body(is("article updated"));

		// After Update

		given()
				.accept(ContentType.JSON)
				.when()
				.get(getUri)
				.then()
				.assertThat()
				.body("body", is("kqcjbdkvchbqkdbvkqb"));


	}

}
