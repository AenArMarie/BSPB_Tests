package com.bspbtests.steps;

import com.bspbtests.constants.AttributeConstants;
import com.bspbtests.pages.CardOrderingForm;
import com.bspbtests.pages.DebitCardsPage;
import com.bspbtests.pages.MainPage;
import com.bspbtests.utility.ProjectLogger;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.assertj.core.api.Assumptions;
import org.assertj.core.api.SoftAssertions;

public class ICountOrderInputSteps {

    @Дано("пользователь переходит на страницу дебетовых карт")
    public void openingDebitCardsPage() {
        ProjectLogger.info("Переход на страницу дебетовых карт");
        MainPage.hoverCards();
        MainPage.clickDebitCards();
        Assumptions.assumeThat(DebitCardsPage.isDisplayed());
    }

    @Дано("пользователь открывает страницу карты 'ЯСЧИТАЮ'")
    public void openingICountPage() {
        ProjectLogger.info("Переход на страницу карты 'ЯСЧИТАЮ'");
        DebitCardsPage.clickICountCardLink();
        Assumptions.assumeThat(CardOrderingForm.isDisplayed());
    }

    @Когда("пользователь вводит номер телефона {string}")
    public void enterPhoneNumber(String number) {
        ProjectLogger.info("Ввод номера телефона");
        CardOrderingForm.inputPhoneNumber(number);
    }

    @Когда("пользователь вводит электронную почту {string}")
    public void enterEmail(String email) {
        ProjectLogger.info("Ввод почты");
        CardOrderingForm.inputEmail(email);
    }

    @Когда("пользователь нажимает кнопку 'Продолжить'")
    public void clickContinue() {
        ProjectLogger.info("Нажатие кнопки продолжить");
        CardOrderingForm.clickContinueButton();
    }

    @Тогда("поля 'Фамилия', 'Имя' и 'Адрес доставки' подсвечиваются красным")
    public void checkingRedStatus() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(CardOrderingForm.getLastNameInvalidity())
                .as("Проверка: поле 'Фамилия' горит красным")
                .isEqualTo(AttributeConstants.TRUE);
        softly.assertThat(CardOrderingForm.getFirstNameInvalidity())
                .as("Проверка: поле 'Имя' горит красным")
                .isEqualTo(AttributeConstants.TRUE);
        softly.assertThat(CardOrderingForm.getAddressInvalidity())
                .as("Проверка: поле 'Адрес доставки' горит красным")
                .isEqualTo(AttributeConstants.TRUE);
        softly.assertAll();
    }
}
