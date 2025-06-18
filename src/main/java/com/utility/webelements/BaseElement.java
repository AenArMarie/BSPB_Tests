package com.utility.webelements;

import com.utility.driver.Driver;
import com.utility.driver.DriverMethods;
import com.utility.logger.ProjectLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

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

    public WebElement getElement()
    {
        WebElement webElement = null;
        try {
            DriverMethods.waitForElementToAppear(getLocator());
            webElement = Driver.instance().findElement(getLocator());
        } catch (NoSuchElementException e) {
            ProjectLogger.error("Элемент " + getName() +" не найден: " + e.getMessage());
        }
        return webElement;
    }

    public void click() {
        getElement().click();
    }

    public String getText() {
        return getElement().getText();
    }

    public WebElement getChildElement(By locator) {
        WebElement webElement = null;
        try {
            webElement = getElement().findElement(locator);
        } catch (NoSuchElementException e) {
            ProjectLogger.error("Элемент не найден: " + e.getMessage());
        }
        return webElement;
    }

    public String getAttribute(String attribute) {
        return getElement().getAttribute(attribute);
    }
}
