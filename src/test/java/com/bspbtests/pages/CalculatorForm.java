package com.bspbtests.pages;

import com.utility.driver.Driver;
import com.utility.driver.DriverMethods;
import com.utility.webelements.Element;
import com.utility.webelements.Input;
import org.openqa.selenium.By;

public class CalculatorForm extends BaseForm{

    Input investmentSumInput = new Input(By.className("css-vfqw27"), "Поле ввода суммы вклада");
    Element investFor31DaysButton = new Element(By.xpath("//*[contains(@class, 'css-gi9t6w') and contains(., '31 день')]/ancestor::li"), "Кнопка '31 день'");
    Element interestAmount = new Element(By.xpath("//*[contains(@class, 'css-1linwur') and contains(., '₽')]"), "Расчетная выгода от вклада");
    Element investmentRate = new Element(By.xpath("//*[contains(@class, 'css-1linwur') and contains(., '%')]"), "Ставка вклада");

    public CalculatorForm() {
        super(By.className("css-te1w57"), "Калькулятор");
    }

    public void setInvestmentSum(String amount) {
        investmentSumInput.setInput(amount);
    }

    public void click31Days() {
        investFor31DaysButton.click();
    }

    public String getInvestmentRate(){
        return investmentRate.getText();
    }

    public String getInterestAmount(){
        return interestAmount.getText();
    }
}
