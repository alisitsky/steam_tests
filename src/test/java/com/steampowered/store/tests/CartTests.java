package com.steampowered.store.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.steampowered.store.utils.RandomUtils.waitForCookie;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class CartTests extends TestBase {

    @Test
    public void validateCartWithSeveralGamesTest() {

        step("Open game page", () -> {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--lang=en");
            Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, options);

            open("https://store.steampowered.com/app/632470/Disco_Elysium__The_Final_Cut/");
        });

        step("Add game to cart via UI", () -> {
            $$("div.btn_addtocart").first().click();
        });


        String sessionIdCookieValue = getWebDriver().manage().getCookieNamed("sessionid").getValue();
        String browserIdCookieValue = getWebDriver().manage().getCookieNamed("browserid").getValue();
        waitForCookie("shoppingCartGID", 4);
        String shoppingCartGIDCookieValue = getWebDriver().manage().getCookieNamed("shoppingCartGID").getValue();


        step("Add more games via API", () -> {
            // Define the base URL
            baseURI = "https://store.steampowered.com";

            // Add headers
            given()
                    .contentType(ContentType.MULTIPART)
                    .multiPart("subid", "407916")   //  pathologic 2 subid
                    .multiPart("sessionid", sessionIdCookieValue)
                    .multiPart("action", "add_to_cart")
                    .cookie("browserid", browserIdCookieValue)
                    .cookie("sessionid", sessionIdCookieValue)
                    .cookie("shoppingCartGID", shoppingCartGIDCookieValue)
                    .when()
                    .post("/cart/addtocart")
                    .then()
                    .log().all(); // This will print the response for verification

            given()
                    .contentType(ContentType.MULTIPART)
                    .multiPart("subid", "260435")   //  talos 2 subid
                    .multiPart("sessionid", sessionIdCookieValue)
                    .multiPart("action", "add_to_cart")
                    .cookie("browserid", browserIdCookieValue)
                    .cookie("sessionid", sessionIdCookieValue)
                    .cookie("shoppingCartGID", shoppingCartGIDCookieValue)
                    .when()
                    .post("/cart/addtocart")
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
