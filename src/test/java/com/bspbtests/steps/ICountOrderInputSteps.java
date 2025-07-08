package com.bspbtests.steps;

import com.bspbtests.constants.AttributeConstants;
import com.bspbtests.pages.CardOrderingForm;
import com.bspbtests.pages.DebitCardsPage;
import com.bspbtests.pages.MainPage;
import com.utility.logger.ProjectLogger;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;

import static com.bspbtests.steps.Hooks.testData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ICountOrderInputSteps {

    private MainPage mainPage;
    private DebitCardsPage debitCardsPage;
    private CardOrderingForm cardOrderingForm;

    @Дано("он переходит на страницу дебетовых карт")
    public void openingDebitCardsPage() {
        mainPage = new MainPage();
        ProjectLogger.info("Переход на страницу дебетовых карт");
        mainPage.hoverCards();
        mainPage.clickDebitCards();
        debitCardsPage = new DebitCardsPage();
        ProjectLogger.info("Проверка отображения страницы дебетовых карт");
        assertTrue(debitCardsPage.isDisplayed(), "Страница дебетовых карт не отображена");
    }

    @Дано("он открывает страницу карты 'ЯСЧИТАЮ'")
    public void openingICountPage() {
        ProjectLogger.info("Переход на страницу карты 'ЯСЧИТАЮ'");
        debitCardsPage.clickICountCardLink();
        cardOrderingForm = new CardOrderingForm();
        ProjectLogger.info("Проверка отображения формы заказа карты");
        assertTrue(cardOrderingForm.isDisplayed(), "Форма заказа карты не отображена");
    }

    @Когда("он вводит номер телефона")
    public void enterPhoneNumber() {
        ProjectLogger.info("Ввод номера телефона");
        cardOrderingForm.inputPhoneNumber(testData.getCardOrderingData().getPhoneNumber());
    }

    @Когда("он вводит электронную почту")
    public void enterEmail() {
        ProjectLogger.info("Ввод почты");
        cardOrderingForm.inputEmail(testData.getCardOrderingData().getEmail());
    }

    @Когда("он нажимает кнопку 'Продолжить'")
    public void clickContinue() {
        ProjectLogger.info("Нажатие кнопки продолжить");
        cardOrderingForm.clickContinueButton();
    }

    @Тогда("поле 'Фамилия' подсвечивается красным")
    public void checkingSurname() {
        assertEquals(AttributeConstants.TRUE, cardOrderingForm.getLastNameInvalidity(), "Поле 'Фамилия' не горит красным");
    }

    @Тогда("поле 'Имя' подсвечивается красным")
    public void checkingName() {
        assertEquals(AttributeConstants.TRUE, cardOrderingForm.getFirstNameInvalidity(), "Поле 'Имя' не горит красным");
    }

    @Тогда("поле 'Адрес доставки' подсвечивается красным")
    public void checkingAddress() {
        assertEquals(AttributeConstants.TRUE, cardOrderingForm.getAdressInvalidity(), "Поле 'Адрес доставки' не горит красным");
    }
}
