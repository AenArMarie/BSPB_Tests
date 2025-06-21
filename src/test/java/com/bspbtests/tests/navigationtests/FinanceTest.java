package com.bspbtests.tests.navigationtests;

import com.bspbtests.constants.CommonLogMessages;
import com.bspbtests.constants.MainPageMenuItemText;
import com.bspbtests.pages.FinancePage;
import com.bspbtests.pages.MainPage;
import com.bspbtests.tests.basetest.BaseTest;
import com.utility.logger.ProjectLogger;
import org.junit.Assert;
import org.junit.Test;

public class FinanceTest extends BaseTest {

    @Test
    public void financeTest() {
        ProjectLogger.info(CommonLogMessages.MAIN_PAGE_CHECK_LOG);
        MainPage mainPage = new MainPage();
        Assert.assertTrue(CommonLogMessages.MAIN_PAGE_NOT_DISPLAYED, mainPage.isDisplayed());

        ProjectLogger.info("Переход на страницу 'Финансовые рынки'");
        mainPage.clickTopMenuItemByText(MainPageMenuItemText.FINANCE);
        FinancePage financePage = new FinancePage();
        ProjectLogger.info("Проверка отображения страницы 'Финансовые рынки'");
        Assert.assertTrue("Страница 'Финансовые рынки' не отображена", financePage.isDisplayed());
    }
}
