package com.steampowered.api.data;

import java.util.stream.Stream;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.XML;

public class TestData {

    public static String getNewsForAppPath = "ISteamNews/GetNewsForApp/v0002/?",
            getNewsForAppParameterAppId = "appid=632470&",
            getNewsForAppParameterNewsCount = "count=3&",
            getNewsForAppParameterMaxLength = "maxlength=300&",
            getNewsForAppUrlDefault = getNewsForAppPath
                    + getNewsForAppParameterAppId
                    + getNewsForAppParameterNewsCount
                    + getNewsForAppParameterMaxLength,
            getNewsForAppUrlNoCountParam = getNewsForAppPath
                    + getNewsForAppParameterAppId
                    + getNewsForAppParameterMaxLength;


    public static Stream<Object[]> contentTypeData() {
        return Stream.of(
                new Object[]{"format=xml", XML},
                new Object[]{"format=json", JSON},
                new Object[]{null, JSON});
    }

    public static Stream<Object[]> newsCountData() {
        return Stream.of(
                new Object[]{"count=0", 0},
                new Object[]{"count=1", 1},
                new Object[]{"count=2", 2},
                new Object[]{"count=10", 10},
                new Object[]{"count=100", 100},
                new Object[]{"count=-1", 0});
    }
}
