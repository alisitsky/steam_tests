package com.steampowered.store.config.web;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

import java.net.URL;

@Sources({
        "classpath:${environment}.properties"
})
public interface WebConfig extends Config  {

    @Key("baseUrl")
    @DefaultValue("https://store.steampowered.com")
    String getBaseUrl();

    @Key("browser")
    @DefaultValue("chrome")
    String getBrowser();

    @Key("browserVersion")
    @DefaultValue("100.0")
    String getBrowserVersion();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String getBrowserSize();

    @Key("pageLoadStrategy")
    @DefaultValue("eager")
    String getPageLoadStrategy();

    @Key("isRemote")
    @DefaultValue("false")
    boolean getIsRemote();

    @Key("remoteUrl")
    @DefaultValue("")
    URL getRemoteUrl();
}
