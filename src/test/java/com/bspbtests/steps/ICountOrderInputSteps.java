package com.bspbtests.steps;

import com.bspbtests.constants.AttributeConstants;
import com.bspbtests.pages.CardOrderingForm;
import com.bspbtests.pages.DebitCardsPage;
import com.bspbtests.pages.MainPage;
import com.utility.logger.ProjectLogger;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.assertj.core.api.SoftAssertions;

import static com.bspbtests.steps.Hooks.testData;

public class ICountOrderInputSteps {

    @Дано("он переходит на страницу дебетовых карт")
    public void openingDebitCardsPage() {
        MainPage mainPage = new MainPage();
        ProjectLogger.info("Переход на страницу дебетовых карт");
        mainPage.hoverCards();
        mainPage.clickDebitCards();
        DebitCardsPage debitCardsPage = new DebitCardsPage();
        debitCardsPage.isDisplayed();
    }

    @Дано("он открывает страницу карты 'ЯСЧИТАЮ'")
    public void openingICountPage() {
        DebitCardsPage debitCardsPage = new DebitCardsPage();
        ProjectLogger.info("Переход на страницу карты 'ЯСЧИТАЮ'");
        debitCardsPage.clickICountCardLink();
        CardOrderingForm cardOrderingForm = new CardOrderingForm();
        cardOrderingForm.isDisplayed();
    }

    @Когда("он вводит номер телефона")
    public void enterPhoneNumber() {
        CardOrderingForm cardOrderingForm = new CardOrderingForm();
        ProjectLogger.info("Ввод номера телефона");
        cardOrderingForm.inputPhoneNumber(testData.getCardOrderingData().getPhoneNumber());
    }

    @Когда("он вводит электронную почту")
    public void enterEmail() {
        CardOrderingForm cardOrderingForm = new CardOrderingForm();
        ProjectLogger.info("Ввод почты");
        cardOrderingForm.inputEmail(testData.getCardOrderingData().getEmail());
    }

    @Когда("он нажимает кнопку 'Продолжить'")
    public void clickContinue() {
        CardOrderingForm cardOrderingForm = new CardOrderingForm();
        ProjectLogger.info("Нажатие кнопки продолжить");
        cardOrderingForm.clickContinueButton();
    }

    @Тогда("поля 'Фамилия', 'Имя' и 'Адрес доставки' подсвечиваются красным")
    public void checkingSurname() {
        CardOrderingForm cardOrderingForm = new CardOrderingForm();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(cardOrderingForm.getLastNameInvalidity())
                .as("Проверка: поле 'Фамилия' горит красным")
                .isEqualTo(AttributeConstants.TRUE);
        softly.assertThat(cardOrderingForm.getFirstNameInvalidity())
                .as("Проверка: поле 'Имя' горит красным")
                .isEqualTo(AttributeConstants.TRUE);
        softly.assertThat(cardOrderingForm.getAdressInvalidity())
                .as("Проверка: поле 'Адрес доставки' горит красным")
                .isEqualTo(AttributeConstants.TRUE);
        softly.assertAll();
    }
}
