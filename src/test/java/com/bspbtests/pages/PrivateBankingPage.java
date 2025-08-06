package com.bspbtests.pages;

import com.bspbtests.pages.baseform.IBaseForm;
import com.bspbtests.webelements.Element;
import org.openqa.selenium.By;

public class PrivateBankingPage implements IBaseForm {

    public static boolean isDisplayed() {
        return new Element(By.className("t-cover__wrapper"), "Обложка страницы").getElement().isDisplayed();
    }
}
