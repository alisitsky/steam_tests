package com.steampowered.store.tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.steampowered.store.config.ConfigReader;
import com.steampowered.store.config.ProjectConfigurator;
import com.steampowered.store.config.web.WebConfig;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.steampowered.store.utils.AttachUtils.*;

public class TestBase {

    private static final WebConfig webConfig = ConfigReader.Instance.read();

    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        ProjectConfigurator projectConfigurator = new ProjectConfigurator(webConfig);
        projectConfigurator.setWebConfig();
    }

    @AfterEach
    void attachments() {
        attachScreenshotAs("Last Step Screenshot");
        attachPageSource();
        attachBrowserConsoleLogs();
        attachVideo();
    }

    @AfterEach
    void afterEach(){
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        Selenide.closeWebDriver();
        RestAssured.reset();
    }
}
