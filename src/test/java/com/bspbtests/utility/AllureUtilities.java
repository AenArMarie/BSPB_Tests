package com.bspbtests.utility;

import com.bspbtests.constants.FileTypes;
import io.qameta.allure.Allure;
import io.restassured.response.Response;

/**
 * Утилиты для работы с Allure
 */
public class AllureUtilities {

    /**
     * Функция приложения ответа на запрос в виде Json к Allure отчету
     * @param name название приложения
     * @param response ответ на запрос типа {@link Response}
     */
    public static void attachJson(String name, Response response) {
        Allure.addAttachment(name, FileTypes.APP_JSON, response.getBody().asPrettyString(), FileTypes.JSON);
    }
}
