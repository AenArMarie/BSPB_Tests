package com.bspbtests.steps;

import com.bspbtests.constants.StringConstants;
import com.bspbtests.pages.CalculatorForm;
import com.bspbtests.pages.MainPage;
import com.bspbtests.pages.WhiteNightsInvestmentPage;
import com.utility.logger.ProjectLogger;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;

import static com.bspbtests.steps.Hooks.testData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InvestmentRateSteps {

    private MainPage mainPage;
    WhiteNightsInvestmentPage whiteNightsInvestmentPage;
    CalculatorForm calculatorForm;

    @Дано("он переходит на страницу вклада 'Белые ночи' из подменю 'Вклады'")
    public void openingWhiteNightsPage() {
        mainPage = new MainPage();
        ProjectLogger.info("Переход на страницу вклада 'Белые ночи'");
        mainPage.hoverInvestments();
        mainPage.clickWhiteNights();
        ProjectLogger.info("Проверка открытия страницы вклада 'Белые ночи'");
        whiteNightsInvestmentPage = new WhiteNightsInvestmentPage();
        assertTrue(whiteNightsInvestmentPage.isDisplayed(), "Страница вклада 'Белые ночи' не открыта");
        ProjectLogger.info("Проверка открытия кальулятора прибыли вклада");
        calculatorForm = new CalculatorForm();
        assertTrue(calculatorForm.isDisplayed(), "Калькулятор вклада 'Белые ночи' не открыт");
    }

    @Когда("он выбирает срок вклада")
    public void choosingInvestmentPeriod() {
        ProjectLogger.info("Установка суммы вклада " + testData.getInvestmentRateData().getInvestedAmount());
        calculatorForm.setInvestmentSum(testData.getInvestmentRateData().getInvestedAmount());
    }

    @Когда("он указывает сумму вклада")
    public void inputtingInvestmentAmount() {
        ProjectLogger.info("Установка срока вклада " + testData.getInvestmentRateData().getInvestmentPeriodText());
        calculatorForm.clickInvestmentPeriodByText(testData.getInvestmentRateData().getInvestmentPeriodText());
    }

    @Тогда("ставка по вкладу соответствует тестовым данным")
    public void checkingInvestmentRate() {
        String investmentRateText = calculatorForm.getInvestmentRate();
        long investmentRate = Long.parseLong(investmentRateText.replaceAll(StringConstants.ALL_NON_NUMERIC_CHARS, StringConstants.EMPTY_STRING));
        ProjectLogger.info("Проверка процента вклада");
        assertEquals(investmentRate, testData.getInvestmentRateData().getExpectedInvestmentRate(), "Процент вклада не соответствует ожидаемому");
    }

    @Тогда("выгода по вкладу соответствует тестовым данным")
    public void checkingInvestmentRevenue() {
        ProjectLogger.info("Получение прибыли вклада");
        assertTrue(calculatorForm.checkIfNormalizedInterestAmountEqualToText(testData.getInvestmentRateData().getExpectedInterestValue()), "Прибыль вклада не соответствует ожидаемой");
    }
}
