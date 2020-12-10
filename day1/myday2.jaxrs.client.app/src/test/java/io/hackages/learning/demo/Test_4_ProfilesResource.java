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
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class Test_4_ProfilesResource {

    @BeforeTest
    public void setUp () {
        RestAssured.baseURI = "http://localhost:8080/workshop.jaxrs.server.app";
    }

    @Test
    public void test_profiles_collection_resource_expect_status_code_ok() throws URISyntaxException {
        URI uri = new URI("/profiles");
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
                .body("size()", is(1))
                .and()
                .body("name", hasItem(is("ghost")))
        ;
    }

}
