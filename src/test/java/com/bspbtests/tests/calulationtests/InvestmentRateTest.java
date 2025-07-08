package com.bspbtests.tests.calulationtests;

import com.bspbtests.constants.CommonLogMessages;
import com.bspbtests.constants.StringConstants;
import com.bspbtests.pages.CalculatorForm;
import com.bspbtests.pages.MainPage;
import com.bspbtests.pages.WhiteNightsInvestmentPage;
import com.bspbtests.tests.basetest.BaseTest;
import com.utility.logger.ProjectLogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InvestmentRateTest extends BaseTest {

    @Test
    public void investmentRateTest() {
        ProjectLogger.info(CommonLogMessages.MAIN_PAGE_CHECK_LOG);
        MainPage mainPage = new MainPage();
        assertTrue(mainPage.isDisplayed(), CommonLogMessages.MAIN_PAGE_NOT_DISPLAYED);
        ProjectLogger.info("Переход на страницу вклада 'Белые ночи'");
        mainPage.hoverInvestments();
        mainPage.clickWhiteNights();
        ProjectLogger.info("Проверка открытия страницы вклада 'Белые ночи'");
        WhiteNightsInvestmentPage whiteNightsInvestmentPage = new WhiteNightsInvestmentPage();
        assertTrue(whiteNightsInvestmentPage.isDisplayed(), "Страница вклада 'Белые ночи' не открыта");
        ProjectLogger.info("Проверка открытия кальулятора прибыли вклада");
        CalculatorForm calculatorForm = new CalculatorForm();
        assertTrue(calculatorForm.isDisplayed(), "Калькулятор вклада 'Белые ночи' не открыт");
        ProjectLogger.info("Установка суммы вклада " + testData.getInvestmentRateData().getInvestedAmount());
        calculatorForm.setInvestmentSum(testData.getInvestmentRateData().getInvestedAmount());
        ProjectLogger.info("Установка срока вклада " + testData.getInvestmentRateData().getInvestmentPeriodText());
        calculatorForm.clickInvestmentPeriodByText(testData.getInvestmentRateData().getInvestmentPeriodText());

        ProjectLogger.info("Получение информации о проценте вклада и прибыли");
        String investmentRateText = calculatorForm.getInvestmentRate();
        long investmentRate = Long.parseLong(investmentRateText.replaceAll(StringConstants.ALL_NON_NUMERIC_CHARS, StringConstants.EMPTY_STRING));

        ProjectLogger.info("Проверка процента вклада");
        assertEquals(investmentRate, testData.getInvestmentRateData().getExpectedInvestmentRate(), "Процент вклада не соответствует ожидаемому");
        ProjectLogger.info("Получение прибыли вклада");
        assertTrue(calculatorForm.checkIfNormalizedInterestAmountEqualToText(testData.getInvestmentRateData().getExpectedInterestValue()), "Прибыль вклада не соответствует ожидаемой");

    }
}
