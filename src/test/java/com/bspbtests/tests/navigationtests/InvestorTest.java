package com.bspbtests.tests.navigationtests;

import com.bspbtests.constants.CommonLogMessages;
import com.bspbtests.constants.MainPageMenuItemText;
import com.bspbtests.pages.InvestorPage;
import com.bspbtests.pages.MainPage;
import com.bspbtests.tests.basetest.BaseTest;
import com.utility.logger.ProjectLogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InvestorTest extends BaseTest {

    @Test
    public void investorTest() {
        ProjectLogger.info(CommonLogMessages.MAIN_PAGE_CHECK_LOG);
        MainPage mainPage = new MainPage();
        assertTrue(mainPage.isDisplayed(), CommonLogMessages.MAIN_PAGE_NOT_DISPLAYED);

        ProjectLogger.info("Переход на страницу 'Инвесторам'");
        mainPage.clickTopMenuItemByText(MainPageMenuItemText.INVESTOR);
        InvestorPage investorPage = new InvestorPage();
        ProjectLogger.info("Проверка отображения страницы 'Инвесторам'");
        assertTrue(investorPage.isDisplayed(), "Страница 'Инвесторам' не отображена");
    }
}
