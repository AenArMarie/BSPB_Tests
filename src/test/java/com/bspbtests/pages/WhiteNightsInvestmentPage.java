package com.bspbtests.pages;

import com.bspbtests.constants.ElementsTextConstants;
import com.bspbtests.pages.baseform.IBaseForm;
import com.bspbtests.webelements.Element;
import org.openqa.selenium.By;

public class WhiteNightsInvestmentPage implements IBaseForm {

    public static boolean isDisplayed() {
        return new Element(By.xpath(String.format("//*[contains(@class, 'css-jlri05') and contains(., '%s')]", ElementsTextConstants.WHITE_NIGHTS)), "Страница 'Белые ночи'").getElement().isDisplayed();
    }
}
