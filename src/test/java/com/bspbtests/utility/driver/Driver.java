package com.bspbtests.utility.driver;

import com.bspbtests.constants.PathConstants;
import com.utility.files.FilesReader;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class Driver {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver instance() {
        if (driver.get() == null) {
            driver.set(createDriver());
        }
        return driver.get();
    }

    private static WebDriver createDriver() {
        BrowserModel browser = FilesReader.readJson(PathConstants.BROWSER_CONFIG_PATH, BrowserModel.class);
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

    public static void quit() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
