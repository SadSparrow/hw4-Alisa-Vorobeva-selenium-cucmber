package org.sparrow.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(xpath = "//button[@class='kitt-cookie-warning__close']")
    private WebElement cookiesBtnClose;

    @FindBy(xpath = "//li[contains(@class,'kitt-top-menu__item_first')]/a[@role or @aria-expanded]")
    private List<WebElement> listBaseMenu;

    @FindBy(xpath = "//a[@data-cga_click_top_menu]")
    private List<WebElement> listSubMenu;


    //закрыть сообщение cookies
    public HomePage closeCookiesDialog() {
        waitUtilElementToBeClickable(cookiesBtnClose).click();
        return this;
    }

    //выбрать пункт меню по названию
    public HomePage selectBaseMenu(String nameBaseMenu) {
        for (WebElement menuItem : listBaseMenu) {
            if (menuItem.getText().trim().equalsIgnoreCase(nameBaseMenu)) {
                waitUtilElementToBeClickable(menuItem).click();
                return this;
            }
        }
        Assertions.fail("Меню '" + nameBaseMenu + "' не было найдено на странице " + driverManager.getDriver().getCurrentUrl());
        return this;
    }

    //выбрать подменю
    public BuyingCompleteHousePage selectSubMenu(String nameSubMenu) {
        for (WebElement menuItem : listSubMenu) {
            if (menuItem.getText().equalsIgnoreCase(nameSubMenu)) {
                waitUtilElementToBeClickable(menuItem).click();
                return pageManager.getBuyingCompleteHousePage().checkOpenBuyingCompleteHousePagePage();
            }
        }
        Assertions.fail("Подменю '" + nameSubMenu + "' не было найдено на стартовой странице!");
        return pageManager.getBuyingCompleteHousePage();
    }
}