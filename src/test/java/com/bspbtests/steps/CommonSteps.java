package com.bspbtests.steps;

import com.bspbtests.constants.CommonLogMessages;
import com.bspbtests.pages.MainPage;
import com.bspbtests.utility.ProjectLogger;
import io.cucumber.java.ru.Дано;
import org.assertj.core.api.Assumptions;

public class CommonSteps {

    @Дано("пользователь находится на главной странице")
    public void userOnMainPage() {
        ProjectLogger.info(CommonLogMessages.MAIN_PAGE_CHECK_LOG);
        Assumptions.assumeThat(MainPage.isDisplayed());
    }

}
