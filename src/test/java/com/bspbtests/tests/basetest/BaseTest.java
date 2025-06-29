package com.bspbtests.tests.basetest;

import com.bspbtests.constants.PathConstants;
import com.bspbtests.jsondata.ConfigData;
import com.bspbtests.jsondata.testdata.TestData;
import com.bspbtests.utility.driver.Driver;
import com.bspbtests.utility.driver.DriverMethods;
import com.utility.files.FilesReader;
import com.utility.logger.ProjectLogger;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public abstract class BaseTest {

    protected TestData testData;

    @Before
    public void setUp() {
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
