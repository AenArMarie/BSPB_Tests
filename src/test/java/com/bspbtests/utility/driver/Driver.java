package com.bspbtests.utility.driver;

import com.bspbtests.constants.PathConstants;
import com.bspbtests.utility.ProjectLogger;
import com.bspbtests.utility.dataprocessing.FilesReader;
import com.bspbtests.utility.enums.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Драйвер
 */
public class Driver {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private static HashMap<String, Object> createSelenoidOptions() {
        HashMap<String, Object> options = new HashMap<>();
        options.put("name", "Test badge...");
        options.put("sessionTimeout", "15m");
        options.put("env", List.of("TZ=UTC"));
        options.put("labels", Map.of("manual", "true"));
        options.put("enableVideo", true);
        return options;
    }

    /**
     * Метод, возвращающий драйвер и создающий его в случае отсутствия
     * @return драйвер текущей сессии типа {@link WebDriver}
     */
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
            case "false" -> BrowserType.fromString(browser.getName()).createLocalDriver();
            //case null, default -> createRemoteWebDriver(browser);
            case null, default -> BrowserType.fromString(browser.getName()).createLocalDriver();
        };
    }

    private static WebDriver createRemoteWebDriver(BrowserModel browser) {
        AbstractDriverOptions<?> options = BrowserType.fromString(browser.getName()).createRemoteOptions();
        options.setCapability("selenoid:options", createSelenoidOptions());
        return RemoteWebDriver.builder().
                address(URI.create("http://localhost:4444/wd/hub")).
                oneOf(options).
                build();
    }

    /**
     * Метод, завершающий работу драйвера
     */
    public static void quit() {
        if (driver.get() != null) {
            //driver.get().quit();
            driver.remove();
        }
    }
}
