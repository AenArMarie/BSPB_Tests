package com.bspbtests.pages;

import com.bspbtests.constants.CommonLocatorTemplates;
import com.bspbtests.constants.ElementsTextConstants;
import com.bspbtests.constants.StringConstants;
import com.bspbtests.pages.baseform.IBaseForm;
import com.bspbtests.utility.driver.DriverMethods;
import com.bspbtests.webelements.Element;
import com.bspbtests.webelements.Input;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public class CalculatorFormI implements IBaseForm {

    private static final Input investmentSumInput = new Input(By.className("css-vfqw27"), "Поле ввода суммы вклада");
    private static final Element interestAmount = new Element(By.xpath(String.format(CommonLocatorTemplates.CALCULATOR_LABEL, ElementsTextConstants.RUB_CURRENCY_TEXT)), "Расчетная выгода от вклада");
    private static final Element investmentRate = new Element(By.xpath(String.format(CommonLocatorTemplates.CALCULATOR_LABEL, ElementsTextConstants.PERCENTAGE_TEXT)), "Ставка вклада");

    private static final String investmentPeriodXpathTemplate = "//*[contains(@class, 'css-gi9t6w') and contains(., '%s')]/ancestor::li";


    public static boolean isDisplayed() {
        return new Element(By.className("css-te1w57"), "Калькулятор").getElement().isDisplayed();
    }

    public static void setInvestmentSum(String amount) {
        investmentSumInput.setInput(amount);
    }

    public static void clickInvestmentPeriodByText(String text) {
        Element investmentPeriodButton = new Element(By.xpath(String.format(investmentPeriodXpathTemplate, text)), "Кнопка выбора периода вложения с текстом " + text);
        investmentPeriodButton.click();
    }

    public static String getInvestmentRate() {
        return investmentRate.getText();
    }

    public static boolean checkIfNormalizedInterestAmountEqualToText(String text) {
        try {
            DriverMethods.getWait().until(driver -> {
                String normalizedAmount = interestAmount.getText().replaceAll(StringConstants.ALL_NON_NUMERIC_CHARS, StringConstants.EMPTY_STRING);
                return normalizedAmount.equals(text);
            });
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
