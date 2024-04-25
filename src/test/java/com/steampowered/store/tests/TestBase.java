package com.steampowered.store.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.steampowered.store.utils.AttachUtils.*;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
//        ProjectConfigurator projectConfigurator = new ProjectConfigurator(webConfig);
//        projectConfigurator.setWebConfig();

        RestAssured.baseURI = "https://store.steampowered.com";
        Configuration.baseUrl = "https://store.steampowered.com";
        Configuration.browser = "chrome";
//        Configuration.browserVersion = webConfig.browserVersion();
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";

//        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";  // String.valueOf(webConfig.remoteUrl());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void afterEach() {
        attachScreenshotAs("Last Step Screenshot");
        attachPageSource();
        attachBrowserConsoleLogs();
        attachVideo();
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        Selenide.closeWebDriver();
    }

}
