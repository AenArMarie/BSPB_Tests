package com.bspbtests.pages;

import com.bspbtests.constants.AttributeConstants;
import com.bspbtests.pages.baseform.BaseForm;
import com.bspbtests.webelements.Element;
import com.bspbtests.webelements.Input;
import org.openqa.selenium.By;

public class CardOrderingForm extends BaseForm {

    private static final Input firstNameInput = new Input(By.id("firstName"), "Поле ввода имени");
    private static final Input lastNameInput = new Input(By.id("lastName"), "Поле ввода фамилии");
    private static final Input phoneNumberInput = new Input(By.xpath("//*[contains(@name, 'phoneNumber')]"), "Поле ввода номера телефона");
    private static final Input emailInput = new Input(By.id("emailAddress"), "Поле ввода почты");
    private static final Input addressInput = new Input(By.id("deliveryAddress"), "Поле ввода адреса");

    private static final Element continueButton = new Element(By.className("css-19eb57h"), "Кнопка продолжения оформления");

    public static boolean isDisplayed() {
        return new Element(By.className("css-qu1ryg"), "Форма оформления заказа карты").getElement().isDisplayed();
    }

    public static void inputPhoneNumber(String text) {
        phoneNumberInput.setInput(text);
    }

    public static void inputEmail(String text) {
        emailInput.setInput(text);
    }

    public static String getFirstNameInvalidity() {
        return firstNameInput.getAttribute(AttributeConstants.ARIA_INVALID);
    }

    public static String getLastNameInvalidity() {
        return lastNameInput.getAttribute(AttributeConstants.ARIA_INVALID);
    }

    public static String getAddressInvalidity() {
        return addressInput.getAttribute(AttributeConstants.ARIA_INVALID);
    }

    public static void clickContinueButton() {
        continueButton.click();
    }
}
