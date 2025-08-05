package com.bspbtests.steps;

import com.bspbtests.constants.StringConstants;
import com.bspbtests.pages.CurrencyConversionForm;
import com.bspbtests.pages.MainPage;
import com.bspbtests.utility.dataprocessing.StringProcessing;
import com.utility.logger.ProjectLogger;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.assertj.core.api.Assumptions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class CurrencyExchangeSteps {

    @Дано("пользователь открывает форму конвертации валют")
    public void openConversionForm() {
        ProjectLogger.info("Открытие страницы покупки валюты");
        MainPage mainPage = new MainPage();
        mainPage.clickBuyCurrencyButton();
        CurrencyConversionForm currencyConversionForm = new CurrencyConversionForm();
        Assumptions.assumeThat(currencyConversionForm.isDisplayed());
    }

    @Дано("выбрана исходная валюта с текстом {string}")
    public void selectExistingCurrency(String valueType) {
        CurrencyConversionForm currencyConversionForm = new CurrencyConversionForm();
        ProjectLogger.info("Выбор имеющейся валюты: " + valueType);
        currencyConversionForm.clickExistingCurrenciesDropDownButton();
        currencyConversionForm.selectAsExistingCurrencyByText(valueType);
    }

    @Дано("выбрана целевая валюта с текстом {string}")
    public void selectTargetCurrency(String valueType) {
        CurrencyConversionForm currencyConversionForm = new CurrencyConversionForm();
        ProjectLogger.info("Выбор конвертированной валюты: " + valueType);
        currencyConversionForm.clickConvertedCurrenciesDropDownButton();
        currencyConversionForm.selectAsConvertedCurrencyByText(valueType);
    }

    @Тогда("вычисленное значение равно конверсии количества валюты {double} по курсу для {string} с погрешностью {double}")
    public void verifyConversion(double value, String conversionSign, double margin) {
        CurrencyConversionForm currencyConversionForm = new CurrencyConversionForm();
        String rawRate = currencyConversionForm.getConversionRateByPartialText(conversionSign);
        String rateStr = StringProcessing.splitStringByTextAndGetPart(rawRate, StringConstants.EQUALS_SEPARATOR, 1);
        double rate = Double.parseDouble(StringProcessing.splitStringByTextAndGetPart(rateStr, StringConstants.ALL_SPACES, 0));

        double expected = rate * value;
        double actual = Double.parseDouble(currencyConversionForm.getConvertedCurrencyAmount().replaceAll(StringConstants.ALL_SPACES, ""));

        assertThat(actual)
                .as("Проверка данных о конвертации")
                .isCloseTo(expected, within(margin));
    }

    @Когда("пользователь вводит количество валюты {double}")
    public void enterLargeAmount(double value) {
        CurrencyConversionForm currencyConversionForm = new CurrencyConversionForm();
        ProjectLogger.info("Установка количества валюты: " + value);
        currencyConversionForm.setExistingCurrencyAmount(String.valueOf(value));
    }
}
