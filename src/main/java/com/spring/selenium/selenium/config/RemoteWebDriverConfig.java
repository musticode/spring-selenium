package com.spring.selenium.selenium.config;


import com.spring.selenium.selenium.service.BrowserOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.net.URL;

@Configuration
@Lazy
public class RemoteWebDriverConfig {

    @Value("${selenium.grid.url}")
    private URL url;


    @Bean
    @ConditionalOnProperty(name = "browser", havingValue = "firefox-grid")
    public WebDriver remoteFirefoxDriver(){
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        return new RemoteWebDriver(this.url, firefoxOptions);
    }

    @Bean
    @ConditionalOnProperty(name = "browser", havingValue = "chrome-grid")
    public WebDriver remoteChromeDriver(){
        BrowserOptions browserOptions = new BrowserOptions();
        return new RemoteWebDriver(this.url, browserOptions.getChromeOptions());
    }

    @Bean
    @ConditionalOnProperty(name = "browser", havingValue = "edge-grid")
    public WebDriver remoteEdgeDriver(){
        EdgeOptions edgeOptions = new EdgeOptions();
        return new RemoteWebDriver(this.url, edgeOptions);
    }




}
