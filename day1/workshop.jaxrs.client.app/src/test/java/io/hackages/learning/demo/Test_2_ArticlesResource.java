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

public class Test_2_ArticlesResource {

    @BeforeTest
    public void setUp () {
        RestAssured.baseURI = "http://localhost:8080/workshop.jaxrs.server.app";
    }

    @Test
    public void test_articles_collection_resource_expect_status_code_ok() throws URISyntaxException {
        URI uri = new URI("/articles");
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get(uri);

        response.body().prettyPrint();

        response
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("size()", is(2))
                .and()
                .body("body", hasItem(is("Hello world")))
                .and()
                .body("body", hasItem(is("Hello Jersey")))
        ;
    }

    @Test(dataProvider = "instanceParams")
    public void test_articles_instance_resource_expect_status_code_ok(long id, String bodyToVerify) throws URISyntaxException {
        URI uri = new URI("/articles/" + id);
        Response response = given()
            .accept(ContentType.JSON)
            .when()
                .get(uri);

        response.body().prettyPrint();

        response.then().assertThat()
            .statusCode(HttpStatus.SC_OK)
            .and()
            .body("body", is(bodyToVerify));
    }

    @DataProvider
    public Object[][] instanceParams() {
        Object[][] testDatas = new Object[][] {
                new Object[] { 1, "Hello world" },
                new Object[] { 2, "Hello Jersey" } };
        return testDatas;
    }
}
