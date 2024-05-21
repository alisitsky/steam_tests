package com.steampowered.store.config;

import com.codeborne.selenide.Configuration;
import com.steampowered.store.config.web.WebConfig;
import io.restassured.RestAssured;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.steampowered.store.utils.RandomUtils.setBrowserLanguage;

public class ProjectConfigurator {

    private final WebConfig webConfig;

    public ProjectConfigurator(WebConfig webConfig) {
        this.webConfig = webConfig;
    }

    public void setWebConfig() {
        RestAssured.baseURI = webConfig.getBaseUrl();
        Configuration.baseUrl = webConfig.getBaseUrl();
        Configuration.browser = webConfig.getBrowser();
        Configuration.browserVersion = webConfig.getBrowserVersion();
        Configuration.browserSize = webConfig.getBrowserSize();
        Configuration.pageLoadStrategy = webConfig.getPageLoadStrategy();
        setBrowserLanguage(webConfig.getBrowserLanguage());
        if (webConfig.getIsRemote()) {
            Configuration.remote = String.valueOf(webConfig.getRemoteUrl());
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }
    }
}
