package com.bspbtests.jsondata.testdata;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvestmentRateData {

    private String investedAmount;
    private String investmentPeriodText;
    private int expectedInvestmentRate;
    private String expectedInterestValue;
}
