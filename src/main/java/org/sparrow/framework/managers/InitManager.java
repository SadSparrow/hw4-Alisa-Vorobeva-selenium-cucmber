package org.sparrow.framework.managers;

import java.util.concurrent.TimeUnit;

import static org.sparrow.framework.util.PropConst.*;

public class InitManager {

    private static final TestPropManager props = TestPropManager.getTestPropManager();
    private static final DriverManager driverManager = DriverManager.getDriverManager();

    public static void initFramework() {
        driverManager.getDriver().manage().window().maximize();
        driverManager.getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
        driverManager.getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(props.getProperty(PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
        driverManager.getDriver().get(TestPropManager.getTestPropManager().getProperty(BASE_URL));
    }

    public static void quitFramework() {
        driverManager.quitDriver();
    }
}