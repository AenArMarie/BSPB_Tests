package com.bspbtests.tests.navigationtests;

import com.bspbtests.constants.CommonLogMessages;
import com.bspbtests.constants.MainPageMenuItemText;
import com.bspbtests.pages.BusinessPage;
import com.bspbtests.pages.MainPage;
import com.bspbtests.tests.basetest.BaseTest;
import com.utility.logger.ProjectLogger;
import org.junit.Assert;
import org.junit.Test;

public class BusinessTest extends BaseTest {

    @Test
    public void businessTest() {
        ProjectLogger.info(CommonLogMessages.MAIN_PAGE_CHECK_LOG);
        MainPage mainPage = new MainPage();
        Assert.assertTrue(CommonLogMessages.MAIN_PAGE_NOT_DISPLAYED, mainPage.isDisplayed());

        ProjectLogger.info("Переход на страницу 'Бизнесу'");
        mainPage.clickTopMenuItemByText(MainPageMenuItemText.BUSINESS);
        ProjectLogger.info("Проверка отображения страницы 'Бизнесу'");
        BusinessPage businessPage = new BusinessPage();
        Assert.assertTrue("Страница 'Бизнесу' не отображена", businessPage.isDisplayed());
    }
}
