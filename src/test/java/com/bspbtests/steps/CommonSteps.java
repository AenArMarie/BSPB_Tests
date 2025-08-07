package com.bspbtests.steps;

import com.bspbtests.pages.MainPage;
import io.cucumber.java.ru.Дано;

import static org.assertj.core.api.Assumptions.assumeThat;

public class CommonSteps {

    @Дано("пользователь находится на главной странице")
    public void userOnMainPage() {
        assumeThat(MainPage.isDisplayed())
                .as("Проверка отображения главной страницы")
                .isTrue();
    }

}
