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
    @MethodSource("changeLanguageData")
    @Story("Change interface language")
    @DisplayName("Change interface language to ")
    public void changeLanguageInHeaderTest(String language, String headerText) {
        mainPage.openPage()
                .clickLanguageDropdownButton()
                .languageDropdownIsVisible()
                .chooseLanguageAndClick(language)
                .headerTextLanguageIsChanged(headerText);
    }

    static Stream<String[]> changeLanguageData() {
        return Stream.of(
                new String[]{"Français", "MAGASIN"},
                new String[]{"Deutsch ", "SHOP"},
                new String[]{"Español ", "TIENDA"});
    }
}