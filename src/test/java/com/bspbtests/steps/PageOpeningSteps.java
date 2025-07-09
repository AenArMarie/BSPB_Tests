package com.bspbtests.steps;

import com.bspbtests.constants.MainPageMenuItemText;
import com.bspbtests.pages.*;
import com.bspbtests.pages.baseform.BaseForm;
import com.utility.logger.ProjectLogger;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import static org.assertj.core.api.Assertions.assertThat;

public class PageOpeningSteps {

    private MainPage mainPage;
    private BaseForm expectedPage;

    @Когда("он нажимает на пункт меню {string}")
    public void menuButtonPressed(String menuText) {
        mainPage = new MainPage();
        ProjectLogger.info("Переход на страницу '" + menuText + "'");
        mainPage.clickTopMenuItemByText(menuText);
    }

    @Тогда("открывается страница {string}")
    public void pageOpened(String expectedPageName) {
        ProjectLogger.info("Проверка отображения страницы '" + expectedPageName + "'");
        switch (expectedPageName) {
            case MainPageMenuItemText.FINANCE:
                expectedPage = new FinancePage();
                break;
            case MainPageMenuItemText.BUSINESS:
                expectedPage = new BusinessPage();
                break;
            case MainPageMenuItemText.VED:
                expectedPage = new VedPage();
                break;
            case MainPageMenuItemText.INVESTOR:
                expectedPage = new InvestorPage();
                break;
            case MainPageMenuItemText.PRIVATE_BANKING:
                expectedPage = new PrivateBankingPage();
                break;
            default:
                ProjectLogger.error("Страница с данным названием не найдена");
                fail();
        }
        assertThat(expectedPage.isDisplayed())
                .as("Проверка отображения страницы " + expectedPageName)
                .isTrue();

    }
}
