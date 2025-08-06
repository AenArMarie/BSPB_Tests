package com.bspbtests.pages;

import com.bspbtests.constants.CommonLocatorTemplates;
import com.bspbtests.pages.baseform.ITemplatedForm;
import com.bspbtests.webelements.Element;
import org.openqa.selenium.By;

public class MainMenuPage implements ITemplatedForm {

    public static boolean isDisplayed(String elementText, String name) {
        return new Element(By.xpath(String.format(CommonLocatorTemplates.UPPER_MENU_ITEM_WITH_TEXT, elementText)), "Страница " + name).getElement().isDisplayed();
    }
}
