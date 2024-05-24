package com.steampowered.api.tests;

import com.steampowered.api.model.GetNewsForAppResponseBodyModel;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.steampowered.api.data.TestData.*;
import static com.steampowered.api.spec.GetNewsForAppSpec.getNewsForAppRequestSpec;
import static com.steampowered.api.spec.GetNewsForAppSpec.getNewsForAppResponseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("News Endpoint Tests")
public class GetNewsForAppTests extends TestBase {

    @Test
    @DisplayName("Validate json schema")
    public void schemaValidationTest() {

        step("Validate json schema", () ->
                given(getNewsForAppRequestSpec)
                        .when()
                        .get(getNewsForAppUrlDefault)
                        .then()
                        .spec(getNewsForAppResponseSpec)
                        .assertThat().body(matchesJsonSchemaInClasspath("get-news-for-app.json")
                        ));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("com.steampowered.api.data.TestData#contentTypeData")
    @Story("Response content-type is changed by cgi")
    @DisplayName("Format cgi is: ")
    public void contentTypeTest(String cgi, ContentType type) {

        step("Validate content-type", () ->
                given(getNewsForAppRequestSpec)
                        .when()
                        .get(getNewsForAppUrlDefault + cgi)
                        .then()
                        .spec(getNewsForAppResponseSpec)
                        .assertThat().contentType(type));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("com.steampowered.api.data.TestData#newsCountData")
    @Story("News count is changed by cgi")
    @DisplayName("Count cgi is: ")
    public void newsCountTest(String cgi, int expectedNewsCount) {

        GetNewsForAppResponseBodyModel getNewsForAppRespBM = step("Request news json", () ->
                given(getNewsForAppRequestSpec)
                        .when()
                        .get(getNewsForAppUrlNoCountParam + cgi)
                        .then()
                        .spec(getNewsForAppResponseSpec)
                        .extract().as(GetNewsForAppResponseBodyModel.class));

        step("Check news count", () ->
                        assertEquals(expectedNewsCount, getNewsForAppRespBM.getAppNews().getNewsItems().size())
        );
    }
}
