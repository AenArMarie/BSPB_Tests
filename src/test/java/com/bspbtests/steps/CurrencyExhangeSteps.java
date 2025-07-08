package com.bspbtests.steps;

import com.bspbtests.constants.StringConstants;
import com.bspbtests.pages.CurrencyConversionForm;
import com.bspbtests.pages.MainPage;
import com.bspbtests.utility.dataprocessing.NumericComparisons;
import com.bspbtests.utility.dataprocessing.StringProcessing;
import com.utility.logger.ProjectLogger;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;

import static com.bspbtests.steps.Hooks.testData;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CurrencyExhangeSteps {

    private MainPage mainPage;
    private CurrencyConversionForm currencyConversionForm;


    @Дано("он открывает форму конвертации валют")
    public void openConversionForm() {
        ProjectLogger.info("Открытие страницы покупки валюты");
        mainPage = new MainPage();
        mainPage.clickBuyCurrencyButton();
        currencyConversionForm = new CurrencyConversionForm();
        assertTrue(currencyConversionForm.isDisplayed(), "Форма конвертации не отображена");
    }

    @Дано("выбрана исходная валюта")
    public void selectExistingCurrency() {
        ProjectLogger.info("Выбор имеющейся валюты: " + testData.getCurrencyExchangeData().getExistingCurrencyText());
        currencyConversionForm.clickExistingCurrenciesDropDownButton();
        currencyConversionForm.selectAsExistingCurrencyByText(testData.getCurrencyExchangeData().getExistingCurrencyText());
    }

    @Дано("выбрана целевая валюта")
    public void selectTargetCurrency() {
        ProjectLogger.info("Выбор конвертированной валюты: " + testData.getCurrencyExchangeData().getConvertedCurrencyText());
        currencyConversionForm.clickConvertedCurrenciesDropDownButton();
        currencyConversionForm.selectAsConvertedCurrencyByText(testData.getCurrencyExchangeData().getConvertedCurrencyText());
    }

    @Когда("он вводит маленькое количество валюты")
    public void enterSmallAmount() {
        double amount = testData.getCurrencyExchangeData().getAmountBelowThreshold();
        ProjectLogger.info("Установка количества валюты: " + amount);
        currencyConversionForm.setExistingCurrencyAmount(String.valueOf(amount));
    }

    @Тогда("расчет конверсии соответствует ожидаемому значению с допустимой погрешностью")
    public void verifySmallConversion() {
        String rawRate = currencyConversionForm.getConversionRateByPartialText(testData.getCurrencyExchangeData().getMinimalConversionRateCurrency());
        String rateStr = StringProcessing.splitStringByTextAndGetPart(rawRate, StringConstants.EQUALS_SEPARATOR, 1);
        double rate = Double.parseDouble(StringProcessing.splitStringByTextAndGetPart(rateStr, StringConstants.ALL_SPACES, 0));

        double expected = rate * testData.getCurrencyExchangeData().getAmountBelowThreshold();
        double actual = Double.parseDouble(currencyConversionForm.getConvertedCurrencyAmount().replaceAll(StringConstants.ALL_SPACES, ""));
        double margin = testData.getCurrencyExchangeData().getMarginOfError();

        ProjectLogger.info("Проверка расчета для маленького количества");
        assertTrue(NumericComparisons.equalsWithMargin(expected, actual, margin), "Данные о конвертации не верны");
    }

    @Когда("он вводит большое количество валюты")
    public void enterLargeAmount() {
        double amount = testData.getCurrencyExchangeData().getAmountAboveThreshold();
        ProjectLogger.info("Установка количества валюты: " + amount);
        currencyConversionForm.setExistingCurrencyAmount(String.valueOf(amount));
    }

    @Тогда("расчет конверсии также соответствует ожидаемому значению с допустимой погрешностью")
    public void verifyLargeConversion() {
        String rawRate = currencyConversionForm.getConversionRateByPartialText(testData.getCurrencyExchangeData().getThresholdForDiffConversionRateText());
        String rateStr = StringProcessing.splitStringByTextAndGetPart(rawRate, StringConstants.EQUALS_SEPARATOR, 1);
        double rate = Double.parseDouble(StringProcessing.splitStringByTextAndGetPart(rateStr, StringConstants.ALL_SPACES, 0));

        double expected = rate * testData.getCurrencyExchangeData().getAmountAboveThreshold();
        double actual = Double.parseDouble(currencyConversionForm.getConvertedCurrencyAmount().replaceAll(StringConstants.ALL_SPACES, ""));
        double margin = testData.getCurrencyExchangeData().getMarginOfError();

        ProjectLogger.info("Проверка расчета для большого количества");
        assertTrue(NumericComparisons.equalsWithMargin(expected, actual, margin), "Данные о конвертации не верны");
    }
}
