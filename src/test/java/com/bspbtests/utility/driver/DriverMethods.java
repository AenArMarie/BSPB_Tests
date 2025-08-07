package com.bspbtests.utility.driver;

import com.bspbtests.constants.FileTypes;
import com.bspbtests.utility.ProjectLogger;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Класс взаимодействия с драйвером
 */
public class DriverMethods {

    @Getter
    private static Wait<WebDriver> wait;

    /**
     * Инициализация таймаута драйвера
     *
     * @param millisOfWaitTime время ожидания в миллисекундах
     */
    public static void initializeWait(int millisOfWaitTime) {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofMillis(millisOfWaitTime));
    }

    /**
     * Метод для получения html-разметки страницы
     *
     * @return разметка страницы в формате {@link String}
     */
    public static String getPageSource() {
        return Driver.getDriver().getPageSource();
    }

    /**
     * Метод создания скриншота
     *
     * @return скриншот в формате {@code byte[]}
     */
    public static byte[] makeScreenshotByteArray() {
        return ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    /**
     * Метод ожидания появления элемента
     *
     * @param locator локатор элемента
     */
    public static void waitForElementToAppear(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            ProjectLogger.error("Элемент не прогрузился за указанное время: " + e.getMessage());
        }
    }

    /**
     * Метод для получения ссылки на видео из Selenoid
     * @return ссылка в формате {@link String}
     */
    public static String getSelenoidVideoLink() {
        String sessionId = ((RemoteWebDriver) Driver.getDriver()).getSessionId().toString();
        return  "http://localhost:8084/video/" + sessionId + FileTypes.MP4;
    }

    /**
     * Метод, возвращающий объект для выполнения действий с браузером
     * @return объект типа {@link Actions}
     */
    public static Actions getActions() {
        return new Actions(Driver.getDriver());
    }
}
