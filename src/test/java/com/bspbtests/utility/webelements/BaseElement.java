package com.bspbtests.utility.webelements;

import com.bspbtests.utility.driver.Driver;
import com.bspbtests.utility.driver.DriverMethods;
import com.utility.logger.ProjectLogger;
import org.openqa.selenium.*;

public abstract class BaseElement {

    private By locator;
    private String name;

    public BaseElement(By elementLocator, String elementName) {
        locator = elementLocator;
        name = elementName;
    }

    public By getLocator() {
        return locator;
    }

    public String getName() {
        return name;
    }

    public WebElement getElement() {
        WebElement webElement = null;
        try {
            DriverMethods.waitForElementToAppear(getLocator());
            webElement = Driver.instance().findElement(getLocator());
        } catch (NoSuchElementException e) {
            ProjectLogger.error("Элемент " + getName() + " не найден: " + e.getMessage());
        }
        return webElement;
    }

    public void click() {
        DriverMethods.getWait().until(d -> {
            try {
                DriverMethods.getActions().moveToElement(getElement()).perform();
                getElement().click();
                return true;
            } catch (ElementClickInterceptedException ignored) {
            }
            return false;
        });

    }

    public String getText() {
        return getElement().getText();
    }

    public void hover() {
        DriverMethods.getActions().moveToElement(getElement()).perform();
    }

    public String getAttribute(String attribute) {
        return getElement().getAttribute(attribute);
    }
}
