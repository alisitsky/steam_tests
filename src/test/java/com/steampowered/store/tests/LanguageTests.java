package com.steampowered.store.tests;

import com.steampowered.store.pages.MainPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.DisplayName;

import java.util.stream.Stream;

@Epic("Localization")
@Feature("Language Change")
public class LanguageTests extends TestBase {

    MainPage mainPage = new MainPage();

    @ParameterizedTest(name = "{0}")
    @MethodSource("com.steampowered.store.data.TestData#changeLanguageData")
    @Story("Change interface language")
    @DisplayName("Change to ")
    public void changeLanguageInHeaderTest(String language, String headerText) {
        mainPage.openPage()
                .clickLanguageDropdownButton()
                .languageDropdownIsVisible()
                .chooseLanguageAndClick(language)
                .headerTextLanguageIsChanged(headerText);
    }
}