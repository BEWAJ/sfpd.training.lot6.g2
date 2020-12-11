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

public class MyTest_Articles extends SetDataForEndToEndTest {

	@Test(dataProvider = "instanceParams")
    public void test_articles_filtering(Integer offset, Integer size, Integer year, Integer result) throws URISyntaxException {
        // URI uri = new URI("/articles?offset=1&limit=1");
        URI uri = new URI("/articles");
        Response response = given()
            .accept(ContentType.JSON)
				.queryParam("limit", offset)
				.queryParam("offset", size)
				.queryParam("year", year)
            .when()
                .get(uri);

        response.body().prettyPrint();

        response.then().assertThat()
            .statusCode(HttpStatus.SC_OK)
            .and()
            .body("size()", is(result));
    }

	@DataProvider
	public Object[][] instanceParams() {
		Object[][] testDatas = new Object[][] {
				new Object[] { 1, 1, 2020, 1},
				new Object[] { 1, 2, 2020, 1 } };
		return testDatas;
	}

}