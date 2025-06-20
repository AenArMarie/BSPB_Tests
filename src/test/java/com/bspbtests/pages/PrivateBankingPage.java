package com.bspbtests.pages;

import org.openqa.selenium.By;

public class PrivateBankingPage extends BaseForm{

    public PrivateBankingPage() {
        super(By.className("t-cover__wrapper"), "Обложка страницы");
    }
}
