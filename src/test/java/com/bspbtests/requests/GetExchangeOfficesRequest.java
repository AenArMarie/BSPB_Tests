package com.bspbtests.requests;

import com.bspbtests.utility.ApiUtilities;
import io.restassured.response.Response;

/**
 * Запрос на получение офисов обмена валют
 */
public class GetExchangeOfficesRequest {

    private static final String URL = "https://www.bspb.ru/api/currency-service/office-rates";

    /**
     * Получение ответа на запрос
     *
     * @return ответ на запрос в формате {@link Response}
     */
    public static Response performGet() {
        return ApiUtilities.getRequest(URL);
    }
}
