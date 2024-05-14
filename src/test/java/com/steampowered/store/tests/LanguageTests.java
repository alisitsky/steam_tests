package com.steampowered.store.tests;

import com.steampowered.store.pages.MainPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class LanguageTests extends TestBase {

    MainPage mainPage = new MainPage();

    @ParameterizedTest
    @MethodSource("changeLanguageData")
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
