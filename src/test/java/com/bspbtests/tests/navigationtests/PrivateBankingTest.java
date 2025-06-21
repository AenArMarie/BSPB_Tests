package com.bspbtests.tests.navigationtests;

import com.bspbtests.constants.CommonLogMessages;
import com.bspbtests.constants.MainPageMenuItemText;
import com.bspbtests.pages.MainPage;
import com.bspbtests.pages.PrivateBankingPage;
import com.bspbtests.tests.basetest.BaseTest;
import com.utility.logger.ProjectLogger;
import org.junit.Assert;
import org.junit.Test;

public class PrivateBankingTest extends BaseTest {

    @Test
    public void privateBankingTest() {
        ProjectLogger.info(CommonLogMessages.MAIN_PAGE_CHECK_LOG);
        MainPage mainPage = new MainPage();
        Assert.assertTrue(CommonLogMessages.MAIN_PAGE_NOT_DISPLAYED, mainPage.isDisplayed());

        ProjectLogger.info("Переход на страницу 'Private Banking'");
        mainPage.clickTopMenuItemByText(MainPageMenuItemText.PRIVATE_BANKING);
        PrivateBankingPage privateBankingPage = new PrivateBankingPage();
        ProjectLogger.info("Проверка отображения страницы 'Private Banking'");
        Assert.assertTrue("Страница 'Private Banking' не отображена", privateBankingPage.isDisplayed());
    }
}
