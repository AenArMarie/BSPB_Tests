package com.bspbtests.pages.baseform;

/**
 * Интерфейс, описывающий общее поведение страниц
 */
public interface IBaseForm {

    /**
     * Метод возвращает статус отображения страницы
     * @return {@code true} при успешной проверке отображения,
     * {@code false} при неуспешной
     */
    static boolean isDisplayed() {
        return false;
    }
}
