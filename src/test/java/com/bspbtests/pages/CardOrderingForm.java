package com.bspbtests.pages;

import com.bspbtests.constants.AttributeConstants;
import com.bspbtests.pages.baseform.BaseForm;
import com.bspbtests.utility.webelements.Element;
import com.bspbtests.utility.webelements.Input;
import org.openqa.selenium.By;

public class CardOrderingForm extends BaseForm {

    private Input firstNameInput = new Input(By.id("firstName"), "Поле ввода имени");
    private Input lastNameInput = new Input(By.id("lastName"), "Поле ввода фамилии");
    private Input phoneNumberInput = new Input(By.xpath("//*[contains(@name, 'phoneNumber')]"), "Поле ввода номера телефона");
    private Input emailInput = new Input(By.id("emailAddress"), "Поле ввода почты");
    private Input adressInput = new Input(By.id("deliveryAddress"), "Поле ввода адреса");

    private Element continueButton = new Element(By.className("css-19eb57h"), "Кнопка продолжения оформления");

    public CardOrderingForm() {
        super(By.className("css-qu1ryg"), "Форма оформления заказа карты");
    }

    public void inputPhoneNumber(String text) {
        phoneNumberInput.setInput(text);
    }

    public void inputEmail(String text) {
        emailInput.setInput(text);
    }

    public String getFirstNameInvalidity() {
        return firstNameInput.getAttribute(AttributeConstants.ARIA_INVALID);
    }

    public String getLastNameInvalidity() {
        return lastNameInput.getAttribute(AttributeConstants.ARIA_INVALID);
    }

    public String getAdressInvalidity() {
        return adressInput.getAttribute(AttributeConstants.ARIA_INVALID);
    }

    public void clickContinueButton() {
        continueButton.click();
    }
}
