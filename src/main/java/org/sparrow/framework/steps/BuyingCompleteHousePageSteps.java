package org.sparrow.framework.steps;

import io.cucumber.java.ru.И;
import org.sparrow.framework.managers.PageManager;

public class BuyingCompleteHousePageSteps {

    private final PageManager pageManager = PageManager.getPageManager();

    @И("^Переключиться на фрейм$")
    public void switchToFrame() {
        pageManager.getBuyingCompleteHousePage().switchToFrame();
    }

    @И("^Заполнить поле \"(.+)\" значением \"(.+)\"$")
    public void fillInputField(String name, String value) {
        pageManager.getBuyingCompleteHousePage().fillInputField(name, value);
    }

    @И("^Кликнуть кнопку-переключатель \"(.+)\"$")
    public void clickToggleButton(String name) {
        pageManager.getBuyingCompleteHousePage().clickToggleButton(name);
    }

    @И("^Проверить расчет: в поле \"(.+)\" отображается \"(.+)\"$")
    public void checkResult(String name, String expectedValue) {
        pageManager.getBuyingCompleteHousePage().checkResult(name, expectedValue);
    }
}