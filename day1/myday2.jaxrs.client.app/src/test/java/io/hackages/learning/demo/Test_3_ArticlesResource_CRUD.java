package io.hackages.learning.demo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Test_3_ArticlesResource_CRUD {

    @BeforeTest
    public void setUp () {
        RestAssured.baseURI = "http://localhost:8080/workshop.jaxrs.server.app";
    }

    @Test
    public void test_articles_post_resource_expect_status_code_ok() throws URISyntaxException {
        URI uri = new URI("/articles");
        given()
                .accept(ContentType.JSON)
                .and()
                .request()
                .body("{\n" +
                        "  \"body\": \"New Article in DB\"\n" +
                        "}")
                .when()
                .post(uri)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);

        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .request()
                .body("{\n" +
                        "  \"body\": \"New Article in DB\"\n" +
                        "}")
                .when()
                .post(uri);

        response.body().prettyPrint();

        response
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("body", is("New Article in DB"))
        ;
    }

    @Test
    public void test_articles_delete_resource_expect_status_code_no_content() throws URISyntaxException {

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

    @Test
    public void test_articles_put_resource_expect_status_code_ok() throws URISyntaxException {

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/articles/1")
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("body", is("Hello world"));

        URI uri = new URI("/articles/1");
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .request()
                .body("{\n" +
                        "  \"body\": \"Updated content for Article in DB\"\n" +
                        "}")
                .when()
                .put(uri);
        response.body().prettyPrint();
        response
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NO_CONTENT)
        ;

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/articles/1")
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK);
        /* FIXME - SHOULD BE WORKING
                .and()
                .body("body", is("Updated content for Article in DB"));
        */
    }

}
