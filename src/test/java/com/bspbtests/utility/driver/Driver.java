package com.bspbtests.utility.driver;

import com.bspbtests.constants.PathConstants;
import com.utility.files.FilesReader;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Driver {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver instance() {
        if (driver.get() == null) {
            try {
                driver.set(createDriver());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }

        }
        return driver.get();
    }

    private static WebDriver createDriver() throws MalformedURLException {
        BrowserModel browser = FilesReader.readJson(PathConstants.BROWSER_CONFIG_PATH, BrowserModel.class);
        switch (browser.getName()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                /*chromeOptions.addArguments(browser.getMode());
                chromeOptions.addArguments(browser.getLanguage());*/
                ChromeOptions options = new ChromeOptions();
                options.setCapability("browserVersion", "128.0");
                options.setCapability("selenoid:options", new HashMap<String, Object>() {{
                    /* How to add test badge */
                    put("name", "Test badge...");

                    /* How to set session timeout */
                    put("sessionTimeout", "15m");

                    /* How to set timezone */
                    put("env", new ArrayList<String>() {{
                        add("TZ=UTC");
                    }});

                    /* How to add "trash" button */
                    put("labels", new HashMap<String, Object>() {{
                        put("manual", "true");
                    }});

                    /* How to enable video recording */
                    put("enableVideo", true);
                }});
                return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
                //return new ChromeDriver(chromeOptions);
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
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
