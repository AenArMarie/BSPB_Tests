package com.bspbtests.data.offices;

import com.bspbtests.data.RatesModel;

import java.util.ArrayList;

/**
 * Запись офисов обмена валюты
 */
public record ExchangeOfficeModel(String address,
                                  Integer id,
                                  String name,
                                  ArrayList<RatesModel> rates) {
}
