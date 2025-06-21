package com.bspbtests.tests.basetest;

import com.bspbtests.constants.PathConstants;
import com.bspbtests.jsondata.ConfigData;
import com.bspbtests.jsondata.TestData;
import com.utility.driver.BrowserModel;
import com.utility.driver.Driver;
import com.utility.driver.DriverMethods;
import com.utility.files.FilesReader;
import com.utility.logger.ProjectLogger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public abstract class BaseTest {

    protected TestData testData;

    @BeforeClass
    public static void setUp() {
        BrowserModel browserModel = FilesReader.readJson(PathConstants.BROWSER_CONFIG_PATH, BrowserModel.class);
        Driver.initialize(browserModel);
    }

    @Before
    public void additionalSetUp() {
        testData = FilesReader.readJson(PathConstants.TEST_DATA_PATH, TestData.class);
        ConfigData configData = FilesReader.readJson(PathConstants.CONFIG_DATA_PATH, ConfigData.class);
        ProjectLogger.info("Запуск теста");
        ProjectLogger.info("Открытие главной страницы");
        Driver.instance().manage().window().maximize();
        Driver.instance().get(configData.getBaseUrl());
        DriverMethods.initializeWait(configData.getDriverWaitTime());
    }

    @Rule
    public TestWatcher watcher = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            DriverMethods.makeScreenshot(description.getMethodName(), PathConstants.SCREENSHOT_PATH, PathConstants.SCREENSHOT_TYPE);
        }

        @Override
        protected void finished(Description description) {
            ProjectLogger.info("Завершение теста\n");
            Driver.quit();
        }
    };
}
