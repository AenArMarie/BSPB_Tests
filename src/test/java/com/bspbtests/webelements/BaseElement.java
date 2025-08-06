package com.bspbtests.webelements;

import com.bspbtests.utility.driver.Driver;
import com.bspbtests.utility.driver.DriverMethods;
import com.bspbtests.utility.ProjectLogger;
import lombok.Getter;
import org.openqa.selenium.*;

@Getter
public abstract class BaseElement {

    private final By locator;
    private final String name;

    public BaseElement(By elementLocator, String elementName) {
        locator = elementLocator;
        name = elementName;
    }

    public WebElement getElement() {
        WebElement webElement = null;
        try {
            DriverMethods.waitForElementToAppear(getLocator());
            webElement = Driver.getDriver().findElement(getLocator());
        } catch (NoSuchElementException e) {
            ProjectLogger.error("Элемент " + getName() + " не найден: " + e.getMessage());
        }
        return webElement;
    }

    public void click() {
        DriverMethods.getActions().moveToElement(getElement()).perform();
        getElement().click();
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
