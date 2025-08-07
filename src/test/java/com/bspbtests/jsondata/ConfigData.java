package com.bspbtests.jsondata;

import lombok.Getter;
import lombok.Setter;

/**
 * Данные конфигурации драйвера
 * <p>
 * Данные могут загружаться из JSON-файла и содержат параметры,
 * необходимые для настройки окружения и поведения WebDriver'а.
 * </p>
 */
@Getter
@Setter
public class ConfigData {

    /**
     * Url, открываемый при запуске
     */
    private String baseUrl;
    /**
     * Стандартный таймаут для явных ожиданий
     */
    private int driverWaitTime;
}
