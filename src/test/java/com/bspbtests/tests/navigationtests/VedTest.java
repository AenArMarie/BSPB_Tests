package com.bspbtests.tests.navigationtests;

import com.bspbtests.pages.BusinessPage;
import com.bspbtests.pages.MainPage;
import com.bspbtests.pages.VedPage;
import com.bspbtests.tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class VedTest extends BaseTest {

    @Test
    public void vedTest(){
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.isDisplayed());

        mainPage.clickTopMenuItemByText("ВЭД");
        VedPage vedPage = new VedPage();
        Assert.assertTrue(vedPage.isDisplayed());
    }
}
