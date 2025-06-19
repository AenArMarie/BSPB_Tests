package com.bspbtests.tests;

import com.bspbtests.constants.PathConstants;
import com.utility.driver.BrowserModel;
import com.utility.driver.Driver;
import com.utility.driver.DriverMethods;
import com.utility.files.FilesReader;
import com.utility.logger.ProjectLogger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public abstract class BaseTest {

    @BeforeClass
    public static void setUp() {
        BrowserModel browserModel = FilesReader.readJson(PathConstants.BROWSER_CONFIG_PATH, BrowserModel.class);
        Driver.initialize(browserModel);
    }

    @Before
    public void additionalSetUp() {
        ProjectLogger.info("Запуск теста");
        ProjectLogger.info("Открытие главной страницы");
        Driver.instance().get("https://www.bspb.ru"); //TODO хардкод
        Driver.instance().manage().window().maximize();
        DriverMethods.initializeWait(5000); //TODO хардкод
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
            //Driver.quit();
        }
    };
}
