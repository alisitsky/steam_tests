package com.steampowered.store.utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class RandomUtils {

    public static void waitForCookie(String cookieName, int timeoutInSeconds) {
        new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(timeoutInSeconds))
                .until(driver -> driver.manage().getCookieNamed(cookieName) != null);
    }

    public static void setBrowserLanguage(String lang) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=" + lang);
        Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
    }

    public static String getCookieValue(String cookieName){
        return getWebDriver().manage().getCookieNamed(cookieName).getValue();
    }

    public static String getCookieValue(String cookieName, int secondsPolling){
        new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(secondsPolling))
                .until(driver -> driver.manage().getCookieNamed(cookieName) != null);
        return getWebDriver().manage().getCookieNamed(cookieName).getValue();
    }

}
