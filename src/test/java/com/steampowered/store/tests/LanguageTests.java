package com.steampowered.store.tests;

import com.steampowered.store.pages.MainPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.qameta.allure.Allure.step;

@Layer("ui")
@Epic("Localization")
@Feature("Language Change")
public class LanguageTests extends TestBase {

    MainPage mainPage = new MainPage();

    @ParameterizedTest(name = "{0}")
    @AllureId("32711")
    @MethodSource("com.steampowered.store.data.TestData#changeLanguageData")
    @Story("Change interface language")
    @DisplayName("Change to ")
    @Owner("alisitsky")
    public void changeLanguageInHeaderTest(String language, String headerText) {

        step("Open main page", () ->
                mainPage.openPage());

        step("Click language button to get dropdown", () -> {
            mainPage.clickLanguageDropdownButton()
                    .languageDropdownIsVisible();
        });

        step("Select language from dropdown", () ->
                mainPage.chooseLanguageAndClick(language));

        step("Header text language is changed", () ->
                mainPage.headerTextLanguageIsChanged(headerText));
    }
}