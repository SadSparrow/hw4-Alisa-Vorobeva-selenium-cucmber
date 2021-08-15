package org.sparrow.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.sparrow.framework.util.FormatString;

import java.util.List;

public class BuyingCompleteHousePage extends BasePage {

    @FindBy(xpath = "//div[contains(@class, 'dc-input__input-container')]/div")
    private List<WebElement> inputFields;

    @FindBy(xpath = "//div[@data-test-id='discounts']")
    private WebElement toggleButtonsContainer;

    @FindBy(xpath = "//div[@data-test-id='main-results-block']//ul[not(@data-e2e-id)]/li " +
            "| //div[@data-test-id='required-income-block']")
    List<WebElement> listResult;

    public BuyingCompleteHousePage switchToFrame() {
        driverManager.getDriver().switchTo().frame(driverManager.getDriver().findElement(By.id("iFrameResizer0")));
        return this;
    }

    public BuyingCompleteHousePage switchToDefaultContent() {
        driverManager.getDriver().switchTo().defaultContent();
        return this;
    }

    //проверка значений, расчитанных калькулятором
    public BuyingCompleteHousePage checkResult(String name, String expectedValue) {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (WebElement element : listResult) {
            String text = element.getText();
            if (text.contains(name)) {
                String[] values = text.split("\n");
                Assertions.assertEquals(FormatString.getStringWithoutSpaceLowerCase(expectedValue),
                        FormatString.getStringWithoutSpaceLowerCase(values[1]),
                        "Рассчитанное значение некорректно");
                return this;
            }
        }
        Assertions.fail(("Поле '" + name + "' не было найдено на странице"));
        return this;
    }

    //клик по кнопке-переключателю
    public BuyingCompleteHousePage clickToggleButton(String name) {
        WebElement toggleButton = getToggleButtonByName(name);
        boolean marker = isToggleButtonOn(toggleButton);
//        waitUtilElementToBeClickable(toggleButton); not work!
        toggleButton.click();
        try {
            wait.until(ExpectedConditions.attributeContains(toggleButton, "ariaChecked", String.valueOf(!marker)));
        } catch (TimeoutException e) {
            Assertions.fail("Состояние кнопки-переключателя не изменилось");
        }
        return this;
    }

    //проверка состояния кнопки-переключателя по имени
    public BuyingCompleteHousePage isToggleButtonOn(String name, boolean expected) {
        Assertions.assertEquals(expected, isToggleButtonOn(getToggleButtonByName(name)),
                "Состояние кнопки-переключателя некорректно");
        return this;
    }

    //возвращает элемент - кнопку-переклчатель по имени
    private WebElement getToggleButtonByName(String name) {
        WebElement toggleButtonName = toggleButtonsContainer.findElement(By.xpath(".//span[contains(text(), '" + name + "')]"));
        return toggleButtonName.findElement(By.xpath("./../..//input"));
    }

    //проверка состояния кнопки-переключателя
    private boolean isToggleButtonOn(WebElement toggleButton) {
        return Boolean.parseBoolean(toggleButton.getAttribute("ariaChecked"));
    }

    //заполнение полей
    public BuyingCompleteHousePage fillInputField(String name, String value) {
        By inputXpath = By.xpath("./../input");

        for (WebElement item : inputFields) {
            if (item.getText().contains(name)) {
                WebElement element = item.findElement(inputXpath);
//                int i = 0;
//
//                do {
//                    element.click();
//                    clear(i++);
//                } while (!element.getAttribute("defaultValue").equals(""));
                element.click();
                element.sendKeys(Keys.chord(Keys.CONTROL, "a"), value);
                Assertions.assertEquals(value, element.getAttribute("defaultValue"), "Поле заполнено некорректно");
                return this;
            }
        }
        Assertions.fail(("Поле '" + name + "' не было найдено на странице"));
        return this;
    }

    //очистка поля input
    private void clear(int i) {
        if (i < 10) {
            action.sendKeys(Keys.chord(Keys.CONTROL, "A"))
                    .sendKeys(Keys.BACK_SPACE)
                    .build().perform();
        } else {
            Assertions.fail("Неудалось очистить поле");
        }
    }

    //Проверка открытия страницы, путём проверки Заголовка страницы
    public BuyingCompleteHousePage checkOpenBuyingCompleteHousePagePage() {
        Assertions.assertEquals("Ипотека на готовое жилье — СберБанк",
                driverManager.getDriver().getTitle(),
                "Заголовок не соответствует требуемому");
        return this;
    }
}