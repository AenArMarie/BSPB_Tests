package com.utility.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Driver {

    private static WebDriver driver;

    public static void initialize(BrowserModel browser) {
        if (driver == null) {
            synchronized (Driver.class) {
                switch(browser.getBrowserName()) {
                    case "chrome" :
                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.addArguments(browser.getBrowserMode());
                        chromeOptions.addArguments(browser.getBrowserLanguage());
                        driver = new ChromeDriver(chromeOptions);
                        break;
                    case "firefox" :
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        firefoxOptions.addArguments(browser.getBrowserMode());
                        firefoxOptions.addArguments(browser.getBrowserLanguage());
                        driver = new FirefoxDriver(firefoxOptions);
                        break;
                    case "edge" :
                        EdgeOptions edgeOptions = new EdgeOptions();
                        edgeOptions.addArguments(browser.getBrowserMode());
                        edgeOptions.addArguments(browser.getBrowserLanguage());
                        driver = new EdgeDriver(edgeOptions);
                        break;
                    default:
                        throw new IllegalArgumentException("Неподдерживаемый браузер: " + browser.getBrowserName());
                }
                driver.manage().window().maximize();
            }
        }
        else throw new IllegalArgumentException("Браузер уже инициализирован");
    }

    public static WebDriver instance() {
        if (driver == null)
            return driver;
        else throw new IllegalArgumentException("Браузер не инициализирован");
    }

    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
