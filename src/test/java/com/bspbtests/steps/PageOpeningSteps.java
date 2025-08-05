package com.bspbtests.steps;

import com.bspbtests.constants.ElementsTextConstants;
import com.bspbtests.constants.MainPageMenuItemText;
import com.bspbtests.pages.MainMenuPage;
import com.bspbtests.pages.MainPage;
import com.bspbtests.pages.PrivateBankingPage;
import com.bspbtests.pages.baseform.BaseForm;
import com.bspbtests.utility.ProjectLogger;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;

import static org.assertj.core.api.Assertions.assertThat;

public class PageOpeningSteps {

    @Когда("пользователь нажимает на пункт меню {string}")
    public void menuButtonPressed(String menuText) {
        ProjectLogger.info("Переход на страницу '" + menuText + "'");
        MainPage.clickTopMenuItemByText(menuText);
    }

    @Тогда("открывается страница {string}")
    public void pageOpened(String expectedPageName) {
        ProjectLogger.info("Проверка отображения страницы '" + expectedPageName + "'");
        switch (expectedPageName) { //TODO поправь ради Христа
            case MainPageMenuItemText.PRIVATE_BANKING -> assertThat(PrivateBankingPage.isDisplayed()).
                    as("Проверка отображения страницы " + expectedPageName).
                    isTrue();
            default -> assertThat(MainMenuPage.isDisplayed(ElementsTextConstants.PAGES_UNIQUE_ELEMENT_TEXT.get(expectedPageName), expectedPageName)).
                    as("Проверка отображения страницы " + expectedPageName).
                    isTrue();
        }
    }
}
