package com.bspbtests.utility.driver;

import com.utility.logger.ProjectLogger;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

    public static String getPageSource() {
        return Driver.instance().getPageSource();
    }

    public static byte[] makeScreenshotByteArray() {
        return ((TakesScreenshot) Driver.instance()).getScreenshotAs(OutputType.BYTES);
    }

    public static void waitForElementToAppear(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(Driver.instance(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            ProjectLogger.error("Элемент не прогрузился за указанное время: " + e.getMessage());
        }
    }

    public static Actions getActions() {
        return new Actions(Driver.instance());
    }
}
