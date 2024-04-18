package com.steampowered.store.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class CartTests extends TestBase {

    @Test
    public void removeItemFromCartTest() {


        step("Remove book from cart", () -> {
            // todo спрятать креды в дженкинс
            open("https://store.steampowered.com/favicon.ico");
            Cookie myCookie = new Cookie("steamLoginSecure", "76561199481030438%7C%7CeyAidHlwIjogIkpXVCIsICJhbGciOiAiRWREU0EiIH0.eyAiaXNzIjogInI6MEVGM18yNDQ4OUYxN18xMTBGMyIsICJzdWIiOiAiNzY1NjExOTk0ODEwMzA0MzgiLCAiYXVkIjogWyAid2ViOnN0b3JlIiBdLCAiZXhwIjogMTcxMzUzNTUwNywgIm5iZiI6IDE3MDQ4MDg3NjAsICJpYXQiOiAxNzEzNDQ4NzYwLCAianRpIjogIjBFRkNfMjQ0ODlGMTVfRkQyMjMiLCAib2F0IjogMTcxMzQ0ODc1OSwgInJ0X2V4cCI6IDE3MzEyMDEyMDcsICJwZXIiOiAwLCAiaXBfc3ViamVjdCI6ICIxMDkuMjQ1LjE5NS4xOTYiLCAiaXBfY29uZmlybWVyIjogIjEwOS4yNDUuMTk1LjE5NiIgfQ.0KOg8F4Y4TV5f5QbKOAITfKIvxdZP9lraNVZgxOKmD6lIcao3FgfdcJLTw3nzuipOFfNITYt8OuPjbcPv-JPBA");
            WebDriverRunner.getWebDriver().manage().addCookie(myCookie);
            open("https://store.steampowered.com/cart");

            $("div[data-featuretarget=shoppingcart-count-widget]").shouldNotBe(visible);
            $$("div.Panel.Focusable").findBy(text("Disco Elysium")).shouldNotBe(visible);
            $$("div.Panel.Focusable").findBy(text("Your cart is empty.")).shouldBe(visible);
            $$("button").findBy(text("Continue to payment")).shouldHave(cssClass("Disabled"));
        });

        sleep(1000);

    }
}
