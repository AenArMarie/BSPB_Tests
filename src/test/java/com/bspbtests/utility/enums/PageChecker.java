package com.bspbtests.utility.enums;

import com.bspbtests.constants.ElementsTextConstants;
import com.bspbtests.constants.MainPageMenuItemText;
import com.bspbtests.pages.MainMenuPage;
import com.bspbtests.pages.PrivateBankingPage;
import com.bspbtests.pages.baseform.IBaseForm;

/**
 * Enum для вывода метода {@code isDisplayed} разных страниц
 * @see IBaseForm#isDisplayed()
 * @see com.bspbtests.pages.baseform.ITemplatedForm#isDisplayed(String, String)
 */
public enum PageChecker {
    PRIVATE_BANKING {
        @Override
        public boolean isDisplayed(String pageName) {
            return PrivateBankingPage.isDisplayed();
        }
    },

    DEFAULT {
        @Override
        public boolean isDisplayed(String pageName) {
            return MainMenuPage.isDisplayed(
                    ElementsTextConstants.PAGES_UNIQUE_ELEMENT_TEXT.get(pageName), pageName
            );
        }
    };

    /**
     * Метод, выполняющий {@link IBaseForm#isDisplayed()} или {@link com.bspbtests.pages.baseform.ITemplatedForm#isDisplayed(String, String)}
     * выбранного браузера
     * @param pageName название страницы для логов
     * @return {@code true} если страница показана,
     * {@code false} в обратном случае
     */
    public abstract boolean isDisplayed(String pageName);

    /**
     * Метод, ищущий страницу по имени
     * @param pageName имя страницы
     * @return объект enum
     */
    public static PageChecker fromPageName(String pageName) {
        if (MainPageMenuItemText.PRIVATE_BANKING.equals(pageName)) {
            return PRIVATE_BANKING;
        }
        return DEFAULT;
    }
}
