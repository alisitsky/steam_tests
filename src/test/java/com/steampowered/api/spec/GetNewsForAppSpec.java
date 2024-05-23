package com.steampowered.api.spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.steampowered.store.utils.AllureUtils.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;

public class GetNewsForAppSpec {

    public static RequestSpecification getNewsForAppRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().headers()
            .log().method()
            .log().body();

    public static ResponseSpecification getNewsForAppResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();

}
