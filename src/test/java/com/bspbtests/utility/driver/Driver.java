package com.bspbtests.utility.driver;

import com.bspbtests.constants.PathConstants;
import com.utility.files.FilesReader;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Driver {

    private static final ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

    public static RemoteWebDriver instance() {
        if (driver.get() == null) {
            try {
                driver.set(createDriver());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        return driver.get();
    }

    private static RemoteWebDriver createDriver() throws MalformedURLException {
        BrowserModel browser = FilesReader.readJson(PathConstants.BROWSER_CONFIG_PATH, BrowserModel.class);
        AbstractDriverOptions options;
        switch (browser.getName()) {
            case "chrome" -> {
                options = new ChromeOptions();
                options.setCapability("browserVersion", "128.0");
            }
            case "firefox" -> {
                options = new FirefoxOptions();
                options.setCapability("browserVersion", "125.0");
            }
            default ->
                throw new IllegalArgumentException("Неподдерживаемый браузер: " + browser.getName());
        }
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("name", "Test badge...");
            put("sessionTimeout", "15m");
            put("env", new ArrayList<String>() {{
                add("TZ=UTC");
            }});
            put("labels", new HashMap<String, Object>() {{
                put("manual", "true");
            }});
            put("enableVideo", true);
        }});
        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
    }

    public static void quit() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
