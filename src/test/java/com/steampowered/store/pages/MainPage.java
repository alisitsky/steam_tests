package com.steampowered.store.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.ARROW_DOWN;
import static org.openqa.selenium.Keys.ENTER;

public class MainPage {

    SelenideElement     languageDropdownButton = $("span#language_pulldown"),
                        languageDropdown = $("div#language_dropdown"),
                        headerTextContainer = $("div.supernav_container"),
                        searchInput = $("input#store_nav_search_term"),
                        searchSuggestDropdown = $("div#search_suggestion_contents"),
                        topItemOfSuggest = $("a.match_category_top");
    ElementsCollection  languageListItem = $$("a.popup_menu_item");

    @Step("Open main page")
    public MainPage openPage() {
        open(baseUrl);
        return this;
    }

    public MainPage clickLanguageDropdownButton() {
        languageDropdownButton.click();
        return this;
    }

    public MainPage languageDropdownIsVisible() {
        languageDropdown.shouldBe(visible);
        return this;
    }

    public MainPage chooseLanguageAndClick(String language) {
        languageListItem.findBy(text(language)).click();
        return this;
    }

    public MainPage headerTextLanguageIsChanged(String headerText) {
        headerTextContainer.shouldHave(text(headerText));
        return this;
    }

    @Step("Set game title into search input")
    public MainPage setGameTitleIntoSearchInput(String gameTitle) {
        searchInput.setValue(gameTitle);
        return this;
    }

    @Step("Search suggest is visible")
    public MainPage searchSuggestIsVisible() {
        searchSuggestDropdown.shouldBe(visible);
        return this;
    }

    @Step("Click 1st game from the suggest list")
    public MainPage clickTopItemFromSuggest() {
        topItemOfSuggest.click();
        return this;
    }

    @Step("Choose 1st game from the suggest list with keyboard")
    public MainPage chooseTopItemFromSuggestWithKeyboard() {
        searchInput.sendKeys(ARROW_DOWN, ENTER);
        return this;
    }
}
