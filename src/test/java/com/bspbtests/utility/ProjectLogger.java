package com.bspbtests.utility;

import io.qameta.allure.Allure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Логгер
 */
public class ProjectLogger {

    private static final Logger logger = LoggerFactory.getLogger(ProjectLogger.class);

    /**
     * Метод, составляющий {@code INFO} лог и прикрепляющий его в Allure
     * @param text текст лога
     */
    public static void info(String text) {
        logger.info(text);
        Allure.addAttachment("INFO log", text);
    }

    /**
     * Метод, составляющий {@code ERROR} лог и прикрепляющий его в Allure
     * @param text текст лога
     */
    public static void error(String text) {
        logger.error(text);
        Allure.addAttachment("ERROR log", text);
    }
}
