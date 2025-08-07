package com.bspbtests.pages;

import com.bspbtests.constants.CommonLocatorTemplates;
import com.bspbtests.constants.ElementsTextConstants;
import com.bspbtests.pages.baseform.IBaseForm;
import com.bspbtests.webelements.Element;
import org.openqa.selenium.By;

/**
 * Страница дебетовых кард
 */
public class DebitCardsPage implements IBaseForm {

    private static final Element iCountCardLink = new Element(By.xpath(String.format(CommonLocatorTemplates.CARD_LINK, ElementsTextConstants.ICOUNT_CARD_TEXT)), "Ссылка на карту 'ЯСЧИТАЮ'");

    /**
     * @see IBaseForm#isDisplayed()
     */
    public static boolean isDisplayed() {
        return new Element(By.xpath(String.format(CommonLocatorTemplates.CARD_LINK, ElementsTextConstants.ICOUNT_CARD_TEXT)), "Страница дебетовых карт").getElement().isDisplayed();
    }

    /**
     * Нажатие на карту "ЯСчитаю"
     */
    public static void clickICountCardLink() {
        iCountCardLink.click();
    }
}
