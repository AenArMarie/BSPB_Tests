package com.bspbtests.utility.dataprocessing;

import com.bspbtests.utility.ProjectLogger;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Класс для чтения файлов
 */
public class FilesReader {


    /**
     * Метод, преобразующий Json в объект класса {@code T}
     *
     * @param filePath путь к файлу из проектной директории
     * @param targetClass класс типа {@code T}
     * @param <T> тип объекта, в который переводится Json
     * @return объект типа {@code T}, полученный в результате перевода из Json
     */
    public static <T> T readJson(String filePath, Class<T> targetClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(filePath), targetClass);
        } catch (IOException e) {
            ProjectLogger.error("Ошибка чтения JSON " + e.getMessage());
            return null;
        }
    }
}
