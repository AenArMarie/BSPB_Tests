package com.bspbtests.pages;

import com.bspbtests.constants.CommonLocatorTemplates;
import com.bspbtests.constants.ElementsTextConstants;
import com.bspbtests.pages.baseform.IBaseForm;
import com.bspbtests.webelements.Element;
import org.openqa.selenium.By;

public class DebitCardsPage implements IBaseForm {

    private static final Element iCountCardLink = new Element(By.xpath(String.format(CommonLocatorTemplates.CARD_LINK, ElementsTextConstants.ICOUNT_CARD_TEXT)), "Ссылка на карту 'ЯСЧИТАЮ'");

    public static boolean isDisplayed() {
        return new Element(By.xpath(String.format(CommonLocatorTemplates.CARD_LINK, ElementsTextConstants.ICOUNT_CARD_TEXT)), "Страница дебетовых карт").getElement().isDisplayed();
    }

    public static void clickICountCardLink() {
        iCountCardLink.click();
    }
}
