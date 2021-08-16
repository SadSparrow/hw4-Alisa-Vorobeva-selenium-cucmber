package org.sparrow.framework.steps;

import io.cucumber.java.ru.И;
import org.sparrow.framework.managers.PageManager;

import java.util.Map;

public class BuyingCompleteHousePageSteps {

    private final PageManager pageManager = PageManager.getPageManager();

    @И("^Переключиться на фрейм$")
    public void switchToFrame() {
        pageManager.getBuyingCompleteHousePage().switchToFrame();
    }

    @И("^Заполнить поля значениями$")
    public void fillInputField(Map<String, String> arg) {
        arg.forEach((name, value) -> pageManager.getBuyingCompleteHousePage().fillField(name, value));
    }

    @И("^Перевести кнопку-переключатель \"(Страхование жизни и здоровья|Молодая семья)\" в состояние \"(true|false)\"$")
    public void clickToggleButton(String name, boolean isOn) {
        pageManager.getBuyingCompleteHousePage().clickToggleButton(name, isOn);
    }

    @И("^Проверить расчет$")
    public void checkResult(Map<String, String> arg) {
        arg.forEach((name, expectedValue) -> pageManager.getBuyingCompleteHousePage().checkResult(name, expectedValue));
    }
}