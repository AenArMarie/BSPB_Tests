package com.bspbtests.utility.driver;

import com.utility.logger.ProjectLogger;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class DriverMethods {

    @Getter
    private static Wait<WebDriver> wait;

    public static void initializeWait(int millisOfWaitTime) {
        wait = new WebDriverWait(Driver.instance(), Duration.ofMillis(millisOfWaitTime));
    }

    public static void makeScreenshot(String testResultName, String screenshotPath, String screenshotType) {
        File screenshot = ((TakesScreenshot) Driver.instance()).getScreenshotAs(OutputType.FILE);
        File directory = new File(screenshotPath);
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                ProjectLogger.error("Не удалось создать папку для скриншотов");
            }
        }
        try {
            FileHandler.copy(screenshot, new File(screenshotPath + testResultName + screenshotType));
            ProjectLogger.info("Скриншот сохранен: " + screenshotPath + screenshotType);
        } catch (IOException e) {
            ProjectLogger.error("Не удалось создать файл скриншота");
        }
    }

    public static byte[] makeScreenshotByteArray() {
        return ((TakesScreenshot) Driver.instance()).getScreenshotAs(OutputType.BYTES);
    }

    public static void waitForElementToAppear(By locator) {
        try {
            wait.until(d -> Driver.instance().findElement(locator) != null &&
                    Driver.instance().findElement(locator).isDisplayed());
        } catch (TimeoutException e) {
            ProjectLogger.error("Элемент не прогрузился за указанное время: " + e.getMessage());
        }
    }

    public static Actions getActions() {
        return new Actions(Driver.instance());
    }
}
