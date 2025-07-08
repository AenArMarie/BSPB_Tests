package com.bspbtests.tests.navigationtests;

import com.bspbtests.constants.CommonLogMessages;
import com.bspbtests.constants.MainPageMenuItemText;
import com.bspbtests.pages.BusinessPage;
import com.bspbtests.pages.MainPage;
import com.bspbtests.tests.basetest.BaseTest;
import com.utility.logger.ProjectLogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BusinessTest extends BaseTest {

    @Test
    public void businessTest() {
        ProjectLogger.info(CommonLogMessages.MAIN_PAGE_CHECK_LOG);
        MainPage mainPage = new MainPage();
        assertTrue(mainPage.isDisplayed(), CommonLogMessages.MAIN_PAGE_NOT_DISPLAYED);

        ProjectLogger.info("Переход на страницу 'Бизнесу'");
        mainPage.clickTopMenuItemByText(MainPageMenuItemText.BUSINESS);
        ProjectLogger.info("Проверка отображения страницы 'Бизнесу'");
        BusinessPage businessPage = new BusinessPage();
        assertTrue(businessPage.isDisplayed(), "Страница 'Бизнесу' не отображена");
    }
}
