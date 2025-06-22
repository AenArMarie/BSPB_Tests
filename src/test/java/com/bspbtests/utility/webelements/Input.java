package com.bspbtests.utility.webelements;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class Input extends BaseElement {

    public Input(By elementLocator, String elementName) {
        super(elementLocator, elementName);
    }

    public void setInput(String text) {
        getElement().sendKeys(Keys.CONTROL + "a");
        getElement().sendKeys(text);
    }
}
