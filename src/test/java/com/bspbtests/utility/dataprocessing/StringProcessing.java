package com.bspbtests.utility.dataprocessing;

/**
 * Обработчик строк
 */
public class StringProcessing {

    /**
     * Метод, разделяющий {@link String} на несколько частей и возвращающий значение по индексу {@code partIndex}
     *
     * @param string разделяемая строка
     * @param separator последовательность символов, по которой разбивается строка
     * @param partIndex индекс возвращаемого фрагмента строки
     * @return фрагмент строки {@link String}
     */
    public static String splitStringByTextAndGetPart(String string, String separator, int partIndex) {
        String[] parts = string.split(separator);
        if (parts.length > partIndex) {
            return parts[partIndex];
        } else return "";
    }
}
