package com.bspbtests.jsondata.testdata;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyExchangeData {

    private String existingCurrencyText;
    private String convertedCurrencyText;
    private String minimalConversionRateCurrency;
    private String thresholdForDiffConversionRateText;
    private int amountBelowThreshold;
    private int amountAboveThreshold;
    private double marginOfError;
}
