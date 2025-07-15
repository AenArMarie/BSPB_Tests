package com.bspbtests.utility.driver;

import com.bspbtests.constants.FileTypes;
import com.utility.logger.ProjectLogger;
import io.qameta.allure.Allure;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
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

    public static InputStream getSelenoidVideo() {
        String sessionId = Driver.instance().getSessionId().toString();
        String videoUrl = "http://localhost:8084/video" + sessionId + FileTypes.MP4;

        try (InputStream is = new URL(videoUrl).openStream()) {
            return is;
        } catch (Exception e) {
            return null;
        }
    }

    public static Actions getActions() {
        return new Actions(Driver.instance());
    }
}
