package com.bspbtests.utility.driver;

import com.bspbtests.constants.PathConstants;
import com.bspbtests.utility.FilesReader;
import com.bspbtests.utility.ProjectLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class Driver {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private static final Map<String, Supplier<AbstractDriverOptions<?>>> REMOTE_WEB_DRIVER_OPTIONS_FACTORY =
            Map.of(
                    "chrome", () -> {
                        var opts = new ChromeOptions();
                        opts.setCapability("browserVersion", "128.0");
                        return opts;
                    },
                    "firefox", () -> {
                        var opts = new FirefoxOptions();
                        opts.setCapability("browserVersion", "125.0");
                        return opts;
                    }
            );

    private static final Map<String, Supplier<WebDriver>> LOCAL_WEB_DRIVER_FACTORY =
            Map.of(
                    "chrome", ChromeDriver::new,
                    "firefox", FirefoxDriver::new
            );

    private static final HashMap<String, Object> SELENOID_OPTIONS =
            new HashMap<>() {{
                put("name", "Test badge...");
                put("sessionTimeout", "15m");
                put("env", new ArrayList<String>() {{
                    add("TZ=UTC");
                }});
                put("labels", new HashMap<String, Object>() {{
                    put("manual", "true");
                }});
                put("enableVideo", true);
            }};

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            driver.set(createDriver());
        }
        return driver.get();
    }

    private static WebDriver createDriver() {
        BrowserModel browser = FilesReader.readJson(PathConstants.BROWSER_CONFIG_PATH, BrowserModel.class);
        if (browser == null || browser.getName() == null) {
            ProjectLogger.error("Ошибка при чтении конфигурации браузера");
            throw new RuntimeException();
        }
        return switch (System.getenv("remoteDriver")) {
            case "false" -> LOCAL_WEB_DRIVER_FACTORY.get(browser.getName()).get();
            case null, default -> createRemoteWebDriver(browser);
        };
    }

    private static WebDriver createRemoteWebDriver(BrowserModel browser) {
        var supplier = REMOTE_WEB_DRIVER_OPTIONS_FACTORY.get(browser.getName().toLowerCase());
        if (supplier == null) {
            throw new IllegalArgumentException(
                    "Неподдерживаемый браузер: " + browser.getName()
            );
        }
        AbstractDriverOptions<?> options = supplier.get();
        options.setCapability("selenoid:options", SELENOID_OPTIONS);
        return RemoteWebDriver.builder().
                address(URI.create("http://localhost:4444/wd/hub")).
                oneOf(options).
                build();
    }

    public static void quit() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
