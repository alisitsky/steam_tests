package com.steampowered.api.data;

import java.util.stream.Stream;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.XML;

public class GetNewsForAppTestData {

    public static String getNewsForAppPath = "ISteamNews/GetNewsForApp/v0002/?",
            getNewsForAppCgiAppId = "appid=632470&",
            getNewsForAppCgiNewsCount = "count=3&",
            getNewsForAppCgiMaxLength = "maxlength=300&",
            getGetNewsForAppCgiTags = "tags=patchnotes&",
            getNewsForAppUrlDefault = getNewsForAppPath
                                    + getNewsForAppCgiAppId
                                    + getNewsForAppCgiNewsCount
                                    + getNewsForAppCgiMaxLength,
            getNewsForAppUrlNoCountParam = getNewsForAppPath
                                    + getNewsForAppCgiAppId
                                    + getNewsForAppCgiMaxLength,
            newsTagValue = "patchnotes",
            pathToNewsJsonSchema = "get-news-for-app.json";


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
