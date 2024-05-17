package com.steampowered.store.spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.steampowered.store.utils.AllureUtils.withCustomTemplates;
import static com.steampowered.store.utils.RandomUtils.getCookieValue;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;

public class AddGameToCartSpec {

    public static RequestSpecification addGameToCartRequestSpec = with()
            .filter(withCustomTemplates())
            .contentType(ContentType.MULTIPART)
            .log().uri()
            .log().headers()
            .log().method()
            .log().body();    

    public static ResponseSpecification addGameToCartResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();
}
