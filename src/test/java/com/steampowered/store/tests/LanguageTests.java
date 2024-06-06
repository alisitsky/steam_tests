package com.steampowered.store.tests;

import com.steampowered.store.pages.MainPage;
import io.qameta.allure.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.DisplayName;

import java.util.stream.Stream;

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
        mainPage.openPage()
                .clickLanguageDropdownButton()
                .languageDropdownIsVisible()
                .chooseLanguageAndClick(language)
                .headerTextLanguageIsChanged(headerText);
    }
}