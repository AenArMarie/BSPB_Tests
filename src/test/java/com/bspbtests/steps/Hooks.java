package com.bspbtests.steps;

import com.bspbtests.constants.PathConstants;
import com.bspbtests.jsondata.ConfigData;
import com.bspbtests.jsondata.testdata.TestData;
import com.bspbtests.utility.driver.Driver;
import com.bspbtests.utility.driver.DriverMethods;
import com.utility.files.FilesReader;
import com.utility.logger.ProjectLogger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    public static TestData testData;
    public static ConfigData configData;

    @Before
    public void setUp() {
        testData = FilesReader.readJson(PathConstants.TEST_DATA_PATH, TestData.class);
        configData = FilesReader.readJson(PathConstants.CONFIG_DATA_PATH, ConfigData.class);

        ProjectLogger.info("Запуск теста");
        ProjectLogger.info("Открытие главной страницы");

        Driver.instance().manage().window().maximize();
        Driver.instance().get(configData.getBaseUrl());
        DriverMethods.initializeWait(configData.getDriverWaitTime());
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            DriverMethods.makeScreenshot(
                    scenario.getName(),
                    PathConstants.SCREENSHOT_PATH,
                    PathConstants.SCREENSHOT_TYPE
            );
        }

        ProjectLogger.info("Завершение теста\n");
        Driver.quit();
    }
}
