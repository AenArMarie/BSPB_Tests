package com.bspbtests.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ElementsTextConstants {

    public static final String ICOUNT_CARD_TEXT = "ЯСЧИТАЮ";

    public static final String MAIN_PAGE_INVEST = "Вклады";
    public static final String WHITE_NIGHTS = "Белые ночи";
    public static final String CARDS = "Карты";
    public static final String DEBIT_CARDS = "Дебетовые карты";
    public static final String MAIN_PAGE_MORTGAGE = "Ипотека";

    public static final String PERCENTAGE_TEXT = "%";
    public static final String RUB_CURRENCY_TEXT = "₽";

    public static final Map<String, String> PAGES_UNIQUE_ELEMENT_TEXT;

    static {
        Map<String, String> map = new HashMap<>();
        map.put("Бизнесу", "Зарплатный");
        map.put("Финансовые рынки", "Финансовым");
        map.put("Инвесторам", "акционеров");
        map.put("ВЭД", "ВЭД360");
        PAGES_UNIQUE_ELEMENT_TEXT = Collections.unmodifiableMap(map);
    }
}
