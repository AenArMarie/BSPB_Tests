package com.bspbtests.steps;

import com.bspbtests.constants.StringConstants;
import com.bspbtests.pages.CalculatorForm;
import com.bspbtests.pages.MainPage;
import com.bspbtests.pages.WhiteNightsInvestmentPage;
import com.utility.logger.ProjectLogger;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.assertj.core.api.Assumptions;
import org.assertj.core.api.SoftAssertions;

public class InvestmentRateSteps {

    @Дано("пользователь переходит на страницу вклада 'Белые ночи' из подменю 'Вклады'")
    public void openingWhiteNightsPage() {
        MainPage mainPage = new MainPage();
        ProjectLogger.info("Переход на страницу вклада 'Белые ночи'");
        mainPage.hoverInvestments();
        mainPage.clickWhiteNights();
        WhiteNightsInvestmentPage whiteNightsInvestmentPage = new WhiteNightsInvestmentPage();
        whiteNightsInvestmentPage.isDisplayed();
        CalculatorForm calculatorForm = new CalculatorForm();
        Assumptions.assumeThat(calculatorForm.isDisplayed());
    }

    @Когда("пользователь выбирает срок вклада {string}")
    public void choosingInvestmentPeriod(String investmentPeriod) {
        CalculatorForm calculatorForm = new CalculatorForm();
        ProjectLogger.info("Установка суммы вклада " + investmentPeriod);
        calculatorForm.clickInvestmentPeriodByText(investmentPeriod);
    }

    @Когда("пользователь указывает сумму вклада {string}")
    public void inputtingInvestmentAmount(String investedSum) {
        CalculatorForm calculatorForm = new CalculatorForm();
        ProjectLogger.info("Установка срока вклада " + investedSum);
        calculatorForm.setInvestmentSum(investedSum);
    }

    @Тогда("ставка равна {int} и выгода по вкладу равна {string}")
    public void checkingInvestmentRate(int expectedRate, String expectedValue) {
        CalculatorForm calculatorForm = new CalculatorForm();
        SoftAssertions softly = new SoftAssertions();
        String investmentRateText = calculatorForm.getInvestmentRate();
        long investmentRate = Long.parseLong(investmentRateText.replaceAll(StringConstants.ALL_NON_NUMERIC_CHARS, StringConstants.EMPTY_STRING));
        softly.assertThat(investmentRate)
                .as("Проверка процента вклада")
                .isEqualTo(expectedRate);
        softly.assertThat(calculatorForm.checkIfNormalizedInterestAmountEqualToText(expectedValue))
                .as("Проверка прибыли вклада").isTrue();
        softly.assertAll();
    }

}
