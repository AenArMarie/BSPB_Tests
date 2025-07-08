package com.bspbtests.tests.basetest;

import com.bspbtests.constants.PathConstants;
import com.bspbtests.jsondata.ConfigData;
import com.bspbtests.jsondata.testdata.TestData;
import com.bspbtests.utility.driver.Driver;
import com.bspbtests.utility.driver.DriverMethods;
import com.utility.files.FilesReader;
import com.utility.logger.ProjectLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.*;

public abstract class BaseTest {

    protected TestData testData;

    @BeforeEach
    public void setUp() {
        testData = FilesReader.readJson(PathConstants.TEST_DATA_PATH, TestData.class);
        ConfigData configData = FilesReader.readJson(PathConstants.CONFIG_DATA_PATH, ConfigData.class);
        ProjectLogger.info("Запуск теста");
        ProjectLogger.info("Открытие главной страницы");
        Driver.instance().manage().window().maximize();
        Driver.instance().get(configData.getBaseUrl());
        DriverMethods.initializeWait(configData.getDriverWaitTime());
    }

    @RegisterExtension
    TestResultWatcher watcher = new TestResultWatcher();

    static class TestResultWatcher implements TestWatcher, AfterTestExecutionCallback {

        @Override
        public void testFailed(ExtensionContext context, Throwable cause) {
            String methodName = context.getRequiredTestMethod().getName();
            DriverMethods.makeScreenshot(methodName, PathConstants.SCREENSHOT_PATH, PathConstants.SCREENSHOT_TYPE);
        }

        @Override
        public void afterTestExecution(ExtensionContext context) {
            ProjectLogger.info("Завершение теста\n");
            Driver.quit();
        }
    }
}
