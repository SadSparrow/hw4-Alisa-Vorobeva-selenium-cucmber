package org.sparrow.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sparrow.framework.managers.DriverManager;
import org.sparrow.framework.managers.PageManager;
import org.sparrow.framework.managers.TestPropManager;

public class BasePage {

    protected final DriverManager driverManager = DriverManager.getDriverManager();
    protected PageManager pageManager = PageManager.getPageManager();
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 10, 1000);
    private final TestPropManager props = TestPropManager.getTestPropManager();
    protected JavascriptExecutor js = (JavascriptExecutor) driverManager.getDriver();
    protected Actions action = new Actions(driverManager.getDriver());

    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }


    //явное ожидание того, что элемент станет видимым
    protected WebElement waitUtilElementToBeVisible(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            Assertions.fail("Элемент не виден");
        }
        return element;
    }

    //явное ожидание того, что элемент станет кликабельным
    protected WebElement waitUtilElementToBeClickable(WebElement element) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            Assertions.fail("Элемент не кликабелен");
        }
        return element;
    }

    //скрол до элемента
    protected WebElement scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }

    //заполнение inputа с проверкой
    protected void fillInputField(WebElement field, String value) {
        waitUtilElementToBeClickable(field).click();
        field.sendKeys(value);
        try {
            wait.until(ExpectedConditions.attributeContains(field, "value", value));
        } catch (TimeoutException e) {
            Assertions.fail("Поле было заполнено некорректно");
        }
    }
}