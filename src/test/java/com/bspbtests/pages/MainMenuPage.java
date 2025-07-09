package com.bspbtests.pages;

import com.bspbtests.constants.CommonLocatorTemplates;
import com.bspbtests.pages.baseform.BaseForm;
import org.openqa.selenium.By;

public class MainMenuPage extends BaseForm {

    public MainMenuPage(String elementText, String name) {
        super(By.xpath(String.format(CommonLocatorTemplates.UPPER_MENU_ITEM_WITH_TEXT, elementText)), "Страница " + name);
    }
}
