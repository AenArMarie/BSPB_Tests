package com.bspbtests.steps;

import com.bspbtests.constants.StringConstants;
import com.bspbtests.pages.CurrencyConversionForm;
import com.bspbtests.pages.MainPage;
import com.bspbtests.utility.dataprocessing.StringProcessing;
import com.utility.logger.ProjectLogger;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;

import static com.bspbtests.steps.Hooks.testData;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class CurrencyExchangeSteps {

    @Дано("он открывает форму конвертации валют")
    public void openConversionForm() {
        ProjectLogger.info("Открытие страницы покупки валюты");
        MainPage mainPage = new MainPage();
        mainPage.clickBuyCurrencyButton();
        CurrencyConversionForm currencyConversionForm = new CurrencyConversionForm();
        currencyConversionForm.isDisplayed();
    }

    @Дано("выбрана исходная валюта")
    public void selectExistingCurrency() {
        CurrencyConversionForm currencyConversionForm = new CurrencyConversionForm();
        ProjectLogger.info("Выбор имеющейся валюты: " + testData.getCurrencyExchangeData().getExistingCurrencyText());
        currencyConversionForm.clickExistingCurrenciesDropDownButton();
        currencyConversionForm.selectAsExistingCurrencyByText(testData.getCurrencyExchangeData().getExistingCurrencyText());
    }

    @Дано("выбрана целевая валюта")
    public void selectTargetCurrency() {
        CurrencyConversionForm currencyConversionForm = new CurrencyConversionForm();
        ProjectLogger.info("Выбор конвертированной валюты: " + testData.getCurrencyExchangeData().getConvertedCurrencyText());
        currencyConversionForm.clickConvertedCurrenciesDropDownButton();
        currencyConversionForm.selectAsConvertedCurrencyByText(testData.getCurrencyExchangeData().getConvertedCurrencyText());
    }

    @Когда("он вводит маленькое количество валюты")
    public void enterSmallAmount() {
        CurrencyConversionForm currencyConversionForm = new CurrencyConversionForm();
        double amount = testData.getCurrencyExchangeData().getAmountBelowThreshold();
        ProjectLogger.info("Установка количества валюты: " + amount);
        currencyConversionForm.setExistingCurrencyAmount(String.valueOf(amount));
    }

    @Тогда("расчет конверсии соответствует ожидаемому значению с допустимой погрешностью")
    public void verifySmallConversion() {
        CurrencyConversionForm currencyConversionForm = new CurrencyConversionForm();
        String rawRate = currencyConversionForm.getConversionRateByPartialText(testData.getCurrencyExchangeData().getMinimalConversionRateCurrency());
        String rateStr = StringProcessing.splitStringByTextAndGetPart(rawRate, StringConstants.EQUALS_SEPARATOR, 1);
        double rate = Double.parseDouble(StringProcessing.splitStringByTextAndGetPart(rateStr, StringConstants.ALL_SPACES, 0));

        double expected = rate * testData.getCurrencyExchangeData().getAmountBelowThreshold();
        double actual = Double.parseDouble(currencyConversionForm.getConvertedCurrencyAmount().replaceAll(StringConstants.ALL_SPACES, ""));
        double margin = testData.getCurrencyExchangeData().getMarginOfError();

        assertThat(actual)
                .as("Проверка данных о конвертации")
                .isCloseTo(expected, within(margin));
    }

    @Когда("он вводит большое количество валюты")
    public void enterLargeAmount() {
        CurrencyConversionForm currencyConversionForm = new CurrencyConversionForm();
        double amount = testData.getCurrencyExchangeData().getAmountAboveThreshold();
        ProjectLogger.info("Установка количества валюты: " + amount);
        currencyConversionForm.setExistingCurrencyAmount(String.valueOf(amount));
    }

    @Тогда("расчет конверсии также соответствует ожидаемому значению с допустимой погрешностью")
    public void verifyLargeConversion() {
        CurrencyConversionForm currencyConversionForm = new CurrencyConversionForm();
        String rawRate = currencyConversionForm.getConversionRateByPartialText(testData.getCurrencyExchangeData().getThresholdForDiffConversionRateText());
        String rateStr = StringProcessing.splitStringByTextAndGetPart(rawRate, StringConstants.EQUALS_SEPARATOR, 1);
        double rate = Double.parseDouble(StringProcessing.splitStringByTextAndGetPart(rateStr, StringConstants.ALL_SPACES, 0));

        double expected = rate * testData.getCurrencyExchangeData().getAmountAboveThreshold();
        double actual = Double.parseDouble(currencyConversionForm.getConvertedCurrencyAmount().replaceAll(StringConstants.ALL_SPACES, ""));
        double margin = testData.getCurrencyExchangeData().getMarginOfError();

        assertThat(actual)
                .as("Проверка данных о конвертации")
                .isCloseTo(expected, within(margin));
    }
}
