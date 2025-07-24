package com.bspbtests.steps;

import com.bspbtests.constants.StringConstants;
import com.bspbtests.pages.CurrencyConversionForm;
import com.bspbtests.pages.MainPage;
import com.bspbtests.utility.dataprocessing.StringProcessing;
import com.utility.logger.ProjectLogger;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Step;

import static com.bspbtests.steps.Hooks.testData;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class CurrencyExchangeSteps {

    @Дано("пользователь открывает форму конвертации валют")
    @Step("Открытие формы конвертации валют")
    public void openConversionForm() {
        ProjectLogger.info("Открытие страницы покупки валюты");
        MainPage mainPage = new MainPage();
        mainPage.clickBuyCurrencyButton();
        CurrencyConversionForm currencyConversionForm = new CurrencyConversionForm();
        currencyConversionForm.isDisplayed();
    }

    @Дано("выбрана исходная валюта")
    @Step("Выбор исходной валюты")
    public void selectExistingCurrency() {
        CurrencyConversionForm currencyConversionForm = new CurrencyConversionForm();
        ProjectLogger.info("Выбор имеющейся валюты: " + testData.getCurrencyExchangeData().getExistingCurrencyText());
        currencyConversionForm.clickExistingCurrenciesDropDownButton();
        currencyConversionForm.selectAsExistingCurrencyByText(testData.getCurrencyExchangeData().getExistingCurrencyText());
    }

    @Дано("выбрана целевая валюта")
    @Step("Выбор целевой валюты")
    public void selectTargetCurrency() {
        CurrencyConversionForm currencyConversionForm = new CurrencyConversionForm();
        ProjectLogger.info("Выбор конвертированной валюты: " + testData.getCurrencyExchangeData().getConvertedCurrencyText());
        currencyConversionForm.clickConvertedCurrenciesDropDownButton();
        currencyConversionForm.selectAsConvertedCurrencyByText(testData.getCurrencyExchangeData().getConvertedCurrencyText());
    }

    @Тогда("расчет конверсии количества валюты {double} соответствует ожидаемому значению с допустимой погрешностью {double}")
    @Step("Проверка на соответствие конверсии количества валюты тестовым данным")
    public void verifySmallConversion(double value, double margin) {
        CurrencyConversionForm currencyConversionForm = new CurrencyConversionForm();
        String rawRate;
        if (value < 500.0)
            rawRate = currencyConversionForm.getConversionRateByPartialText(testData.getCurrencyExchangeData().getMinimalConversionRateCurrency());
        else
            rawRate = currencyConversionForm.getConversionRateByPartialText(testData.getCurrencyExchangeData().getThresholdForDiffConversionRateText());
        String rateStr = StringProcessing.splitStringByTextAndGetPart(rawRate, StringConstants.EQUALS_SEPARATOR, 1);
        double rate = Double.parseDouble(StringProcessing.splitStringByTextAndGetPart(rateStr, StringConstants.ALL_SPACES, 0));

        double expected = rate * value;
        double actual = Double.parseDouble(currencyConversionForm.getConvertedCurrencyAmount().replaceAll(StringConstants.ALL_SPACES, ""));

        assertThat(actual)
                .as("Проверка данных о конвертации")
                .isCloseTo(expected, within(margin));
    }

    @Когда("пользователь вводит количество валюты {double}")
    @Step("Ввод большого количества валюты")
    public void enterLargeAmount(double value) {
        CurrencyConversionForm currencyConversionForm = new CurrencyConversionForm();
        ProjectLogger.info("Установка количества валюты: " + value);
        currencyConversionForm.setExistingCurrencyAmount(String.valueOf(value));
    }

    @Тогда("расчет конверсии также соответствует ожидаемому значению с допустимой погрешностью")
    @Step("Проверка на соответствие конверсии большого количества валюты тестовым данным")
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
