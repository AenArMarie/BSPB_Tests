package com.bspbtests.pages;

import com.bspbtests.constants.AttributeConstants;
import com.bspbtests.pages.baseform.BaseForm;
import com.bspbtests.webelements.Element;
import com.bspbtests.webelements.Input;
import org.openqa.selenium.By;

public class CardOrderingForm extends BaseForm {

    private final Input firstNameInput = new Input(By.id("firstName"), "Поле ввода имени");
    private final Input lastNameInput = new Input(By.id("lastName"), "Поле ввода фамилии");
    private final Input phoneNumberInput = new Input(By.xpath("//*[contains(@name, 'phoneNumber')]"), "Поле ввода номера телефона");
    private final Input emailInput = new Input(By.id("emailAddress"), "Поле ввода почты");
    private final Input addressInput = new Input(By.id("deliveryAddress"), "Поле ввода адреса");

    private final Element continueButton = new Element(By.className("css-19eb57h"), "Кнопка продолжения оформления");

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

    public String getAddressInvalidity() {
        return addressInput.getAttribute(AttributeConstants.ARIA_INVALID);
    }

    public void clickContinueButton() {
        continueButton.click();
    }
}
