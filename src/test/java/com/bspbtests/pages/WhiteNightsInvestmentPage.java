package com.bspbtests.pages;

import org.openqa.selenium.By;

public class WhiteNightsInvestmentPage extends BaseForm{

    public WhiteNightsInvestmentPage() {
        super(By.xpath("//*[contains(@class, 'css-jlri05') and contains(., 'Белые ночи')]"), "Страница 'Белые ночи'");
    }
}
