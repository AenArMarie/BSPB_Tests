package com.bspbtests.tests.navigationtests;

import com.bspbtests.constants.CommonLogMessages;
import com.bspbtests.constants.MainPageMenuItemText;
import com.bspbtests.pages.MainPage;
import com.bspbtests.pages.VedPage;
import com.bspbtests.tests.basetest.BaseTest;
import com.utility.logger.ProjectLogger;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class VedTest extends BaseTest {

    @Test
    @Disabled
    public void vedTest() {
        ProjectLogger.info(CommonLogMessages.MAIN_PAGE_CHECK_LOG);
        MainPage mainPage = new MainPage();
        assertTrue(mainPage.isDisplayed(), CommonLogMessages.MAIN_PAGE_NOT_DISPLAYED);

        ProjectLogger.info("Переход на страницу 'ВЭД'");
        mainPage.clickTopMenuItemByText(MainPageMenuItemText.VED);
        VedPage vedPage = new VedPage();
        ProjectLogger.info("Проверка отображения страницы 'ВЭД'");
        assertTrue(vedPage.isDisplayed(), "Страница 'ВЭД' не отображена");
    }
}
