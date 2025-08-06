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

public class DriverMethods {

    @Getter
    private static Wait<WebDriver> wait;

    public static void initializeWait(int millisOfWaitTime) {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofMillis(millisOfWaitTime));
    }

    public static String getPageSource() {
        return Driver.getDriver().getPageSource();
    }

    public static byte[] makeScreenshotByteArray() {
        return ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static void waitForElementToAppear(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            ProjectLogger.error("Элемент не прогрузился за указанное время: " + e.getMessage());
        }
    }

    public static String getSelenoidVideoLink() {
        String sessionId = ((RemoteWebDriver) Driver.getDriver()).getSessionId().toString();
        return  "http://localhost:8084/video/" + sessionId + FileTypes.MP4;
    }

    public static Actions getActions() {
        return new Actions(Driver.getDriver());
    }
}
