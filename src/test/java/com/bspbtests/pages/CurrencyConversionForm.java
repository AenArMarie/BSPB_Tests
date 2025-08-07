package com.bspbtests.pages;

import com.bspbtests.constants.AttributeConstants;
import com.bspbtests.pages.baseform.IBaseForm;
import com.bspbtests.webelements.Element;
import com.bspbtests.webelements.Input;
import org.openqa.selenium.By;

/**
 * Калькулятор обмена валют
 */
public class CurrencyConversionForm implements IBaseForm {

    private static final Element existingDropDownButton = new Element(By.xpath("//button[contains(@id, 'menu-button') and ancestor::*[contains(@class, 'css-17m0t2z')]]"),
            "Кнопка открытия выпадающего меню с выбором имеющейся валюты");
    private static final Element convertedDropDownButton = new Element(By.xpath("//button[contains(@id, 'menu-button') and ancestor::*[contains(@class, 'css-i8yqn1')]]"),
            "Кнопка открытия выпадающего меню с выбором переведенной валюты");

    private static final String existingCurrencyMenuButtonXpathTemplate = "//button[@role = 'menuitem' and contains(., '%s') and ancestor::*[contains(@class, 'css-17m0t2z')] ]";
    private static final String convertedCurrencyMenuButtonXpathTemplate = "//button[@role = 'menuitem' and contains(., '%s') and ancestor::*[contains(@class, 'css-i8yqn1')] ]";

    private static final String conversionRateTextTemplate = "//*[contains(@class, 'css-v61jaa') and ancestor::*[contains(@class, 'css-17m0t2z')] and ancestor::*[contains(@id, 'tabpanel-0')] and contains(. ,'%s')]";

    private static final Input existingCurrencyAmountInput = new Input(By.xpath("//input[ancestor::*[contains(@class, 'css-17m0t2z')] and ancestor::*[contains(@id, 'tabpanel-0')]]"), "Поле ввода имеющейся валюты");
    private static final Input convertedCurrencyAmountInput = new Input(By.xpath("//input[ancestor::*[contains(@class, 'css-i8yqn1')] and ancestor::*[contains(@id, 'tabpanel-0')]]"), "Поле ввода имеющейся валюты");

    /**
     * @see IBaseForm#isDisplayed()
     */
    public static boolean isDisplayed() {
        return new Element(By.className("css-rrf209"), "Форма конвертации валюты").getElement().isDisplayed();
    }

    /**
     * Нажатие кнопки раскрытия имеющейся валюты
     */
    public static void clickExistingCurrenciesDropDownButton() {
        existingDropDownButton.click();
    }

    /**
     * Нажатие кнопки раскрытия конвертированной валюты
     */
    public static void clickConvertedCurrenciesDropDownButton() {
        convertedDropDownButton.click();
    }

    /**
     * Метод выбора имеющейся валюты по тексту
     *
     * @param text фрагмент текста выбираемой валюты
     */
    public static void selectAsExistingCurrencyByText(String text) {
        Element existingCurrency = new Element(By.xpath(String.format(existingCurrencyMenuButtonXpathTemplate, text)),
                String.format("Кнопка с текстом '%s' в меню имеющейся валюты", text));
        existingCurrency.click();
    }

    /**
     * Метод выбора конвертированной валюты по тексту
     *
     * @param text фрагмент текста выбираемой валюты
     */
    public static void selectAsConvertedCurrencyByText(String text) {
        Element convertedCurrency = new Element(By.xpath(String.format(convertedCurrencyMenuButtonXpathTemplate, text)),
                String.format("Кнопка с текстом '%s' в меню переведенной валюты", text));
        convertedCurrency.click();
    }

    /**
     * Установка количества обмениваемой валюты
     * @param value количество обмениваемой валюты
     */
    public static void setExistingCurrencyAmount(String value) {
        existingCurrencyAmountInput.setInput(value);
    }

    /**
     * Метод получения количества конвертированной валюты
     *
     * @return количество конвертированной валюты в формате {@link String}
     */
    public static String getConvertedCurrencyAmount() {
        return convertedCurrencyAmountInput.getAttribute(AttributeConstants.VALUE);
    }

    /**
     * Получение курса обмена по тексту
     *
     * @param partialText фрагмент текста из курса обмена
     * @return полный текст курса обмена в формате {@link String}
     */
    public static String getConversionRateByPartialText(String partialText) {
        Element conversionRate = new Element(By.xpath(String.format(conversionRateTextTemplate, partialText)), String.format("Текст с информацией о конвертации, содержащий '%s'", partialText));
        return conversionRate.getText();
    }
}
