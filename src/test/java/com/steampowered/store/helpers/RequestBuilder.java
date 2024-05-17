package com.steampowered.store.helpers;

import io.restassured.specification.RequestSpecification;

import static com.steampowered.store.utils.RandomUtils.getCookieValue;

public class RequestBuilder {

    public RequestSpecification buildRequestAddingGameToCart(RequestSpecification ReqSpec, String subId) {
        return ReqSpec
                .multiPart("subid", subId)
                .multiPart("sessionid", getCookieValue("sessionid"))
                .multiPart("action", "add_to_cart")
                .cookie("browserid", getCookieValue("browserid"))
                .cookie("sessionid", getCookieValue("sessionid"))
                .cookie("shoppingCartGID", getCookieValue("shoppingCartGID", 4));
    }
}