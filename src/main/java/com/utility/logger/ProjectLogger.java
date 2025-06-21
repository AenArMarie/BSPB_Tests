package com.utility.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectLogger {

    private static final Logger logger = LoggerFactory.getLogger(ProjectLogger.class);

    public static void info(String text) {
        logger.info(text);
    }

    public static void debug(String text) {
        logger.debug(text);
    }

    public static void error(String text) {
        logger.error(text);
    }
}
