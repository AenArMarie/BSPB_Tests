package com.bspbtests.pages.baseform;

/**
 * Интерфейс, описывающий общее поведения шаблонных страниц
 */
public interface ITemplatedForm {

    /**
     * Метод возвращает статус отображения страницы
     *
     * @param locatorPart фрагмент локатора
     * @param name название страницы
     * @return {@code true} при успешной проверке, {@code false} при провале
     */
    static boolean isDisplayed(String locatorPart, String name) {
        return false;
    }
}
