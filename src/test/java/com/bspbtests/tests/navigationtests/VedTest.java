package com.bspbtests.tests.navigationtests;

import com.bspbtests.constants.CommonLogMessages;
import com.bspbtests.constants.MainPageMenuItemText;
import com.bspbtests.pages.MainPage;
import com.bspbtests.pages.VedPage;
import com.bspbtests.tests.basetest.BaseTest;
import com.utility.logger.ProjectLogger;
import org.junit.Assert;
import org.junit.Test;

public class VedTest extends BaseTest {

    @Test
    public void vedTest() {
        ProjectLogger.info(CommonLogMessages.MAIN_PAGE_CHECK_LOG);
        MainPage mainPage = new MainPage();
        Assert.assertTrue(CommonLogMessages.MAIN_PAGE_NOT_DISPLAYED, mainPage.isDisplayed());

        ProjectLogger.info("Переход на страницу 'ВЭД'");
        mainPage.clickTopMenuItemByText(MainPageMenuItemText.VED);
        VedPage vedPage = new VedPage();
        ProjectLogger.info("Проверка отображения страницы 'ВЭД'");
        Assert.assertTrue("Страница 'ВЭД' не отображена", vedPage.isDisplayed());
    }
}
