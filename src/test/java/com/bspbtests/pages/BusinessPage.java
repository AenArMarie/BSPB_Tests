package com.bspbtests.pages;

import org.openqa.selenium.By;

public class BusinessPage extends BaseForm{

    public BusinessPage() {
        super(By.xpath("//*[contains(@class, 'css-1y4qndl') and contains(., 'Зарплатный')]"), "Пункт 'Зарплатный проект'");
    }
}
