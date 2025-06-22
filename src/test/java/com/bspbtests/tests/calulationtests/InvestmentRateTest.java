package com.bspbtests.tests.calulationtests;

import com.bspbtests.constants.CommonLogMessages;
import com.bspbtests.constants.StringConstants;
import com.bspbtests.pages.CalculatorForm;
import com.bspbtests.pages.MainPage;
import com.bspbtests.pages.WhiteNightsInvestmentPage;
import com.bspbtests.tests.basetest.BaseTest;
import com.utility.logger.ProjectLogger;
import org.junit.Assert;
import org.junit.Test;

public class InvestmentRateTest extends BaseTest {

    @Test
    public void investmentRateTest() {
        ProjectLogger.info(CommonLogMessages.MAIN_PAGE_CHECK_LOG);
        MainPage mainPage = new MainPage();
        Assert.assertTrue(CommonLogMessages.MAIN_PAGE_NOT_DISPLAYED, mainPage.isDisplayed());
        ProjectLogger.info("Переход на страницу вклада 'Белые ночи'");
        mainPage.hoverInvestments();
        mainPage.clickWhiteNights();
        ProjectLogger.info("Проверка открытия страницы вклада 'Белые ночи'");
        WhiteNightsInvestmentPage whiteNightsInvestmentPage = new WhiteNightsInvestmentPage();
        Assert.assertTrue("Страница вклада 'Белые ночи' не открыта", whiteNightsInvestmentPage.isDisplayed());
        ProjectLogger.info("Проверка открытия кальулятора прибыли вклада");
        CalculatorForm calculatorForm = new CalculatorForm();
        Assert.assertTrue("Калькулятор вклада 'Белые ночи' не открыт", calculatorForm.isDisplayed());
        ProjectLogger.info("Установка суммы вклада " + testData.getInvestmentRateData().getInvestedAmount());
        calculatorForm.setInvestmentSum(testData.getInvestmentRateData().getInvestedAmount());
        ProjectLogger.info("Установка срока вклада " + testData.getInvestmentRateData().getInvestmentPeriodText());
        calculatorForm.clickInvestmentPeriodByText(testData.getInvestmentRateData().getInvestmentPeriodText());

        ProjectLogger.info("Получение информации о проценте вклада и прибыли");
        String investmentRateText = calculatorForm.getInvestmentRate();
        long investmentRate = Long.parseLong(investmentRateText.replaceAll(StringConstants.ALL_NON_NUMERIC_CHARS, StringConstants.EMPTY_STRING));

        ProjectLogger.info("Проверка процента вклада");
        Assert.assertEquals("Процент вклада не соответствует ожидаемому", investmentRate, testData.getInvestmentRateData().getExpectedInvestmentRate());
        ProjectLogger.info("Получение прибыли вклада");
        Assert.assertTrue("Прибыль вклада не соответствует ожидаемой", calculatorForm.checkIfNormalizedInterestAmountEqualToText(testData.getInvestmentRateData().getExpectedInterestValue()));

    }
}
