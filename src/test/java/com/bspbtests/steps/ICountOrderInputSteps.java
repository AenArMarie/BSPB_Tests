package com.bspbtests.steps;

import com.bspbtests.constants.AttributeConstants;
import com.bspbtests.pages.CardOrderingFormI;
import com.bspbtests.pages.DebitCardsPage;
import com.bspbtests.pages.MainPage;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.assertj.core.api.Assumptions;
import org.assertj.core.api.SoftAssertions;

public class ICountOrderInputSteps {

    @Дано("пользователь переходит на страницу дебетовых карт")
    public void openingDebitCardsPage() {
        MainPage.hoverCards();
        MainPage.clickDebitCards();
        Assumptions.assumeThat(DebitCardsPage.isDisplayed()).isTrue();
    }

    @Дано("пользователь открывает страницу карты 'ЯСЧИТАЮ'")
    public void openingICountPage() {
        DebitCardsPage.clickICountCardLink();
        Assumptions.assumeThat(CardOrderingFormI.isDisplayed()).isTrue();
    }

    @Когда("пользователь вводит номер телефона {string}")
    public void enterPhoneNumber(String number) {
        CardOrderingFormI.inputPhoneNumber(number);
    }

    @Когда("пользователь вводит электронную почту {string}")
    public void enterEmail(String email) {
        CardOrderingFormI.inputEmail(email);
    }

    @Когда("пользователь нажимает кнопку 'Продолжить'")
    public void clickContinue() {
        CardOrderingFormI.clickContinueButton();
    }

    @Тогда("поля 'Фамилия', 'Имя' и 'Адрес доставки' подсвечиваются красным")
    public void checkingRedStatus() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(CardOrderingFormI.getLastNameInvalidity())
                .as("Проверка: поле 'Фамилия' горит красным")
                .isEqualTo(AttributeConstants.TRUE);
        softly.assertThat(CardOrderingFormI.getFirstNameInvalidity())
                .as("Проверка: поле 'Имя' горит красным")
                .isEqualTo(AttributeConstants.TRUE);
        softly.assertThat(CardOrderingFormI.getAddressInvalidity())
                .as("Проверка: поле 'Адрес доставки' горит красным")
                .isEqualTo(AttributeConstants.TRUE);
        softly.assertAll();
    }
}
