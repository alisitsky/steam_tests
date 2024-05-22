package com.steampowered.api.tests;

import com.steampowered.api.model.GetNewsForAppResponseBodyModel;
import org.junit.jupiter.api.Test;

import static com.steampowered.api.spec.GetNewsForAppSpec.getNewsForAppResponseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetNewsForAppTests {

    @Test
    public void schemaValidationTest(){

        GetNewsForAppResponseBodyModel getNewsForAppResponseBM =
                step("Get /ISteamNews/GetNewsForApp response", () ->
                        given()
                                .when()
                                .get("https://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/?appid=632470&count=3&maxlength=300&format=json")
                                .then()
                                .spec(getNewsForAppResponseSpec)
                                .extract().as(GetNewsForAppResponseBodyModel.class)
                        );

        step("Validate", () -> {
            assertEquals(632470, getNewsForAppResponseBM.getAppNews().getAppId());
                });
    }
}
