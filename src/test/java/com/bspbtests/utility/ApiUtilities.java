package com.bspbtests.utility;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Утилиты для работы с REST API
 */
public class ApiUtilities {

    /**
     * Метод для выполнения GET-запроса без header-ов и query-параметров
     * @param url URL запроса
     * @return ответ на запрос в формате {@link Response}
     */
    public static Response getRequest(String url) {
        return given()
                .when()
                .get(url)
                .then()
                .extract().response();
    }

    /**
     * Метод для преобразования ответа на запрос в выбранный класс
     * @param response ответ на запрос
     * @param targetClass класс, в который преобразуется ответ на запрос
     * @return ответ на запрос, преобразованный в объект типа {@code T}
     * @param <T> тип объекта, в который преобразуется ответ на запрос
     */
    public static <T> T parseResponseAs(Response response, Class<T> targetClass) {
        return response.as(targetClass);
    }
}
