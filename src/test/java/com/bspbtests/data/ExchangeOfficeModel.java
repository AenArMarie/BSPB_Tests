package com.bspbtests.data;

import java.util.ArrayList;

/**
 * Запись офисов обмена валюты
 */
public record ExchangeOfficeModel(String address,
                                  Integer id,
                                  String name,
                                  ArrayList<RatesModel> rates) {
}
