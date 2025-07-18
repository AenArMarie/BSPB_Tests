package com.bspbtests.jsondata.testdata;

import com.bspbtests.data.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestData {

    private CurrencyExchangeData currencyExchangeData;
    private InvestmentRateData investmentRateData;
    private CardOrderingData cardOrderingData;
    private User userData;
}
