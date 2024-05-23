package com.steampowered.api.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.steampowered.api.data.TestData.getNewsForAppUrl;
import static com.steampowered.api.spec.GetNewsForAppSpec.getNewsForAppRequestSpec;
import static com.steampowered.api.spec.GetNewsForAppSpec.getNewsForAppResponseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Epic("News Endpoint Tests")
public class GetNewsForAppTests extends TestBase {

    @Test
    @DisplayName("Validate json schema")
    public void schemaValidationTest() {

        step("Validate json schema", () ->
                given(getNewsForAppRequestSpec)
                        .when()
                        .get(getNewsForAppUrl)
                        .then()
                        .spec(getNewsForAppResponseSpec)
                        .assertThat().body(matchesJsonSchemaInClasspath("get-news-for-app.json")
                        ));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("com.steampowered.api.data.TestData#contentTypeData")
    @Story("Response content-type is changed by cgi")
    @DisplayName("Cgi is: ")
    public void contentTypeTest(String cgi, ContentType type) {

        step("Validate content-type", () ->
                given(getNewsForAppRequestSpec)
                        .when()
                        .get(getNewsForAppUrl + cgi)
                        .then()
                        .spec(getNewsForAppResponseSpec)
                        .assertThat().contentType(type));
    }
}
