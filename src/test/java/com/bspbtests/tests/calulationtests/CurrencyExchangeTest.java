package com.bspbtests.tests.calulationtests;

import com.bspbtests.constants.CommonLogMessages;
import com.bspbtests.constants.StringConstants;
import com.bspbtests.pages.CurrencyConversionForm;
import com.bspbtests.pages.MainPage;
import com.bspbtests.tests.basetest.BaseTest;
import com.utility.dataprocessing.NumericComparisons;
import com.utility.dataprocessing.StringProcessing;
import com.utility.logger.ProjectLogger;
import org.junit.Assert;
import org.junit.Test;

public class CurrencyExchangeTest extends BaseTest {

    @Test
    public void currencyExchangeTest() {
        ProjectLogger.info(CommonLogMessages.MAIN_PAGE_CHECK_LOG);
        MainPage mainPage = new MainPage();
        Assert.assertTrue(CommonLogMessages.MAIN_PAGE_NOT_DISPLAYED, mainPage.isDisplayed());
        ProjectLogger.info("Открытие страницы покупки валюты");
        mainPage.clickBuyCurrencyButton();

        ProjectLogger.info("Проверка открытия страницы покупки валюты");
        CurrencyConversionForm currencyConversionForm = new CurrencyConversionForm();
        Assert.assertTrue("Форма с калькулятором конвертации валюты не отображена", currencyConversionForm.isDisplayed());

        ProjectLogger.info("Выбор имеющейса валюты: " + testData.getCurrencyExchangeData().getExistingCurrencyText());
        currencyConversionForm.clickExistingCurrenciesDropDownButton();
        currencyConversionForm.selectAsExistingCurrencyByText(testData.getCurrencyExchangeData().getExistingCurrencyText());

        ProjectLogger.info("Выбор конвертированной валюты: " + testData.getCurrencyExchangeData().getConvertedCurrencyText());
        currencyConversionForm.clickConvertedCurrenciesDropDownButton();
        currencyConversionForm.selectAsConvertedCurrencyByText(testData.getCurrencyExchangeData().getConvertedCurrencyText());

        ProjectLogger.info("Установка имеющейгося количества валюты " + testData.getCurrencyExchangeData().getAmountBelowThreshold());
        currencyConversionForm.setExistingCurrencyAmount(String.valueOf(testData.getCurrencyExchangeData().getAmountBelowThreshold()));
        ProjectLogger.info("Получение конвертации при малом количестве валюты");
        String firstConversionText = StringProcessing.splitStringByTextAndGetPart(
                currencyConversionForm.getConversionRateByPartialText(testData.getCurrencyExchangeData().getMinimalConversionRateCurrency()),
                StringConstants.EQUALS_SEPARATOR, 1);
        double firstConversionRate = Double.parseDouble(StringProcessing.splitStringByTextAndGetPart(firstConversionText, StringConstants.ALL_SPACES, 0));
        ProjectLogger.info("Проверка расчетов");
        Assert.assertTrue("Данные о конвертации не верны", NumericComparisons.equalsWithMargin(firstConversionRate * testData.getCurrencyExchangeData().getAmountBelowThreshold(),
                Double.parseDouble(currencyConversionForm.getConvertedCurrencyAmount().replaceAll(StringConstants.ALL_SPACES, StringConstants.EMPTY_STRING)), testData.getCurrencyExchangeData().getMarginOfError()));

        ProjectLogger.info("Установка имеющейгося количества валюты " + testData.getCurrencyExchangeData().getAmountAboveThreshold());
        currencyConversionForm.setExistingCurrencyAmount(String.valueOf(testData.getCurrencyExchangeData().getAmountAboveThreshold()));
        ProjectLogger.info("Получение конвертации при большом количестве валюты");
        String secondConversionText = StringProcessing.splitStringByTextAndGetPart(
                currencyConversionForm.getConversionRateByPartialText(testData.getCurrencyExchangeData().getThresholdForDiffConversionRateText()),
                StringConstants.EQUALS_SEPARATOR, 1);
        double secondConversionRate = Double.parseDouble(StringProcessing.splitStringByTextAndGetPart(secondConversionText, StringConstants.ALL_SPACES, 0));
        ProjectLogger.info("Проверка расчетов");
        Assert.assertTrue("Данные о конвертации не верны", NumericComparisons.equalsWithMargin(secondConversionRate * testData.getCurrencyExchangeData().getAmountAboveThreshold(),
                Double.parseDouble(currencyConversionForm.getConvertedCurrencyAmount().replaceAll(StringConstants.ALL_SPACES, StringConstants.EMPTY_STRING)), testData.getCurrencyExchangeData().getMarginOfError()));
    }
}
