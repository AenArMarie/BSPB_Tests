package com.bspbtests.tests.inputtests;

import com.bspbtests.constants.AttributeConstants;
import com.bspbtests.constants.CommonLogMessages;
import com.bspbtests.pages.CardOrderingForm;
import com.bspbtests.pages.DebitCardsPage;
import com.bspbtests.pages.MainPage;
import com.bspbtests.tests.basetest.BaseTest;
import com.utility.logger.ProjectLogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ICountOrderInputTest extends BaseTest {

    @Test
    public void iCountOrderInputTest() {
        ProjectLogger.info(CommonLogMessages.MAIN_PAGE_CHECK_LOG);
        MainPage mainPage = new MainPage();
        assertTrue(mainPage.isDisplayed(), CommonLogMessages.MAIN_PAGE_NOT_DISPLAYED);

        ProjectLogger.info("Переход на страницу дебетовых карт");
        mainPage.hoverCards();
        mainPage.clickDebitCards();
        DebitCardsPage debitCardsPage = new DebitCardsPage();
        ProjectLogger.info("Проверка отображения страницы дебетовых карт");
        assertTrue(debitCardsPage.isDisplayed(), "Страница дебетовых карт не отображена");

        ProjectLogger.info("Переход на страницу карты 'ЯСЧИТАЮ'");
        debitCardsPage.clickICountCardLink();
        CardOrderingForm cardOrderingForm = new CardOrderingForm();
        ProjectLogger.info("Проверка отображения формы заказа карты");
        assertTrue(cardOrderingForm.isDisplayed(), "Форма заказа карты не отображена");

        ProjectLogger.info("Ввод номера телефона");
        cardOrderingForm.inputPhoneNumber(testData.getCardOrderingData().getPhoneNumber());
        ProjectLogger.info("Ввод почты");
        cardOrderingForm.inputEmail(testData.getCardOrderingData().getEmail());
        ProjectLogger.info("Нажатие кнопки продолжить");
        cardOrderingForm.clickContinueButton();

        ProjectLogger.info("Проверка выделения обязательных незаполненных полей красным цветом");
        assertEquals(AttributeConstants.TRUE, cardOrderingForm.getFirstNameInvalidity(), "Поле 'Имя' не горит красным");
        assertEquals(AttributeConstants.TRUE, cardOrderingForm.getLastNameInvalidity(), "Поле 'Фамилия' не горит красным");
        assertEquals(AttributeConstants.TRUE, cardOrderingForm.getAdressInvalidity(), "Поле 'Адрес доставки' не горит красным");
    }
}
