package com.bspbtests.pages;

import com.bspbtests.constants.CommonLocatorTemplates;
import com.bspbtests.constants.ElementsTextConstants;
import com.bspbtests.pages.baseform.BaseForm;
import org.openqa.selenium.By;

public class InvestorPage extends BaseForm {

    public InvestorPage() {
        super(By.xpath(String.format(CommonLocatorTemplates.UPPER_MENU_ITEM_WITH_TEXT, ElementsTextConstants.INVESTOR_PAGE_UNIQUE_TEXT)), "Страница инвесторов'");
    }
}
