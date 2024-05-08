package com.steampowered.store.utils;

import io.qameta.allure.restassured.AllureRestAssured;

public class AllureUtils {
    private static final AllureRestAssured FILTER = new AllureRestAssured();

    public static AllureRestAssured withCustomTemplates() {
        FILTER.setRequestTemplate("request.ftl");
        FILTER.setResponseTemplate("response.ftl");
        return FILTER;
    }
}