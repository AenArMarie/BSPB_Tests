package com.bspbtests.steps;

import com.bspbtests.constants.StringConstants;
import com.bspbtests.pages.CurrencyConversionFormI;
import com.bspbtests.pages.MainPage;
import com.bspbtests.utility.dataprocessing.StringProcessing;
import com.bspbtests.utility.ProjectLogger;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.assertj.core.api.Assumptions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class CurrencyExchangeSteps {

    @Дано("пользователь открывает форму конвертации валют")
    public void openConversionForm() {
        MainPage.clickBuyCurrencyButton();
        Assumptions.assumeThat(CurrencyConversionFormI.isDisplayed()).isTrue();
    }

    @Дано("выбрана исходная валюта с текстом {string}")
    public void selectExistingCurrency(String valueType) {
        CurrencyConversionFormI.clickExistingCurrenciesDropDownButton();
        CurrencyConversionFormI.selectAsExistingCurrencyByText(valueType);
    }

    @Дано("выбрана целевая валюта с текстом {string}")
    public void selectTargetCurrency(String valueType) {
        CurrencyConversionFormI.clickConvertedCurrenciesDropDownButton();
        CurrencyConversionFormI.selectAsConvertedCurrencyByText(valueType);
    }

    @Тогда("вычисленное значение равно конверсии количества валюты {double} по курсу для {string} с погрешностью {double}")
    public void verifyConversion(double value, String conversionSign, double margin) {
        String rawRate = CurrencyConversionFormI.getConversionRateByPartialText(conversionSign);
        String rateStr = StringProcessing.splitStringByTextAndGetPart(rawRate, StringConstants.EQUALS_SEPARATOR, 1);
        double rate = Double.parseDouble(StringProcessing.splitStringByTextAndGetPart(rateStr, StringConstants.ALL_SPACES, 0));

        double expected = rate * value;
        double actual = Double.parseDouble(CurrencyConversionFormI.getConvertedCurrencyAmount().replaceAll(StringConstants.ALL_SPACES, ""));

        ProjectLogger.info(String.format("""
               Rate: %.2f
               Expected: %.2f
               Actual: %.2f
               """,
                rate, expected, actual));

        assertThat(actual)
                .as("Проверка данных о конвертации")
                .isCloseTo(expected, within(margin));
    }

    @Когда("пользователь вводит количество валюты {double}")
    public void enterLargeAmount(double value) {
        CurrencyConversionFormI.setExistingCurrencyAmount(String.valueOf(value));
    }
}
