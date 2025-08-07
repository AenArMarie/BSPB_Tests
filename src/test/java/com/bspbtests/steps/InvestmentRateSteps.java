package com.bspbtests.steps;

import com.bspbtests.constants.StringConstants;
import com.bspbtests.pages.CalculatorForm;
import com.bspbtests.pages.MainPage;
import com.bspbtests.pages.WhiteNightsInvestmentPage;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assumptions.assumeThat;

public class InvestmentRateSteps {

    @Дано("пользователь переходит на страницу вклада 'Белые ночи' из подменю 'Вклады'")
    public void openingWhiteNightsPage() {
        MainPage.hoverInvestments();
        MainPage.clickWhiteNights();
        assumeThat(WhiteNightsInvestmentPage.isDisplayed()).isTrue();
        assumeThat(CalculatorForm.isDisplayed()).isTrue();
    }

    @Когда("пользователь выбирает срок вклада {string}")
    public void choosingInvestmentPeriod(String investmentPeriod) {
        CalculatorForm.clickInvestmentPeriodByText(investmentPeriod);
    }

    @Когда("пользователь указывает сумму вклада {string}")
    public void inputtingInvestmentAmount(String investedSum) {
        CalculatorForm.setInvestmentSum(investedSum);
    }

    @Тогда("ставка равна {int} и выгода по вкладу равна {string}")
    public void checkingInvestmentRate(int expectedRate, String expectedValue) {
        SoftAssertions softly = new SoftAssertions();
        String investmentRateText = CalculatorForm.getInvestmentRate();
        long investmentRate = Long.parseLong(investmentRateText.replaceAll(StringConstants.ALL_NON_NUMERIC_CHARS, StringConstants.EMPTY_STRING));
        softly.assertThat(investmentRate)
                .as("Проверка процента вклада")
                .isEqualTo(expectedRate);
        softly.assertThat(CalculatorForm.checkIfNormalizedInterestAmountEqualToText(expectedValue))
                .as("Проверка прибыли вклада").isTrue();
        softly.assertAll();
    }

}
