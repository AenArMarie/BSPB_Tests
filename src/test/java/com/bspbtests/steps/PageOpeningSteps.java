package com.bspbtests.steps;

import com.bspbtests.constants.ElementsTextConstants;
import com.bspbtests.constants.MainPageMenuItemText;
import com.bspbtests.pages.MainMenuPage;
import com.bspbtests.pages.MainPage;
import com.bspbtests.pages.PrivateBankingPage;
import com.bspbtests.pages.baseform.BaseForm;
import com.utility.logger.ProjectLogger;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Step;

import static org.assertj.core.api.Assertions.assertThat;

public class PageOpeningSteps {

    @Когда("пользователь нажимает на пункт меню {string}")
    @Step("Нажатие на пункт меню {menuText}")
    public void menuButtonPressed(String menuText) {
        MainPage mainPage = new MainPage();
        ProjectLogger.info("Переход на страницу '" + menuText + "'");
        mainPage.clickTopMenuItemByText(menuText);
    }

    @Тогда("открывается страница {string}")
    @Step("Открытие страницы {expectedPageName}")
    public void pageOpened(String expectedPageName) {
        ProjectLogger.info("Проверка отображения страницы '" + expectedPageName + "'");
        BaseForm expectedPage;
        switch (expectedPageName) {
            case MainPageMenuItemText.PRIVATE_BANKING ->
                expectedPage = new PrivateBankingPage();
            default ->
                expectedPage = new MainMenuPage(ElementsTextConstants.PAGES_UNIQUE_ELEMENT_TEXT.get(expectedPageName), expectedPageName);
        }
        assertThat(expectedPage.isDisplayed())
                .as("Проверка отображения страницы " + expectedPageName)
                .isTrue();
    }
}
