package com.bspbtests.utility;

import io.qameta.allure.Allure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectLogger {

    private static final Logger logger = LoggerFactory.getLogger(ProjectLogger.class);

    public static void info(String text) {
        logger.info(text);
        Allure.addAttachment("INFO log", text);
    }

    public static void debug(String text) {
        logger.debug(text);
        Allure.addAttachment("DEBUG log", text);
    }

    public static void error(String text) {
        logger.error(text);
        Allure.addAttachment("ERROR log", text);
    }
}
