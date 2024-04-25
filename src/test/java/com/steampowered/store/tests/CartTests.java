package com.steampowered.store.tests;

import com.steampowered.store.pages.GamePage;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.steampowered.store.data.TestData.*;
import static com.steampowered.store.utils.RandomUtils.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

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

        step("Add more games via API", () -> {
//            String sessionIdCookieValue = getWebDriver().manage().getCookieNamed("sessionid").getValue();
//            String browserIdCookieValue = getWebDriver().manage().getCookieNamed("browserid").getValue();
//            waitForCookie("shoppingCartGID", 4);
//            String shoppingCartGIDCookieValue = getWebDriver().manage().getCookieNamed("shoppingCartGID").getValue();

            // Define the base URL
//            baseURI = "https://store.steampowered.com";

            // Add headers
            given()
                    .contentType(ContentType.MULTIPART)
                    .multiPart("subid", game2SubId)   //  pathologic 2 subid
                    .multiPart("sessionid", getCookieValue("sessionid"))
                    .multiPart("action", "add_to_cart")
                    .cookie("browserid", getCookieValue("browserid"))
                    .cookie("sessionid", getCookieValue("sessionid"))
                    .cookie("shoppingCartGID", getCookieValue("shoppingCartGID", 4))
                    .when()
                    .post(addToCartApiPath)
                    .then()
                    .log().all(); // This will print the response for verification


            given()
                    .contentType(ContentType.MULTIPART)
                    .multiPart("subid", game3SubId)   //  talos 2 subid
                    .multiPart("sessionid", getCookieValue("sessionid"))
                    .multiPart("action", "add_to_cart")
                    .cookie("browserid", getCookieValue("browserid"))
                    .cookie("sessionid", getCookieValue("sessionid"))
                    .cookie("shoppingCartGID", getCookieValue("shoppingCartGID", 4))
                    .when()
                    .post(addToCartApiPath)
                    .then()
                    .log().all(); // This will print the response for verification

            //todo добавить проверку ответа на саксесс
        });

        step("Proceed to Cart", () -> {
            $$("button.DialogButton").get(1).click();
        });

        step("Check cart", () -> {
            $("div[data-featuretarget=shoppingcart-count-widget]").shouldHave(text("Cart (3)"));
            $$("div.Panel.Focusable").findBy(text("Disco Elysium")).shouldBe(visible);
            $$("div.Panel.Focusable").findBy(text("Pathologic 2")).shouldBe(visible);
            $$("div.Panel.Focusable").findBy(text("The Talos Principle 2")).shouldBe(visible);
            $$("button.Secondary.Focusable").findBy(text("Continue Shopping")).shouldBe(visible);
        });
    }
}
