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
        MainPage mainPage = new MainPage();
        ProjectLogger.info("Переход на страницу дебетовых карт");
        mainPage.hoverCards();
        mainPage.clickDebitCards();
        DebitCardsPage debitCardsPage = new DebitCardsPage();
        Assumptions.assumeThat(debitCardsPage.isDisplayed());
    }

    @Дано("пользователь открывает страницу карты 'ЯСЧИТАЮ'")
    public void openingICountPage() {
        DebitCardsPage debitCardsPage = new DebitCardsPage();
        ProjectLogger.info("Переход на страницу карты 'ЯСЧИТАЮ'");
        debitCardsPage.clickICountCardLink();
        CardOrderingForm cardOrderingForm = new CardOrderingForm();
        Assumptions.assumeThat(cardOrderingForm.isDisplayed());
    }

    @Когда("пользователь вводит номер телефона {string}")
    public void enterPhoneNumber(String number) {
        CardOrderingForm cardOrderingForm = new CardOrderingForm();
        ProjectLogger.info("Ввод номера телефона");
        cardOrderingForm.inputPhoneNumber(number);
    }

    @Когда("пользователь вводит электронную почту {string}")
    public void enterEmail(String email) {
        CardOrderingForm cardOrderingForm = new CardOrderingForm();
        ProjectLogger.info("Ввод почты");
        cardOrderingForm.inputEmail(email);
    }

    @Когда("пользователь нажимает кнопку 'Продолжить'")
    public void clickContinue() {
        CardOrderingForm cardOrderingForm = new CardOrderingForm();
        ProjectLogger.info("Нажатие кнопки продолжить");
        cardOrderingForm.clickContinueButton();
    }

    @Тогда("поля 'Фамилия', 'Имя' и 'Адрес доставки' подсвечиваются красным")
    public void checkingRedStatus() {
        CardOrderingForm cardOrderingForm = new CardOrderingForm();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(cardOrderingForm.getLastNameInvalidity())
                .as("Проверка: поле 'Фамилия' горит красным")
                .isEqualTo(AttributeConstants.TRUE);
        softly.assertThat(cardOrderingForm.getFirstNameInvalidity())
                .as("Проверка: поле 'Имя' горит красным")
                .isEqualTo(AttributeConstants.TRUE);
        softly.assertThat(cardOrderingForm.getAddressInvalidity())
                .as("Проверка: поле 'Адрес доставки' горит красным")
                .isEqualTo(AttributeConstants.TRUE);
        softly.assertAll();
    }
}
