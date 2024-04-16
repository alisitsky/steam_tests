package com.steampowered.store.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
//        ProjectConfigurator projectConfigurator = new ProjectConfigurator(webConfig);
//        projectConfigurator.setWebConfig();

        Configuration.baseUrl = "https://store.steampowered.com/";
        Configuration.browser = "chrome";
//        Configuration.browserVersion = webConfig.browserVersion();
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @AfterEach
    void afterEach() {
//        attachScreenshotAs("Last Step Screenshot");
//        attachPageSource();
//        attachBrowserConsoleLogs();
//        attachVideo();
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        Selenide.closeWebDriver();
    }

}
