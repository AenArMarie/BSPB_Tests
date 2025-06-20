package com.bspbtests.tests.calulationtests;

import com.bspbtests.pages.CalculatorForm;
import com.bspbtests.pages.MainPage;
import com.bspbtests.pages.WhiteNightsInvestmentPage;
import com.bspbtests.tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class InvestmentRateTest extends BaseTest {

    @Test
    public void investmentRateTest(){
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.isDisplayed());
        mainPage.hoverInvestments();
        mainPage.clickWhiteNights();
        WhiteNightsInvestmentPage whiteNightsInvestmentPage = new WhiteNightsInvestmentPage();
        Assert.assertTrue(whiteNightsInvestmentPage.isDisplayed());
        CalculatorForm calculatorForm = new CalculatorForm();
        Assert.assertTrue(calculatorForm.isDisplayed());
        calculatorForm.setInvestmentSum("1000000"); //TODO хардкод
        calculatorForm.click31Days();

        String interestAmountText = calculatorForm.getInterestAmount();
        interestAmountText = interestAmountText.replaceAll("\\D+", ""); //TODO сделать функцию для преобразования
        long interestValue = Long.parseLong(interestAmountText);

        String investmentRateText = calculatorForm.getInvestmentRate();
        investmentRateText = investmentRateText.replaceAll("\\D+", "");
        long investmentRate = Long.parseLong(investmentRateText);

        Assert.assertEquals(13589, interestValue); //TODO хардкод
        Assert.assertEquals(16, investmentRate);
    }
}
