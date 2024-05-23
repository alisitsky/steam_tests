package com.steampowered.api.tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    static void beforeAll(){
        RestAssured.baseURI = "https://api.steampowered.com/";

    }
}
