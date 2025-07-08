package com.bspbtests.tests.navigationtests;

import com.bspbtests.constants.CommonLogMessages;
import com.bspbtests.constants.MainPageMenuItemText;
import com.bspbtests.pages.MainPage;
import com.bspbtests.pages.PrivateBankingPage;
import com.bspbtests.tests.basetest.BaseTest;
import com.utility.logger.ProjectLogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrivateBankingTest extends BaseTest {

    @Test
    public void privateBankingTest() {
        ProjectLogger.info(CommonLogMessages.MAIN_PAGE_CHECK_LOG);
        MainPage mainPage = new MainPage();
        assertTrue(mainPage.isDisplayed(), CommonLogMessages.MAIN_PAGE_NOT_DISPLAYED);

        ProjectLogger.info("Переход на страницу 'Private Banking'");
        mainPage.clickTopMenuItemByText(MainPageMenuItemText.PRIVATE_BANKING);
        PrivateBankingPage privateBankingPage = new PrivateBankingPage();
        ProjectLogger.info("Проверка отображения страницы 'Private Banking'");
        assertTrue(privateBankingPage.isDisplayed(), "Страница 'Private Banking' не отображена");
    }
}
