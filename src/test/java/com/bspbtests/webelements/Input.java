package com.bspbtests.webelements;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

/**
 * Поле ввода
 */
public class Input extends BaseElement {

    /**
     * @see BaseElement
     */
    public Input(By elementLocator, String elementName) { //TODO джавадок с дурным тоном
        super(elementLocator, elementName);
    }

    /**
     * Ввод значения в поле ввода
     * @param text вводимое значение
     */
    public void setInput(String text) {
        getElement().sendKeys(Keys.CONTROL + "a");
        getElement().sendKeys(text);
    }
}
