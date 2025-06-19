package com.bspbtests.pages;

import com.utility.webelements.Element;
import org.openqa.selenium.By;

public class MainPage extends BaseForm{

    Element investmentsMenu = new Element(By.xpath("//*[contains(@class, 'css-1y4qndl') and contains(., 'Вклады')]"), "Вклады"); //TODO вынести в верхнее меню
    Element whiteNightsInvestment = new Element(By.xpath("//a[contains(@class, 'css-70yt0k') and contains(., 'Белые ночи')]"), "Ссылка на вклад 'Белые ночи'");
    Element buyCurrencyButton = new Element(By.xpath("//*[contains(@class, 'css-oen4ev')]"), "Кнопка 'Купить валюту'");

    public MainPage() {
        super(By.xpath("//*[contains(@class, 'css-2wq498') and contains(., 'Ипотека')]"), "Карточка 'Ипотека'");
    }

    public void hoverInvestments() {
        investmentsMenu.hover();
    }

    public void clickWhiteNights() {
        whiteNightsInvestment.click();
    }

    public void clickBuyCurrencyButton(){
        buyCurrencyButton.click();
    }
}
