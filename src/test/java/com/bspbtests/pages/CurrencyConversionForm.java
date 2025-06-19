package com.bspbtests.pages;

import com.utility.webelements.Element;
import org.openqa.selenium.By;

public class CurrencyConversionForm extends BaseForm{

    private Element existingUsd = new Element(By.id("menu-list-:rj:-menuitem-:rr:"), "Кнопка выбора доллара в меню имеющейся валюты");
    private Element convertedRub = new Element(By.id("menu-list-:rt:-menuitem-:rv:"), "Кнопка выбора рубля в меню переведенной валюты");

    private Element existingDropDownButton = new Element(By.id("menu-button-:rj:"), "Кнопка открытия выпадающего меню с выбором имеющейся валюты");
    private Element convertedDropDownButton = new Element(By.id("menu-button-:rt:"), "Кнопка открытия выпадающего меню с выбором переведенной валюты");

    public CurrencyConversionForm() {
        super(By.className("css-1l0102k"), "Форма конвертации валюты"); //TODO 2 найденных элемента по локатору
    }

    public void clickExistingCurrenciesDropDownButton(){
        existingDropDownButton.click();
    }

    public void clickConvertedCurrenciesDropDownButton(){
        convertedDropDownButton.click();
    }

    public void selectUsdAsExistingCurrency(){
        existingUsd.click();
    }

    public void selectRubAsConvertedCurrency(){
        convertedRub.click();
    }
}
