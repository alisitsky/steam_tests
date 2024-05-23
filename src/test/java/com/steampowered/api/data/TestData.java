package com.steampowered.api.data;

import java.util.stream.Stream;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.XML;

public class TestData {

    public static String getNewsForAppPath = "ISteamNews/GetNewsForApp/v0002/?",
            getNewsForAppParameterAppId = "appid=632470&",
            getNewsForAppParameterNewsCount = "count=3&",
            getNewsForAppParameterMaxLength = "maxlength=300&",

            getNewsForAppUrl    = getNewsForAppPath
                                + getNewsForAppParameterAppId
                                + getNewsForAppParameterNewsCount
                                + getNewsForAppParameterMaxLength;

    public static Stream<Object[]> contentTypeData() {
        return Stream.of(
                new Object[]{"format=xml", XML},
                new Object[]{"format=json", JSON});
    }
}
