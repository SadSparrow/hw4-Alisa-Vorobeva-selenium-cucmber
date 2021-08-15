package org.sparrow.framework.steps;

import io.cucumber.java.ru.И;
import org.sparrow.framework.managers.PageManager;

public class HomePageSteps {

    private final PageManager pageManager = PageManager.getPageManager();

    @И("^Закрыть сообщение cookies$")
    public void closeCookiesDialog() {
        pageManager.getHomePage().closeCookiesDialog();
    }

    @И("^Выбрать \"(.+)\" в главном меню$")
    public void selectBaseMenu(String nameMenu) {
        pageManager.getHomePage().selectBaseMenu(nameMenu);
    }

    @И("^Выбрать \"(.+)\" в подменю главного меню$")
    public void closeCookiesDialog(String nameSubMenu) {
        pageManager.getHomePage().selectSubMenu(nameSubMenu);
    }
}