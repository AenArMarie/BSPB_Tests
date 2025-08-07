package com.bspbtests.utility.enums;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

import java.util.Arrays;

/**
 * Enum для разных типов браузера
 */
public enum BrowserType {
    CHROME {
        @Override
        public WebDriver createLocalDriver() {
            return new ChromeDriver();
        }

        @Override
        public AbstractDriverOptions<?> createRemoteOptions() {
            ChromeOptions options = new ChromeOptions();
            options.setCapability("browserVersion", "128.0");
            return options;
        }
    },

    FIREFOX {
        @Override
        public WebDriver createLocalDriver() {
            return new FirefoxDriver();
        }

        @Override
        public AbstractDriverOptions<?> createRemoteOptions() {
            FirefoxOptions options = new FirefoxOptions();
            options.setCapability("browserVersion", "125.0");
            return options;
        }
    };

    /**
     * Метод для создания локального драйвера
     *
     * @return веб драйвер в формате {@link WebDriver}
     */
    public abstract WebDriver createLocalDriver();

    /**
     * Метод для создания настроек удаленного браузера
     * @return настройки браузера в формате {@link AbstractDriverOptions<>}
     */
    public abstract AbstractDriverOptions<?> createRemoteOptions();

    /**
     * Метод для поиска браузера среди enum-объектов
     * @param name название браузера
     * @return enum нужного браузер
     */
    public static BrowserType fromString(String name) {
        return Arrays.stream(values())
                .filter(b -> b.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Неизвестный браузер: " + name));
    }
}

