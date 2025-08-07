package com.bspbtests.webelements;

import org.openqa.selenium.By;

/**
 * Класс элемента, реализующий абстрактный (используется для проверки отображения страниц)
 */
public class Element extends BaseElement {

    /**
     * @see BaseElement
     */
    public Element(By elementLocator, String elementName) {
        super(elementLocator, elementName);
    }
}
