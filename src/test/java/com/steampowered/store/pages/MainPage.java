package com.steampowered.store.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

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

    public MainPage setGameTitleIntoSearchInput(String gameTitle) {
        searchInput.setValue(gameTitle);
        return this;
    }

    public MainPage searchSuggestIsVisible() {
        searchSuggestDropdown.shouldBe(visible);
        return this;
    }

    public MainPage clickTopItemFromSuggest() {
        topItemOfSuggest.click();
        return this;
    }

    public MainPage chooseTopItemFromSuggestWithKeyboard() {
        searchInput.sendKeys(ARROW_DOWN, ENTER);
        return this;
    }
}
