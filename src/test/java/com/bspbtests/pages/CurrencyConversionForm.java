package com.bspbtests.pages;

import com.utility.webelements.Element;
import com.utility.webelements.Input;
import org.openqa.selenium.By;

public class CurrencyConversionForm extends BaseForm{

    private Element existingUsd;
    private Element convertedRub;

    private Element existingDropDownButton = new Element(By.xpath("//button[contains(@id, 'menu-button') and ancestor::*[contains(@class, 'css-17m0t2z')]]"),
            "Кнопка открытия выпадающего меню с выбором имеющейся валюты");
    private Element convertedDropDownButton = new Element(By.xpath("//button[contains(@id, 'menu-button') and ancestor::*[contains(@class, 'css-i8yqn1')]]"),
            "Кнопка открытия выпадающего меню с выбором переведенной валюты");

    private String existingCurrencyMenuButtonXpathTemplate = "//button[@role = 'menuitem' and contains(., '%s') and ancestor::*[contains(@class, 'css-17m0t2z')] ]";
    private String convertedCurrencyMenuButtonXpathTemplate = "//button[@role = 'menuitem' and contains(., '%s') and ancestor::*[contains(@class, 'css-i8yqn1')] ]";

    private String conversionRateTextTemplate = "//*[contains(@class, 'css-v61jaa') and ancestor::*[contains(@class, 'css-17m0t2z')] and ancestor::*[contains(@id, 'tabpanel-0')] and contains(. ,'%s')]";

    private Input existingCurrencyAmountInput = new Input(By.xpath("//input[ancestor::*[contains(@class, 'css-17m0t2z')] and ancestor::*[contains(@id, 'tabpanel-0')]]"), "Поле ввода имеющейся валюты");
    private Input convertedCurrencyAmountInput = new Input(By.xpath("//input[ancestor::*[contains(@class, 'css-i8yqn1')] and ancestor::*[contains(@id, 'tabpanel-0')]]"), "Поле ввода имеющейся валюты");

    public CurrencyConversionForm() {
        super(By.className("css-1l0102k"), "Форма конвертации валюты"); //TODO 2 найденных элемента по локатору
    }

    public void clickExistingCurrenciesDropDownButton(){
        existingDropDownButton.click();
    }

    public void clickConvertedCurrenciesDropDownButton(){
        convertedDropDownButton.click();
    }

    public void clickUsdAsExistingCurrency(){
        existingUsd = new Element(By.xpath(String.format(existingCurrencyMenuButtonXpathTemplate, "Доллар США")), //TODO хардкод, делать универсальным
                "Кнопка выбора доллара в меню имеющейся валюты");
        existingUsd.click();
    }

    public void clickRubAsConvertedCurrency(){
        convertedRub = new Element(By.xpath(String.format(convertedCurrencyMenuButtonXpathTemplate, "Российский рубль")),
                "Кнопка выбора рубля в меню переведенной валюты");
        convertedRub.click();
    }

    public void setExistingCurrencyAmount(String value){
        existingCurrencyAmountInput.setInput(value);
    }

    public String getConvertedCurrencyAmount(){
        return convertedCurrencyAmountInput.getAttribute("value"); //TODO хардкод
    }

    public String getConversionRateByPartialText(String partialText){
        Element conversionRate = new Element(By.xpath(String.format(conversionRateTextTemplate, partialText)), ""); //TODO имя
        return conversionRate.getText();
    }
}
