package com.steampowered.api.tests;

import com.steampowered.api.model.GetNewsForAppResponseBodyModel;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.steampowered.api.data.GetNewsForAppTestData.*;
import static com.steampowered.api.helpers.GetNewsForAppHelpers.getNewsList;
import static com.steampowered.api.spec.GetNewsForAppSpec.getNewsForAppRequestSpec;
import static com.steampowered.api.spec.GetNewsForAppSpec.getNewsForAppResponseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("News Endpoint Tests")
@Feature("ISteamNews/GetNewsForApp/v0002/")
public class GetNewsForAppTests extends TestBase {

    @Test
    @Story("Response schema validation")
    @DisplayName("Json schema")
    public void schemaValidationTest() {

        Response response = step("Request news json", () ->
                given(getNewsForAppRequestSpec)
                        .when()
                        .get(getNewsForAppUrlDefault)
                        .then()
                        .spec(getNewsForAppResponseSpec)
                        .extract()
                        .response());

        step("Validate JSON schema", () ->
                response.then()
                        .assertThat()
                        .body(matchesJsonSchemaInClasspath(pathToNewsJsonSchema)));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("com.steampowered.api.data.GetNewsForAppTestData#contentTypeData")
    @Story("Response content-type is changed by cgi")
    @DisplayName("Format cgi is: ")
    public void contentTypeTest(String cgi, ContentType type) {

        Response response = step("Request news in given format", () ->
                given(getNewsForAppRequestSpec)
                        .when()
                        .get(getNewsForAppUrlDefault + cgi)
                        .then()
                        .spec(getNewsForAppResponseSpec)
                        .extract()
                        .response());

        step("Validate response format", () ->
                response.then()
                        .assertThat()
                        .contentType(type));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("com.steampowered.api.data.GetNewsForAppTestData#newsCountData")
    @Story("News count is changed by cgi")
    @DisplayName("Count cgi is: ")
    public void newsCountTest(String cgi, int expectedNewsCount) {

        GetNewsForAppResponseBodyModel responseBody = step("Request news json", () ->
                given(getNewsForAppRequestSpec)
                        .when()
                        .get(getNewsForAppUrlNoCountParam + cgi)
                        .then()
                        .spec(getNewsForAppResponseSpec)
                        .extract().as(GetNewsForAppResponseBodyModel.class));

        step("Check news count", () ->
                assertEquals(expectedNewsCount, responseBody.getAppNews().getNewsItems().size(),
                        "Endpoint should return given amount of news items")
        );
    }

    @Test
    @Story("Filter news by tag")
    @DisplayName("All the news have given tag")
    public void searchByTagTest() {

        GetNewsForAppResponseBodyModel responseBody = step("Request news json", () ->
                given(getNewsForAppRequestSpec)
                        .when()
                        .get(getNewsForAppUrlDefault + getGetNewsForAppCgiTags)
                        .then()
                        .spec(getNewsForAppResponseSpec)
                        .extract().as(GetNewsForAppResponseBodyModel.class));

        step("Check every news has tag", () -> {
            for (GetNewsForAppResponseBodyModel.AppNews.NewsItems item : getNewsList(responseBody))
                assertTrue(item.getTags().contains(newsTagValue), "Each news item should contain the given tag");
        });
    }
}
