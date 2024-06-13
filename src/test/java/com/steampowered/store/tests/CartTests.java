package com.steampowered.store.tests;

import com.steampowered.store.api.addToCartApi;
import com.steampowered.store.model.AddToCartResponseBodyModel;
import com.steampowered.store.pages.CartPage;
import com.steampowered.store.pages.GamePage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.steampowered.store.data.TestData.*;
import static io.qameta.allure.Allure.step;

@Layer("ui")
@Epic("Cart Tests")
@Feature("Cart")
public class CartTests extends TestBase {

    GamePage gamePage = new GamePage();
    CartPage cartPage = new CartPage();
    addToCartApi addToCartApi = new addToCartApi();

    @Test
    @AllureId("32709")
    @Story("Display of games in the cart")
    @DisplayName("Three games")
    @Owner("alisitsky")
    public void validateCartWithSeveralGamesTest() {

        step("Open game page", () ->
                gamePage.openPage());

        step("Add game to cart via UI", () ->
                gamePage.addItemToCart());

        AddToCartResponseBodyModel addToCartResponseBM1 =
                step("Add 2nd game via API", () ->
                        addToCartApi.addToCart(game2SubId));

        step("Check game added", () ->
                addToCartApi.addedSuccessfully(game2SubId, addToCartResponseBM1));

        AddToCartResponseBodyModel addToCartResponseBM2 =
                step("Add 3rd game via API", () ->
                        addToCartApi.addToCart(game3SubId));

        step("Check game added", () ->
                addToCartApi.addedSuccessfully(game3SubId, addToCartResponseBM2));

        step("Proceed to Cart", () ->
                gamePage.confirmProceedToCartDialog());

        step("Check cart contents", () -> {
            cartPage.countWidgetItemsAmountIs(3)
                    .gameIsInCart(game1Title)
                    .gameIsInCart(game2Title)
                    .gameIsInCart(game3Title)
                    .continueShoppingButtonIsVisible();
        });
    }

    @Test
    @AllureId("32708")
    @Story("Remove games from the cart")
    @DisplayName("Remove 1 of 3")
    @Owner("alisitsky")
    public void removeOneGameFromCartTest() {

        step("Open game page", () ->
                gamePage.openPage());

        step("Add game to cart via UI", () ->
                gamePage.addItemToCart());

        AddToCartResponseBodyModel addToCartResponseBM1 =
                step("Add 2nd game via API", () ->
                        addToCartApi.addToCart(game2SubId));

        step("Check game added", () ->
                addToCartApi.addedSuccessfully(game2SubId, addToCartResponseBM1));

        AddToCartResponseBodyModel addToCartResponseBM2 =
                step("Add 3rd game via API", () ->
                        addToCartApi.addToCart(game3SubId));

        step("Check game added", () ->
                addToCartApi.addedSuccessfully(game3SubId, addToCartResponseBM2));

        step("Proceed to Cart", () ->
                gamePage.confirmProceedToCartDialog());

        step("Save est.total to check it change later", () ->
                cartPage.saveEstimatedTotal());

        step("Remove 1st added game from cart", () ->
                cartPage.removeGameFromCart(game1Title));

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
    @AllureId("32710")
    @Story("Remove games from the cart")
    @DisplayName("Remove all")
    @Owner("alisitsky")
    public void removeAllGamesFromCartTest() {

        step("Open game page", () ->
                gamePage.openPage());

        step("Add game to cart via UI", () ->
                gamePage.addItemToCart());

        AddToCartResponseBodyModel addToCartResponseBM1 =
                step("Add 2nd game via API", () ->
                        addToCartApi.addToCart(game2SubId));

        step("Check game added", () ->
                addToCartApi.addedSuccessfully(game2SubId, addToCartResponseBM1));

        AddToCartResponseBodyModel addToCartResponseBM2 =
                step("Add 3rd game via API", () ->
                        addToCartApi.addToCart(game3SubId));

        step("Check game added", () ->
                addToCartApi.addedSuccessfully(game3SubId, addToCartResponseBM2));

        step("Proceed to Cart", () ->
                gamePage.confirmProceedToCartDialog());

        step("Save est.total to check it change later", () ->
                cartPage.saveEstimatedTotal());

        step("Click \"Remove all items\"", () ->
                cartPage.removeAllItems());

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
