package com.bspbtests.utility;

import com.bspbtests.constants.FileTypes;
import com.utility.logger.ProjectLogger;
import io.qameta.allure.Allure;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AllureUtilities {

    public static void attachJson(String name, String filePath) {
        try {
            Allure.addAttachment(name, FileTypes.APP_JSON, new FileInputStream(filePath), FileTypes.JSON);
        } catch (FileNotFoundException e) {
            ProjectLogger.error("Не удалось прикрепить файл");
        }
    }

    public static void attachJson(String name, Response response) {
        Allure.addAttachment(name, FileTypes.APP_JSON, response.getBody().asPrettyString(), FileTypes.JSON);
    }
}
