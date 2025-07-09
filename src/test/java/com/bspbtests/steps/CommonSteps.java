package com.bspbtests.steps;

import com.bspbtests.constants.CommonLogMessages;
import com.bspbtests.pages.MainPage;
import com.utility.logger.ProjectLogger;
import io.cucumber.java.ru.Дано;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommonSteps {

    @Дано("пользователь находится на главной странице")
    public void userOnMainPage() {
        ProjectLogger.info(CommonLogMessages.MAIN_PAGE_CHECK_LOG);
        MainPage mainPage = new MainPage();
        mainPage.isDisplayed();
    }

}
