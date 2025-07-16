package com.bspbtests.steps;

import com.bspbtests.constants.CommonLogMessages;
import com.bspbtests.constants.FileTypes;
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
import io.qameta.allure.Allure;
import io.qameta.allure.Step;

import java.io.ByteArrayInputStream;

public class Hooks {

    public static TestData testData;
    public static ConfigData configData;

    @Before
    @Step("Запуск теста")
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
    @Step("Завершение теста")
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            Allure.addAttachment(scenario.getName(),
                    FileTypes.IMAGE_PNG,
                    new ByteArrayInputStream(DriverMethods.makeScreenshotByteArray()),
                    FileTypes.PNG_TYPE);
            Allure.addAttachment(CommonLogMessages.PAGE_SOURCE_NAME,
                    FileTypes.TEXT_HTML,
                    DriverMethods.getPageSource(),
                    FileTypes.HTML_TYPE);
            Allure.addAttachment(CommonLogMessages.SELENOID_VIDEO_LINK,
                    FileTypes.TEXT,
                    DriverMethods.getSelenoidVideoLink());
        }

        ProjectLogger.info("Завершение теста\n");
        Driver.quit();
    }
}
