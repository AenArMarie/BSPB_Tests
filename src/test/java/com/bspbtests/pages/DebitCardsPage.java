package com.bspbtests.pages;

import com.bspbtests.constants.CommonLocatorTemplates;
import com.bspbtests.constants.ElementsTextConstants;
import com.bspbtests.pages.baseform.BaseForm;
import com.bspbtests.utility.webelements.Element;
import org.openqa.selenium.By;

public class DebitCardsPage extends BaseForm {

    private Element iCountCardLink = new Element(By.xpath(String.format(CommonLocatorTemplates.CARD_LINK, ElementsTextConstants.ICOUNT_CARD_TEXT)), "Ссылка на карту 'ЯСЧИТАЮ'");

    public DebitCardsPage() {
        super(By.xpath(String.format(CommonLocatorTemplates.CARD_LINK, ElementsTextConstants.ICOUNT_CARD_TEXT)), "Страница дебетовых карт");
    }

    public void clickICountCardLink() {
        iCountCardLink.click();
    }
}
