package com.steampowered.store.tests;

import com.steampowered.store.model.AddToCartResponseBodyModel;
import com.steampowered.store.pages.GamePage;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.steampowered.store.data.TestData.*;
import static com.steampowered.store.spec.AddGameToCartSpec.*;
import static com.steampowered.store.utils.RandomUtils.setBrowserLanguage;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTests extends TestBase {

    @Test
    public void validateCartWithSeveralGamesTest() {

        GamePage gamePage = new GamePage();

        step("Open game page", () -> {
            setBrowserLanguage("en");
            gamePage.openPage();
        });

        step("Add game to cart via UI", () -> {
            gamePage.addFirstItemToCart();
        });

        //todo в аллюр отчет добавить инфу про запросы

        AddToCartResponseBodyModel addToCartResponseBM1 =
                step("Add 2nd game via API", () ->
                        given(addGameToCartRequestSpec)
                                .multiPart("subid", game2SubId)
                                .when()
                                .post(addToCartApiPath)
                                .then()
                                .spec(addGameToCartResponseSpec)
                                .extract().as(AddToCartResponseBodyModel.class));

        step("Check game added", () -> {
            assertTrue(addToCartResponseBM1.isSuccess());
            assertEquals(Integer.parseInt(game2SubId),
                    addToCartResponseBM1.getContents().getLineItems().get(1).getPackageItem().getPackageId());
        });

        AddToCartResponseBodyModel addToCartResponseBM2 = step("Add 3rd game via API", () ->
                given(addGameToCartRequestSpec)
                        .multiPart("subid", game3SubId)
                        .when()
                        .post(addToCartApiPath)
                        .then()
                        .spec(addGameToCartResponseSpec)
                        .extract().as(AddToCartResponseBodyModel.class));

        step("Check game added", () -> {
            assertTrue(addToCartResponseBM2.isSuccess());
            assertEquals(Integer.parseInt(game2SubId),
                    addToCartResponseBM2.getContents().getLineItems().get(1).getPackageItem().getPackageId());
        });

        step("Proceed to Cart", () -> {
            gamePage.confirmProceedToCartDialog();
        });

        step("Check cart", () -> {
            gamePage.countWidgetItemsAmount(3)
                    .gameIsInCart(game1Title)
                    .gameIsInCart(game2Title)
                    .gameIsInCart(game3Title)
                    .continueShoppingButtonIsVisible();
        });
    }
}
