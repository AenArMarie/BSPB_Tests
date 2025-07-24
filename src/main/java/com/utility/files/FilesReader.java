package com.utility.files;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utility.logger.ProjectLogger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesReader {

    public static <T> T readJson(String filePath, Class<T> targetClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(filePath), targetClass);
        } catch (IOException e) {
            ProjectLogger.error("Ошибка чтения JSON " + e.getMessage());
            return null;
        }
    }

    public static String readFileAsString(String localPath) {
        String returnedString;
        try {
            returnedString = new String(Files.readAllBytes(Paths.get(localPath)));
        } catch (IOException e) {
            ProjectLogger.error("Ошибка чтения файла " + e.getMessage());
            return null;
        }
        return returnedString;
    }
}
