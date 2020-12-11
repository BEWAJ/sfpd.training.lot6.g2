package io.hackages.learning.demo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Test_2_Articles extends SetDataForEndToEndTest {


	@Test
    public void test_articles_collection_resource_expect_status_code_ok() throws URISyntaxException {
        URI uri = new URI("/articles");
        Response response = given()
				.auth()
				.preemptive()
				.basic("Test", "password")
                .accept(ContentType.JSON)
                .when()
                .get(uri);

        response.body().prettyPrint();

        response
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_ACCEPTED)
                .and()
                .body("size()", is(3))
                .and()
                .body("body", hasItem(is("New Article 1 in DB")))
                .and()
                .body("body", hasItem(is("New Article 2 in DB")))
        ;
    }

    @Test(dataProvider = "instanceParams")
    public void test_articles_instance_resource_expect_status_code_ok(long id, String bodyToVerify) throws URISyntaxException {
        URI uri = new URI("/articles/" + id);
        Response response = given()
				.auth()
				.preemptive()
				.basic("Test", "password")
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
                new Object[] { 1, "New Article 1 in DB" },
                new Object[] { 2, "New Article 2 in DB" } };
        return testDatas;
    }


}
