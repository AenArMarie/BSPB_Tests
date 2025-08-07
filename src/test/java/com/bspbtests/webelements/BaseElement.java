package com.bspbtests.webelements;

import com.bspbtests.utility.driver.Driver;
import com.bspbtests.utility.driver.DriverMethods;
import com.bspbtests.utility.ProjectLogger;
import lombok.Getter;
import org.openqa.selenium.*;

/**
 * Абстрактный класс для элемента
 */
@Getter
public abstract class BaseElement {

    private final By locator;
    private final String name;

    /**
     * Конструктор элемента
     * @param elementLocator локатор элемента
     * @param elementName имя элемента
     */
    public BaseElement(By elementLocator, String elementName) {
        locator = elementLocator;
        name = elementName;
    }

    /**
     * Метод, возвращающий объект типа {@link WebElement}, к которому принадлежит данный элемент
     * @return элемент {@link WebElement}
     */
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

    /**
     * Метод, реализующий клик по элементу
     */
    public void click() {
        DriverMethods.getActions().moveToElement(getElement()).perform();
        getElement().click();
    }

    /**
     * Метод, возвращающий текст элемента
     * @return текст элемента в формате {@link String}
     */
    public String getText() {
        return getElement().getText();
    }

    /**
     * Метод, реализующий наведение курсора на элемент
     */
    public void hover() {
        DriverMethods.getActions().moveToElement(getElement()).perform();
    }

    /**
     * Метод, возвращающий значение атрибута элемента
     * @param attribute имя атрибута
     * @return значение атрибута в формате {@link String}
     */
    public String getAttribute(String attribute) {
        return getElement().getAttribute(attribute);
    }
}
