package com.steampowered.store.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.ownText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.steampowered.store.data.TestData.estimatedTotalBeforeChange;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CartPage {
    SelenideElement cartCounterWidget = $("div[data-featuretarget=shoppingcart-count-widget]"),
            continueShoppingButton = $$("button.Secondary.Focusable").findBy(text("Continue Shopping")),
            estimatedTotal = $$("button.DialogButton.Primary.Focusable").get(1).preceding(1)
                    .$$("div").findBy(text("Estimated total")).sibling(0),
            emptyCartMessage = $$("div.Panel.Focusable div").findBy(ownText("Your cart is empty."));

    ElementsCollection gameItemPannel = $$("div.Panel.Focusable");

    public CartPage countWidgetItemsAmountIs(int gamesAmount) {
        cartCounterWidget.shouldHave(text("Cart (" + gamesAmount + ")"));
        return this;
    }

    public CartPage countWidgetIsNotVisible() {
        cartCounterWidget.shouldNotBe(visible);
        return this;
    }

    public CartPage gameIsInCart(String gameTitle) {
        gameItemPannel.findBy(text(gameTitle)).shouldBe(visible);
        return this;
    }

    public CartPage gameIsNotInCart(String gameTitle) {
        gameItemPannel.findBy(text(gameTitle)).shouldNotBe(visible);
        return this;
    }

    public CartPage continueShoppingButtonIsVisible() {
        continueShoppingButton.shouldBe(visible);
        return this;
    }

    public CartPage continueShoppingButtonIsNotVisible() {
        continueShoppingButton.shouldNotBe(visible);
        return this;
    }

    public CartPage saveEstimatedTotal() {
        estimatedTotalBeforeChange = estimatedTotal.text();
        return this;
    }

    public CartPage removeGameFromCart(String gameTitle) {
        getSelenideElementOfRemoveButtonByGameTitle(gameTitle).click();
        return this;
    }

    private SelenideElement getSelenideElementOfRemoveButtonByGameTitle(String gameTitle) {
        return $$("div.Panel.Focusable div").findBy(ownText(gameTitle))
                .parent().parent().$$("div.Panel.Focusable").findBy(ownText("Remove"));
    }

    public CartPage estimatedTotalIsChanged() {
        assertNotEquals(estimatedTotalBeforeChange, estimatedTotal.text());
        return this;
    }

    public CartPage estimatedTotalIsZero() {
        String total = estimatedTotal.text();
        assertEquals("0,00", total.substring(0, total.length() - 1));
        return this;
    }

    public CartPage emptyCartMessageAppeared() {
        emptyCartMessage.shouldBe(visible);
        return this;
    }


}
