package com.bspbtests.steps;

import com.bspbtests.constants.CommonLogMessages;
import com.bspbtests.pages.MainPage;
import com.utility.logger.ProjectLogger;
import io.cucumber.java.ru.Дано;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommonSteps {

    private MainPage mainPage;

    @Дано("пользователь находится на главной странице")
    public void userOnMainPage() {
        ProjectLogger.info(CommonLogMessages.MAIN_PAGE_CHECK_LOG);
        mainPage = new MainPage();
        assertTrue(mainPage.isDisplayed(), CommonLogMessages.MAIN_PAGE_NOT_DISPLAYED);
    }
}
