package com.bspbtests.tests.navigationtests;

import com.bspbtests.pages.FinancePage;
import com.bspbtests.pages.InvestorPage;
import com.bspbtests.pages.MainPage;
import com.bspbtests.tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class InvestorTest extends BaseTest {

    @Test
    public void investorTest(){
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.isDisplayed());

        mainPage.clickTopMenuItemByText("Инвесторам");
        InvestorPage investorPage = new InvestorPage();
        Assert.assertTrue(investorPage.isDisplayed());
    }
}
