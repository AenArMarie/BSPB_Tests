package com.bspbtests.pages.baseform;

import com.bspbtests.utility.webelements.BaseElement;
import com.bspbtests.utility.webelements.Element;
import org.openqa.selenium.By;

public abstract class BaseForm {

    private BaseElement uniqueElement;

    protected BaseForm(By locator, String name) {
        uniqueElement = new Element(locator, name);
    }

    public boolean isDisplayed() {
        return uniqueElement.getElement().isDisplayed();
    }
}
