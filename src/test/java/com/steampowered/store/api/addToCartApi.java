package com.steampowered.store.api;

import com.steampowered.store.model.AddToCartResponseBodyModel;

import static com.steampowered.store.data.TestData.addToCartApiPath;
import static com.steampowered.store.data.TestData.game2SubId;
import static com.steampowered.store.spec.AddGameToCartSpec.addGameToCartRequestSpec;
import static com.steampowered.store.spec.AddGameToCartSpec.addGameToCartResponseSpec;
import static com.steampowered.store.utils.RandomUtils.getCookieValue;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class addToCartApi {

    public AddToCartResponseBodyModel addToCart(String gameSubId) {
        return given(addGameToCartRequestSpec)
                .multiPart("subid", gameSubId)
                .multiPart("sessionid", getCookieValue("sessionid"))
                .multiPart("action", "add_to_cart")
                .cookie("sessionid", getCookieValue("sessionid"))
                .cookie("shoppingCartGID", getCookieValue("shoppingCartGID", 4))
                .when()
                .post(addToCartApiPath)
                .then()
                .spec(addGameToCartResponseSpec)
                .extract().as(AddToCartResponseBodyModel.class);
    }

    public void addedSuccessfully(String gameSubId, AddToCartResponseBodyModel responseBody) {
        assertTrue(responseBody.isSuccess());
        assertEquals(Integer.parseInt(game2SubId),
                responseBody.getContents().getLineItems().get(1).getPackageItem().getPackageId());

    }

}
