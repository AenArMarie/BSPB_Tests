package com.bspbtests.data;

import java.util.ArrayList;

public record ExchangeOfficeModel(String address,
                                  Integer id,
                                  String name,
                                  ArrayList<RatesModel> rates) {
}
