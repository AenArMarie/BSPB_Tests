package com.bspbtests.pages;

import com.bspbtests.constants.AttributeConstants;
import com.bspbtests.pages.baseform.IBaseForm;
import com.bspbtests.webelements.Element;
import com.bspbtests.webelements.Input;
import org.openqa.selenium.By;

/**
 * Страница заказа карты
 */
public class CardOrderingForm implements IBaseForm {

    private static final Input firstNameInput = new Input(By.id("firstName"), "Поле ввода имени");
    private static final Input lastNameInput = new Input(By.id("lastName"), "Поле ввода фамилии");
    private static final Input phoneNumberInput = new Input(By.xpath("//*[contains(@name, 'phoneNumber')]"), "Поле ввода номера телефона");
    private static final Input emailInput = new Input(By.id("emailAddress"), "Поле ввода почты");
    private static final Input addressInput = new Input(By.id("deliveryAddress"), "Поле ввода адреса");

    private static final Element continueButton = new Element(By.className("css-19eb57h"), "Кнопка продолжения оформления");

    /**
     * @see IBaseForm#isDisplayed()
     */
    public static boolean isDisplayed() {
        return new Element(By.className("css-qu1ryg"), "Форма оформления заказа карты").getElement().isDisplayed();
    }

    /**
     * Метод ввода номера телефона
     *
     * @param text номер телефона
     */
    public static void inputPhoneNumber(String text) {
        phoneNumberInput.setInput(text);
    }

    /**
     * Метод ввода почты
     *
     * @param text почта
     */
    public static void inputEmail(String text) {
        emailInput.setInput(text);
    }

    /**
     * Проверка наличия атрибута не валидности поля имени
     *
     * @return {@code true} если атрибут не валидности присутствует, {@code false} в обратном случае
     */
    public static String getFirstNameInvalidity() {
        return firstNameInput.getAttribute(AttributeConstants.ARIA_INVALID); //TODO енамка для полей
    }

    public static String getLastNameInvalidity() {
        return lastNameInput.getAttribute(AttributeConstants.ARIA_INVALID);
    }

    public static String getAddressInvalidity() {
        return addressInput.getAttribute(AttributeConstants.ARIA_INVALID);
    }

    /**
     * Нажатие кнопки "Продолжить"
     */
    public static void clickContinueButton() {
        continueButton.click();
    }
}
