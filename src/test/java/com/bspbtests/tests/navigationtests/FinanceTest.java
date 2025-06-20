package com.bspbtests.tests.navigationtests;

import com.bspbtests.pages.FinancePage;
import com.bspbtests.pages.MainPage;
import com.bspbtests.pages.VedPage;
import com.bspbtests.tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class FinanceTest extends BaseTest {

    @Test
    public void financeTest(){
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.isDisplayed());

        mainPage.clickTopMenuItemByText("Финансовые рынки");
        FinancePage financePage = new FinancePage();
        Assert.assertTrue(financePage.isDisplayed());
    }
}
