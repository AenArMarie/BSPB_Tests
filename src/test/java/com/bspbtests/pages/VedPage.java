package com.bspbtests.pages;

import org.openqa.selenium.By;

public class VedPage extends BaseForm{

    public VedPage() {
        super(By.xpath("//*[contains(@class, 'css-1y4qnd') and contains(., 'ВЭД360')]"), "Пункт 'ВЕД360'");
    }
}
