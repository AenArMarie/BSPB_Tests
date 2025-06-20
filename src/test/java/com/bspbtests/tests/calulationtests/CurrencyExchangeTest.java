package com.bspbtests.tests.calulationtests;

import com.bspbtests.pages.CurrencyConversionForm;
import com.bspbtests.pages.MainPage;
import com.bspbtests.tests.BaseTest;
import com.utility.comparisons.NumericComparisons;
import org.junit.Assert;
import org.junit.Test;

public class CurrencyExchangeTest extends BaseTest {

    @Test
    public void currencyExchangeTest() {
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.isDisplayed());
        mainPage.clickBuyCurrencyButton();
        CurrencyConversionForm currencyConversionForm = new CurrencyConversionForm();
        Assert.assertTrue(currencyConversionForm.isDisplayed());

        currencyConversionForm.clickExistingCurrenciesDropDownButton();
        currencyConversionForm.clickUsdAsExistingCurrency();

        currencyConversionForm.clickConvertedCurrenciesDropDownButton();
        currencyConversionForm.clickRubAsConvertedCurrency();

        currencyConversionForm.setExistingCurrencyAmount("499");
        String firstConversionText = currencyConversionForm.getConversionRateByPartialText("1 USD");
        double firstConversionRate = 0;

        String[] parts = firstConversionText.split("=\\s*");
        if (parts.length > 1) {
            String afterEq = parts[1];
            String numberStr = afterEq.split("\\s+")[0];
            firstConversionRate = Double.parseDouble(numberStr);
        }
        Assert.assertTrue(NumericComparisons.equalsWithMargin(firstConversionRate * 499, Double.parseDouble(currencyConversionForm.getConvertedCurrencyAmount().replaceAll("\\s+", "")), 0.1)); //TODO хардкод

        currencyConversionForm.setExistingCurrencyAmount("501");
        String secondConversionText = currencyConversionForm.getConversionRateByPartialText("500 USD");
        double secondConversionRate = 0;

        String[] parts2 = secondConversionText.split("=\\s*");
        if (parts2.length > 1) {
            String afterEq = parts2[1];
            String numberStr = afterEq.split("\\s+")[0];
            secondConversionRate = Double.parseDouble(numberStr);
        }
        Assert.assertTrue(NumericComparisons.equalsWithMargin(secondConversionRate * 501, Double.parseDouble(currencyConversionForm.getConvertedCurrencyAmount().replaceAll("\\s+", "")), 0.1)); //TODO хардкод
    }
}
