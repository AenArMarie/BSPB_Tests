package com.bspbtests.steps;

import com.bspbtests.pages.MainPage;
import com.bspbtests.utility.enums.PageChecker;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;

import static org.assertj.core.api.Assertions.assertThat;

public class PageOpeningSteps {

    @Когда("пользователь нажимает на пункт меню {string}")
    public void menuButtonPressed(String menuText) {
        MainPage.clickTopMenuItemByText(menuText);
    }

    @Тогда("открывается страница {string}")
    public void pageOpened(String expectedPageName) {
        assertThat(PageChecker.fromPageName(expectedPageName).isDisplayed(expectedPageName))
                .as("Проверка отображения страницы " + expectedPageName)
                .isTrue();
    }
}
