package io.hackages.learning.demo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Test_2_Atttachment extends SetDataForEndToEndTest {

	@Test
    public void test_articles_with_attachment() throws URISyntaxException {
        URI uri = new URI("/attachment/1");
        Response response = given()
				.auth()
				.preemptive()
				.basic("Test", "password")
            .accept(ContentType.BINARY)
            .when()
                .get(uri);

        response.body().prettyPrint();

        response.then().assertThat()
            .statusCode(HttpStatus.SC_OK)
            .and()
            .body("body", is("New Article 1 in DB"));
    }


}
