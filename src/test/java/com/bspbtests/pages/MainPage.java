package com.bspbtests.pages;

import com.bspbtests.constants.ElementsTextConstants;
import com.bspbtests.pages.baseform.BaseForm;
import com.bspbtests.webelements.Element;
import org.openqa.selenium.By;

public class MainPage extends BaseForm {

    private static final Element investmentsMenu = new Element(By.xpath(String.format("//*[contains(@class, 'css-1y4qndl') and contains(., '%s')]", ElementsTextConstants.MAIN_PAGE_INVEST)), "Выпадающее меню 'Вклады'");
    private static final Element cardsMenu = new Element(By.xpath(String.format("//*[contains(@class, 'css-1y4qndl') and contains(., '%s')]", ElementsTextConstants.CARDS)), "Выпадающее меню 'Карты'");
    private static final Element whiteNightsInvestment = new Element(By.xpath(String.format("//a[contains(@class, 'css-70yt0k') and contains(., '%s')]", ElementsTextConstants.WHITE_NIGHTS)), "Ссылка на вклад 'Белые ночи'");
    private static final Element debitCards = new Element(By.xpath(String.format("//a[contains(@class, 'css-70yt0k') and contains(., '%s')]", ElementsTextConstants.DEBIT_CARDS)), "Ссылка на страницу дебетовых карт");
    private static final Element buyCurrencyButton = new Element(By.xpath("//*[contains(@class, 'css-oen4ev')]"), "Кнопка 'Купить валюту'");

    private static final String topMenuLocatorTemplate = "//*[contains(@class, 'css-9f19sy') and contains(., '%s')]";

    public static boolean isDisplayed() {
        return new Element(By.xpath(String.format("//*[contains(@class, 'css-2wq498') and contains(., '%s')]", ElementsTextConstants.MAIN_PAGE_MORTGAGE)), "Главная страница").getElement().isDisplayed();
    }

    public static void hoverInvestments() {
        investmentsMenu.hover();
    }

    public static void hoverCards() {
        cardsMenu.hover();
    }

    public static void clickWhiteNights() {
        whiteNightsInvestment.click();
    }

    public static void clickDebitCards() {
        debitCards.click();
    }

    public static void clickBuyCurrencyButton() {
        buyCurrencyButton.click();
    }

    public static void clickTopMenuItemByText(String text) {
        Element menuItem = new Element(By.xpath(String.format(topMenuLocatorTemplate, text)), "Кнопка с текстом " + text);
        menuItem.click();
    }
}
