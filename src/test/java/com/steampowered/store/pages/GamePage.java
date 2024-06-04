package com.steampowered.store.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.steampowered.store.data.TestData.game1PagePath;

public class GamePage {

    SelenideElement addItemToCartButton = $$("div.btn_addtocart").first(),
                    proceedToCartDialogButton = $$("button.DialogButton").get(1),
                    itemTitle = $("div#appHubAppName");

    public GamePage openPage() {
        open(game1PagePath);
        return this;
    }

    public GamePage addItemToCart() {
        addItemToCartButton.click();
        return this;
    }

    public GamePage confirmProceedToCartDialog() {
        proceedToCartDialogButton.click();
        return this;
    }

    @Step("Page title corresponds to chosen game")
    public GamePage checkPageTitleIs(String gameTitle) {
        itemTitle.shouldHave(text(gameTitle));
        return this;
    }
}
