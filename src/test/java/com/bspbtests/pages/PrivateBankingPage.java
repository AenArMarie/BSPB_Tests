package com.bspbtests.pages;

import com.bspbtests.pages.baseform.BaseForm;
import com.bspbtests.webelements.Element;
import org.openqa.selenium.By;

public class PrivateBankingPage extends BaseForm {

    public static boolean isDisplayed() {
        return new Element(By.className("t-cover__wrapper"), "Обложка страницы").getElement().isDisplayed();
    }
}
