package org.sparrow.framework.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.sparrow.framework.util.PropConst.PATH_CHROME_DRIVER_WINDOWS;

public class DriverManager {

    private WebDriver driver;
    private static DriverManager INSTANCE = null;
    private final TestPropManager props = TestPropManager.getTestPropManager();

    private DriverManager() {
    }

    public static DriverManager getDriverManager() {
        if (INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return INSTANCE;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private void initDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        options.addArguments("--disable-notifications");
        System.setProperty("webdriver.chrome.driver", props.getProperty(PATH_CHROME_DRIVER_WINDOWS));
        driver = new ChromeDriver(options);
    }
}