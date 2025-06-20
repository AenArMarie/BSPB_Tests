package com.bspbtests.tests.navigationtests;

import com.bspbtests.pages.FinancePage;
import com.bspbtests.pages.MainPage;
import com.bspbtests.pages.PrivateBankingPage;
import com.bspbtests.tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class PrivateBankingTest extends BaseTest {

    @Test
    public void privateBankingTest(){
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.isDisplayed());

        mainPage.clickTopMenuItemByText("Private Banking");
        PrivateBankingPage privateBankingPage = new PrivateBankingPage();
        Assert.assertTrue(privateBankingPage.isDisplayed());
    }
}
