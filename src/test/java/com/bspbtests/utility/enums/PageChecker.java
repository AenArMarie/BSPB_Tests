package com.bspbtests.utility.enums;

import com.bspbtests.constants.ElementsTextConstants;
import com.bspbtests.constants.MainPageMenuItemText;
import com.bspbtests.pages.MainMenuPage;
import com.bspbtests.pages.PrivateBankingPage;

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

    public abstract boolean isDisplayed(String pageName);

    public static PageChecker fromPageName(String pageName) {
        if (MainPageMenuItemText.PRIVATE_BANKING.equals(pageName)) {
            return PRIVATE_BANKING;
        }
        return DEFAULT;
    }
}
