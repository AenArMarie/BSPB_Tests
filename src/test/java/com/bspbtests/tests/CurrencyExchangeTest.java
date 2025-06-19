package com.bspbtests.tests;

import com.bspbtests.pages.CurrencyConversionForm;
import com.bspbtests.pages.MainPage;
import org.junit.Assert;
import org.junit.Test;

public class CurrencyExchangeTest extends BaseTest{

    @Test
    public void currencyExchangeTest() {
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.isDisplayed());
        mainPage.clickBuyCurrencyButton();
        CurrencyConversionForm currencyConversionForm = new CurrencyConversionForm();
        Assert.assertTrue(currencyConversionForm.isDisplayed());

        currencyConversionForm.clickExistingCurrenciesDropDownButton();
        currencyConversionForm.selectUsdAsExistingCurrency();

        currencyConversionForm.clickConvertedCurrenciesDropDownButton();
        currencyConversionForm.selectRubAsConvertedCurrency();
    }
}
