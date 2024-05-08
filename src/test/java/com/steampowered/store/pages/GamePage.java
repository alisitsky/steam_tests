package com.steampowered.store.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.steampowered.store.data.TestData.game1PagePath;


public class GamePage {

    SelenideElement firstItemAddToCartButton = $$("div.btn_addtocart").first(),
                    proceedToCartDialogButton = $$("button.DialogButton").get(1),
                    cartCounterWidget = $("div[data-featuretarget=shoppingcart-count-widget]"),
                    continueShoppingButton = $$("button.Secondary.Focusable").findBy(text("Continue Shopping"));

    ElementsCollection gameItemPannel = $$("div.Panel.Focusable");

    public GamePage openPage(){
        open(game1PagePath);
        return this;
    }

    public GamePage addFirstItemToCart(){
        firstItemAddToCartButton.click();
        return this;
    }

    public GamePage confirmProceedToCartDialog(){
        proceedToCartDialogButton.click();
        return this;
    }

    public GamePage countWidgetItemsAmount(int gamesAmount){
        cartCounterWidget.shouldHave(text("Cart (" + gamesAmount + ")"));
        return this;
    }

    public GamePage gameIsInCart(String gameTitle){
        gameItemPannel.findBy(text(gameTitle)).shouldBe(visible);
        return this;
    }

    public GamePage continueShoppingButtonIsVisible(){
        continueShoppingButton.shouldBe(visible);
        return this;
    }

    public GamePage name(){

        return this;
    }
}
