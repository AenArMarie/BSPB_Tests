package com.bspbtests.data;

import java.util.ArrayList;

/**
 * Запись ответа на Api-запрос офисов обмена валют
 */
public record OfficeDataModel(ArrayList<ExchangeOfficeModel> items) {
}
