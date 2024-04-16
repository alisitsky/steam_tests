package com.steampowered.store.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;
import org.openqa.selenium.chrome.ChromeOptions;

public class LanguageTests extends TestBase {

    static Stream<String[]> changeLanguageData() {
        return Stream.of(
                new String[]{"Français", "MAGASIN"},
                new String[]{"Deutsch ", "SHOP"},
                new String[]{"Español ", "TIENDA"}
        );
    }

    @ParameterizedTest
    @MethodSource("changeLanguageData")
    public void changeLanguageInHeaderTest(String language, String headerText) {
        open(baseUrl);
        $("span#language_pulldown").click();
        $("div#language_dropdown").shouldBe(visible);
        $$("a.popup_menu_item").findBy(text(language)).click();
        $("div.supernav_container").shouldHave(text(headerText));
    }

    static Stream<String[]> changeLocaleData() {
        return Stream.of(
                new String[]{"fr", "MAGASIN"},
                new String[]{"de ", "SHOP"},
                new String[]{"es ", "TIENDA"}
        );
    }

    @ParameterizedTest
    @MethodSource("changeLocaleData")
    public void languageMatchesBrowserLocaleTest(String locale, String headerText) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=" + locale);
        Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, options);

        open(baseUrl);
        $("div.supernav_container").shouldHave(text(headerText));
        sleep(1000);
    }


}
