package com.bspbtests.tests.inputtests;

import com.bspbtests.constants.AttributeConstants;
import com.bspbtests.constants.CommonLogMessages;
import com.bspbtests.pages.CardOrderingForm;
import com.bspbtests.pages.DebitCardsPage;
import com.bspbtests.pages.MainPage;
import com.bspbtests.tests.basetest.BaseTest;
import com.utility.logger.ProjectLogger;
import org.junit.Assert;
import org.junit.Test;

public class ICountOrderInputTest extends BaseTest {

    @Test
    public void iCountOrderInputTest() {
        ProjectLogger.info(CommonLogMessages.MAIN_PAGE_CHECK_LOG);
        MainPage mainPage = new MainPage();
        Assert.assertTrue(CommonLogMessages.MAIN_PAGE_NOT_DISPLAYED, mainPage.isDisplayed());

        ProjectLogger.info("Переход на страницу дебетовых карт");
        mainPage.hoverCards();
        mainPage.clickDebitCards();
        DebitCardsPage debitCardsPage = new DebitCardsPage();
        ProjectLogger.info("Проверка отображения страницы дебетовых карт");
        Assert.assertTrue("Страница дебетовых карт не отображена", debitCardsPage.isDisplayed());

        ProjectLogger.info("Переход на страницу карты 'ЯСЧИТАЮ'");
        debitCardsPage.clickICountCardLink();
        CardOrderingForm cardOrderingForm = new CardOrderingForm();
        ProjectLogger.info("Проверка отображения формы заказа карты");
        Assert.assertTrue("Форма заказа карты не отображена", cardOrderingForm.isDisplayed());

        ProjectLogger.info("Ввод номера телефона");
        cardOrderingForm.inputPhoneNumber(testData.getCardOrderingData().getPhoneNumber());
        ProjectLogger.info("Ввод почты");
        cardOrderingForm.inputEmail(testData.getCardOrderingData().getEmail());
        ProjectLogger.info("Нажатие кнопки продолжить");
        cardOrderingForm.clickContinueButton();

        ProjectLogger.info("Проверка выделения обязательных незаполненных полей красным цветом");
        Assert.assertEquals("Поле 'Имя' не горит красным", AttributeConstants.TRUE, cardOrderingForm.getFirstNameInvalidity());
        Assert.assertEquals("Поле 'Фамилия' не горит красным", AttributeConstants.TRUE, cardOrderingForm.getLastNameInvalidity());
        Assert.assertEquals("Поле 'Адрес доставки' не горит красным", AttributeConstants.TRUE, cardOrderingForm.getAdressInvalidity());
    }
}
