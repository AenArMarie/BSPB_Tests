package com.bspbtests.pages;

import org.openqa.selenium.By;

public class FinancePage extends BaseForm{

    public FinancePage() {
        super(By.xpath("//*[contains(@class, 'css-1y4qnd') and contains(., 'Финансовым')]"), "Пункт 'Фенансовым институтам'");
    }
}
