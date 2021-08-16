package org.sparrow.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.sparrow.framework.util.FormatString;

public class BuyingCompleteHousePage extends BasePage {

    @FindBy(xpath = "//div[contains(text(), 'Стоимость недвижимости')]/../input")
    private WebElement realEstatePrice;

    @FindBy(xpath = "//div[contains(text(), 'Первоначальный взнос')]/../input")
    private WebElement initialFee;

    @FindBy(xpath = "//div[@class='dc-input__right-section-4-9-1']/span")
    private WebElement percentInitialFee;

    @FindBy(xpath = "//div[contains(text(), 'Срок кредита')]/../input")
    private WebElement loanTerm;

    @FindBy(xpath = "//span[contains(text(), 'Страхование жизни и здоровья')]/../..//label")
    private WebElement lifeHealthInsurance;

    @FindBy(xpath = "//span[contains(text(), 'Молодая семья')]/../..//label")
    private WebElement youngFamily;

    //Ежемесячный платеж
    @FindBy(xpath = "//span[@data-e2e-id='mland-calculator/medium-result-monthly-payment']")
    private WebElement monthlyPayment;

    //Сумма кредита
    @FindBy(xpath = "//span[@data-e2e-id='mland-calculator/medium-result-credit-sum']")
    private WebElement creditSum;

    //Процентная ставка
    @FindBy(xpath = "//span[@data-e2e-id='mland-calculator/medium-result-credit-rate']")
    private WebElement creditRate;

    //Необходимый доход
    @FindBy(xpath = "//span[@data-e2e-id='mland-calculator/result-required-income']")
    private WebElement requiredIncome;


    public BuyingCompleteHousePage switchToFrame() {
        driverManager.getDriver().switchTo().frame(driverManager.getDriver().findElement(By.id("iFrameResizer0")));
        return this;
    }

    public BuyingCompleteHousePage switchToDefaultContent() {
        driverManager.getDriver().switchTo().defaultContent();
        return this;
    }

    //заполнение полей
    public BuyingCompleteHousePage fillField(String name, String value) {
        switch (name) {
            case "Стоимость недвижимости":
                fillRealEstatePrice(realEstatePrice, value);
                break;
            case "Первоначальный взнос":
                fillInputField(initialFee, value);
                break;
            case "Срок кредита":
                fillInputField(loanTerm, value);
                break;
            default:
                Assertions.fail(("Поле '" + name + "' не было найдено на странице"));
        }
        return this;
    }

    //проверка значений, расчитанных калькулятором
    public BuyingCompleteHousePage checkResult(String name, String expectedValue) {

        switch (name) {
            case "Ежемесячный платеж":
                checkValue(expectedValue, monthlyPayment);
                break;
            case "Сумма кредита":
                checkValue(expectedValue, creditSum);
                break;
            case "Процентная ставка":
                checkValue(expectedValue, creditRate);
                break;
            case "Необходимый доход":
                checkValue(expectedValue, requiredIncome);
                break;
            default:
                Assertions.fail(("Поле '" + name + "' не было найдено на странице"));
        }
        return this;
    }

    //клик по кнопке-переключателю //div[contains(text(), 'Страхование жизни и здоровья')]/preceding-sibling::div
    public BuyingCompleteHousePage clickToggleButton(String name, boolean isOn) {
        switch (name) {
            case "Страхование жизни и здоровья":
                if (isToggleButtonOn(lifeHealthInsurance) != isOn) {
                    click(lifeHealthInsurance);
                }
                break;
            case "Молодая семья":
                if (isToggleButtonOn(youngFamily) != isOn) {
                    click(youngFamily);
                }
                break;
            default:
                Assertions.fail(("Кнопка '" + name + "' не было найдено на странице"));
        }
        return this;
    }

    //заполнение полей
    @Override
    protected void fillInputField(WebElement element, String value) {
        doFill(element, value);
    }

    private void fillRealEstatePrice(WebElement element, String value) {
        doFill(element, value);
        wait.until(ExpectedConditions.not
                (ExpectedConditions.attributeContains(percentInitialFee, "innerText", "50,0%")));
    }

    private void doFill(WebElement element, String value) {
        element.click();
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"), value);
        element.sendKeys(Keys.chord(Keys.SHIFT, Keys.TAB));
        element.click();
        Assertions.assertEquals(value, element.getAttribute("defaultValue"), "Поле заполнено некорректно");
    }

    //клик по кнопке-переключателю
    private void click(WebElement toggleButton) {
        action.moveToElement(toggleButton);
        boolean marker = isToggleButtonOn(toggleButton);
        waitUtilElementToBeClickable(toggleButton);
        toggleButton.click();
        //иногда положение меняется со второго клика
        if (marker == isToggleButtonOn(toggleButton)) {
            toggleButton.click();
        }
        //своеобразный wait
        String value = requiredIncome.getAttribute("innerText");
        int i = 0;
        while (!value.equals(requiredIncome.getAttribute("innerText")) && i < 20) {
            i++;
            value = requiredIncome.getAttribute("innerText");
        }
    }

    //проверка состояния кнопки-переключателя
    private boolean isToggleButtonOn(WebElement toggleButton) {
        return toggleButton.getAttribute("className").contains("switch-root--checked");
    }

    private void checkValue(String expectedValue, WebElement element) {
        Assertions.assertEquals(FormatString.getIntFromString(expectedValue),
                FormatString.getIntFromString(element.getAttribute("innerText")),
                "Рассчитанное значение некорректно");
    }

    //Проверка открытия страницы, путём проверки Заголовка страницы
    public BuyingCompleteHousePage checkOpenBuyingCompleteHousePagePage() {
        Assertions.assertEquals("Ипотека на готовое жилье — СберБанк",
                driverManager.getDriver().getTitle(),
                "Заголовок не соответствует требуемому");
        return this;
    }
}