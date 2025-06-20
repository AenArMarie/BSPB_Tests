package com.bspbtests.tests.navigationtests;

import com.bspbtests.pages.BusinessPage;
import com.bspbtests.pages.MainPage;
import com.bspbtests.tests.BaseTest;
import com.utility.constants.MainPaths;
import org.junit.Assert;
import org.junit.Test;

public class BusinessTest extends BaseTest {

    @Test
    public void businessTest(){
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.isDisplayed());

        mainPage.clickTopMenuItemByText("Бизнесу");
        BusinessPage businessPage = new BusinessPage();
        Assert.assertTrue(businessPage.isDisplayed());
    }
}
