package com.bspbtests.pages;

import org.openqa.selenium.By;

public class InvestorPage extends BaseForm{

    public InvestorPage() {
        super(By.xpath("//*[contains(@class, 'css-1y4qnd') and contains(., 'акционеров')]"), "Пункт 'Информация для акционеров'");
    }
}
