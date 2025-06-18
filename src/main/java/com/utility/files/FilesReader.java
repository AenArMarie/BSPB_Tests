package com.utility.files;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utility.logger.ProjectLogger;

import java.io.File;
import java.io.IOException;

public class FilesReader {

    public static <T> T readJson(String filePath, Class<T> targetClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(filePath), targetClass);
        } catch (IOException e) {
            ProjectLogger.error("Ошибка чтения JSON");
            return null;
        }
    }
}
