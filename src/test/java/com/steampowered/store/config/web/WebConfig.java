package com.steampowered.store.config.web;

import org.aeonbits.owner.Config;

import java.net.URL;

@Config.Sources({
        "classpath:webDriver.properties"
})

public interface WebConfig extends Config  {

    @Key("baseUrl")
    String getBaseUrl();

    @Key("browser")
    String getBrowser();

    @Key("browserVersion")
    String getBrowserVersion();

    @Key("browserSize")
    String getBrowserSize();

    @Key("browserLanguage")
    String getBrowserLanguage();

    @Key("pageLoadStrategy")
    String getPageLoadStrategy();

    @Key("isRemote")
    boolean getIsRemote();

    @Key("remoteUrl")
    URL getRemoteUrl();
}
