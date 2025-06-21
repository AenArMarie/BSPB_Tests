package com.utility.driver;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class Driver {

    private static volatile WebDriver driver;

    public static void initialize(BrowserModel browser) {
        if (driver == null) {
            synchronized (Driver.class) {
                if (driver == null) {
                    driver = createDriver(browser);
                }
            }
        } else {
            throw new IllegalStateException("Driver уже инициализирован");
        }
    }

    private static WebDriver createDriver(BrowserModel browser) {
        switch (browser.getName()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
                chromeOptions.addArguments(browser.getMode());
                chromeOptions.addArguments(browser.getLanguage());
                return new ChromeDriver(chromeOptions);
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
                edgeOptions.addArguments(browser.getMode());
                edgeOptions.addArguments(browser.getLanguage());
                return new EdgeDriver(edgeOptions);
            default:
                throw new IllegalArgumentException("Неподдерживаемый браузер: " + browser.getName());
        }
    }

    public static WebDriver instance() {
        if (driver != null)
            return driver;
        else throw new IllegalStateException("Driver не инициализирован");
    }

    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
