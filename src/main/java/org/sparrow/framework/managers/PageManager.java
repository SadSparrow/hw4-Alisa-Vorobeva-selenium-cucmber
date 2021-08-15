package org.sparrow.framework.managers;


import org.sparrow.framework.pages.BuyingCompleteHousePage;
import org.sparrow.framework.pages.HomePage;

public class PageManager {

    private static PageManager pageManager;
    private HomePage homePage;
    private BuyingCompleteHousePage buyingCompleteHousePage;


    private PageManager() {
    }

    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    public BuyingCompleteHousePage getBuyingCompleteHousePage() {
        if (buyingCompleteHousePage == null) {
            buyingCompleteHousePage = new BuyingCompleteHousePage();
        }
        return buyingCompleteHousePage;
    }
}