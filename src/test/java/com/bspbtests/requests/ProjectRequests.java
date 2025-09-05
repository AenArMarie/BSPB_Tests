package com.bspbtests.requests;

import com.bspbtests.utility.ApiUtilities;
import io.restassured.response.Response;

import java.util.Map;

/**
 * Запрос на получение офисов обмена валют
 */
public class ProjectRequests {

    private static final String BASE_URL = "https://www.bspb.ru";

    private enum Endpoint {
        OFFICE_RATES("/api/currency-service/office-rates"),
        CARD_RATES("/api/currency-service/card-rates");

        public final String path;

        Endpoint(String path) {
            this.path = path;
        }
    }

    /**
     * Получение ответа на запрос на получение офисов обмена валют
     *
     * @return ответ на запрос в формате {@link Response}
     */
    public static Response getOfficesExchangeRates() {
        return ApiUtilities.getRequest(BASE_URL + Endpoint.OFFICE_RATES.path);
    }

    /**
     * Получение ответа на запрос на получение обмена валют определенной карты
     *
     * @return ответ на запрос в формате {@link Response}
     */
    public static Response getExchangeRateByCard(String cardName) {
        Map<String, String> query = Map.of("cardType", cardName);
        return ApiUtilities.getRequestWithQuery(BASE_URL + Endpoint.CARD_RATES.path, query);
    }
}
