package com.bspbtests.data;

/**
 * Запись курсов обмена
 */
public record RatesModel(Double buyRate,
                         Double cbRate,
                         String currencyCode,
                         String currencyCodeSecond,
                         Integer lotSize,
                         Double sellRate,
                         Integer transactionVolume) {
}
