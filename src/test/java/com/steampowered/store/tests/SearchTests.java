package com.steampowered.store.tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.ARROW_DOWN;
import static org.openqa.selenium.Keys.ENTER;

public class SearchTests extends TestBase {

    @Test
    public void selectFromSearchSuggestByClickTest() {
        open(baseUrl);
        $("input#store_nav_search_term").setValue("Disco Elysium");
        $("div#search_suggestion_contents").shouldBe(visible);
        $("a.match_category_top").click();
        $("div#appHubAppName").shouldHave(text("Disco Elysium"));
    }

    @Test
    public void selectFromSearchSuggestWithKeyboardTest() {
        open(baseUrl);
        $("input#store_nav_search_term").setValue("Disco Elysium");
        $("div#search_suggestion_contents").shouldBe(visible);
        $("input#store_nav_search_term").sendKeys(ARROW_DOWN, ENTER);
        $("div#appHubAppName").shouldHave(text("Disco Elysium"));
    }
}
