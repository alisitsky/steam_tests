package com.steampowered.store.tests;

import com.steampowered.store.model.AddToCartResponseBodyModel;
import com.steampowered.store.pages.CartPage;
import com.steampowered.store.pages.GamePage;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.ownText;
import static com.codeborne.selenide.Selenide.$$;
import static com.steampowered.store.data.TestData.*;
import static com.steampowered.store.spec.AddGameToCartSpec.addGameToCartRequestSpec;
import static com.steampowered.store.spec.AddGameToCartSpec.addGameToCartResponseSpec;
import static com.steampowered.store.utils.RandomUtils.setBrowserLanguage;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTests extends TestBase {

    GamePage gamePage = new GamePage();
    CartPage cartPage = new CartPage();

    @Test
    public void validateCartWithSeveralGamesTest() {

        // todo выбор языка унести в конфиг (или хотя бы значение в TestData)
        step("Open game page", () -> {
            setBrowserLanguage("en");
            gamePage.openPage();
        });

        step("Add game to cart via UI", () -> {
            gamePage.addFirstItemToCart();
        });

        //todo в аллюр отчет добавить инфу про тело запроса
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

        step("Check cart contents", () -> {
            cartPage.countWidgetItemsAmountIs(3)
                    .gameIsInCart(game1Title)
                    .gameIsInCart(game2Title)
                    .gameIsInCart(game3Title)
                    .continueShoppingButtonIsVisible();
        });
    }

    @Test
    public void removeOneGameFromCartTest() {

        // todo выбор языка унести в конфиг (или хотя бы значение в TestData) или в beforeEach/All
        step("Open game page", () -> {
            setBrowserLanguage("en");
            gamePage.openPage();
        });

        step("Add game to cart via UI", () -> {
            gamePage.addFirstItemToCart();
        });

        //todo в аллюр отчет добавить инфу про тело запроса
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

        step("Save est.total to check it change later", () -> {
            cartPage.saveEstimatedTotal();
        });

        step("Remove 1st added game from cart", () -> {
            cartPage.removeGameFromCart(game1Title);
        });

        step("Check cart contents", () -> {
            cartPage.countWidgetItemsAmountIs(2)
                    .gameIsNotInCart(game1Title)
                    .gameIsInCart(game2Title)
                    .gameIsInCart(game3Title)
                    .estimatedTotalIsChanged()
                    .continueShoppingButtonIsVisible();
        });
    }

    @Test
    public void removeAllGamesFromCartTest() {
        // todo выбор языка унести в конфиг (или хотя бы значение в TestData) или в beforeEach/All
        step("Open game page", () -> {
            setBrowserLanguage("en");
            gamePage.openPage();
        });

        step("Add game to cart via UI", () -> {
            gamePage.addFirstItemToCart();
        });

        //todo в аллюр отчет добавить инфу про тело запроса
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

        step("Save est.total to check it change later", () -> {
            cartPage.saveEstimatedTotal();
        });

        step("Click \"Remove all items\"", () -> {
            $$("div.Panel.Focusable").findBy(ownText("Remove all items")).click();
        });

        step("Check cart contents", () -> {
            cartPage.countWidgetIsNotVisible()
                    .gameIsNotInCart(game1Title)
                    .gameIsNotInCart(game2Title)
                    .gameIsNotInCart(game3Title)
                    .estimatedTotalIsZero()
                    .continueShoppingButtonIsNotVisible()
                    .emptyCartMessageAppeared();
        });
    }
}
