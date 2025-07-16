package com.bspbtests.steps;

import com.bspbtests.constants.StringConstants;
import com.bspbtests.pages.CalculatorForm;
import com.bspbtests.pages.MainPage;
import com.bspbtests.pages.WhiteNightsInvestmentPage;
import com.utility.logger.ProjectLogger;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;

import static com.bspbtests.steps.Hooks.testData;

public class InvestmentRateSteps {

    @Дано("он переходит на страницу вклада 'Белые ночи' из подменю 'Вклады'")
    @Step("Переход на страницу вклада 'Белые ночи' из подменю 'Вклады'")
    public void openingWhiteNightsPage() {
        MainPage mainPage = new MainPage();
        ProjectLogger.info("Переход на страницу вклада 'Белые ночи'");
        mainPage.hoverInvestments();
        mainPage.clickWhiteNights();
        WhiteNightsInvestmentPage whiteNightsInvestmentPage = new WhiteNightsInvestmentPage();
        whiteNightsInvestmentPage.isDisplayed();
        CalculatorForm calculatorForm = new CalculatorForm();
        calculatorForm.isDisplayed();
    }

    @Когда("он выбирает срок вклада")
    @Step("Выбор срока вклада")
    public void choosingInvestmentPeriod() {
        CalculatorForm calculatorForm = new CalculatorForm();
        ProjectLogger.info("Установка суммы вклада " + testData.getInvestmentRateData().getInvestedAmount());
        calculatorForm.setInvestmentSum(testData.getInvestmentRateData().getInvestedAmount());
    }

    @Когда("он указывает сумму вклада")
    @Step("Указание суммы вклада")
    public void inputtingInvestmentAmount() {
        CalculatorForm calculatorForm = new CalculatorForm();
        ProjectLogger.info("Установка срока вклада " + testData.getInvestmentRateData().getInvestmentPeriodText());
        calculatorForm.clickInvestmentPeriodByText(testData.getInvestmentRateData().getInvestmentPeriodText());
    }

    @Тогда("ставка и выгода по вкладу соответствует тестовым данным")
    @Step("Проверка на соответствие ставки и выгоды по вкладу тестовым данным")
    public void checkingInvestmentRate() {
        CalculatorForm calculatorForm = new CalculatorForm();
        SoftAssertions softly = new SoftAssertions();
        String investmentRateText = calculatorForm.getInvestmentRate();
        long investmentRate = Long.parseLong(investmentRateText.replaceAll(StringConstants.ALL_NON_NUMERIC_CHARS, StringConstants.EMPTY_STRING));
        softly.assertThat(investmentRate)
                .as("Проверка процента вклада")
                .isEqualTo(testData.getInvestmentRateData().getExpectedInvestmentRate());
        softly.assertThat(calculatorForm.checkIfNormalizedInterestAmountEqualToText(testData.getInvestmentRateData().getExpectedInterestValue()))
                .as("Проверка прибыли вклада").isTrue();
        softly.assertAll();
    }

}
