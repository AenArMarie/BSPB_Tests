package com.bspbtests.pages;

import com.bspbtests.constants.ElementsTextConstants;
import com.bspbtests.pages.baseform.BaseForm;
import com.bspbtests.utility.webelements.Element;
import org.openqa.selenium.By;

public class MainPage extends BaseForm {

    private Element investmentsMenu = new Element(By.xpath(String.format("//*[contains(@class, 'css-1y4qndl') and contains(., '%s')]", ElementsTextConstants.MAIN_PAGE_INVEST)), "Выпадающее меню 'Вклады'");
    private Element cardsMenu = new Element(By.xpath(String.format("//*[contains(@class, 'css-1y4qndl') and contains(., '%s')]", ElementsTextConstants.CARDS)), "Выпадающее меню 'Карты'");
    private Element whiteNightsInvestment = new Element(By.xpath(String.format("//a[contains(@class, 'css-70yt0k') and contains(., '%s')]", ElementsTextConstants.WHITE_NIGHTS)), "Ссылка на вклад 'Белые ночи'");
    private Element debitCards = new Element(By.xpath(String.format("//a[contains(@class, 'css-70yt0k') and contains(., '%s')]", ElementsTextConstants.DEBIT_CARDS)), "Ссылка на страницу дебетовых карт");
    private Element buyCurrencyButton = new Element(By.xpath("//*[contains(@class, 'css-oen4ev')]"), "Кнопка 'Купить валюту'");

    private String topMenuLocatorTemplate = "//*[contains(@class, 'css-9f19sy') and contains(., '%s')]";

    public MainPage() {
        super(By.xpath(String.format("//*[contains(@class, 'css-2wq498') and contains(., '%s')]", ElementsTextConstants.MAIN_PAGE_MORTGAGE)), "Главная страница");
    }

    public void hoverInvestments() {
        investmentsMenu.hover();
    }

    public void hoverCards() {
        cardsMenu.hover();
    }

    public void clickWhiteNights() {
        whiteNightsInvestment.click();
    }

    public void clickDebitCards() {
        debitCards.click();
    }

    public void clickBuyCurrencyButton() {
        buyCurrencyButton.click();
    }

    public void clickTopMenuItemByText(String text) {
        Element menuItem = new Element(By.xpath(String.format(topMenuLocatorTemplate, text)), "Кнопка с текстом " + text);
        menuItem.click();
    }
}
