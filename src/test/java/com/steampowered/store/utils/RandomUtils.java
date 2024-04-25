package com.steampowered.store.utils;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RandomUtils {

    public static void waitForCookie(String cookieName, int timeoutInSeconds) {
        new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(timeoutInSeconds))
                .until(driver -> driver.manage().getCookieNamed(cookieName) != null);
    }

}
