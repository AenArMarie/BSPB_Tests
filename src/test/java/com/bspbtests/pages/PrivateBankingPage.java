package com.bspbtests.pages;

import com.bspbtests.pages.baseform.BaseForm;
import org.openqa.selenium.By;

public class PrivateBankingPage extends BaseForm {

    public PrivateBankingPage() {
        super(By.className("t-cover__wrapper"), "Обложка страницы");
    }
}
