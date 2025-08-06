package com.bspbtests.utility;

import com.bspbtests.constants.FileTypes;
import io.qameta.allure.Allure;
import io.restassured.response.Response;

public class AllureUtilities {

    public static void attachJson(String name, Response response) {
        Allure.addAttachment(name, FileTypes.APP_JSON, response.getBody().asPrettyString(), FileTypes.JSON);
    }
}
