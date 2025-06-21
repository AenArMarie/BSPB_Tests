package com.bspbtests.pages;

import com.bspbtests.constants.ElementsTextConstants;
import com.bspbtests.pages.baseform.BaseForm;
import org.openqa.selenium.By;

public class WhiteNightsInvestmentPage extends BaseForm {

    public WhiteNightsInvestmentPage() {
        super(By.xpath(String.format("//*[contains(@class, 'css-jlri05') and contains(., '%s')]", ElementsTextConstants.WHITE_NIGHTS)), "Страница 'Белые ночи'");
    }
}
