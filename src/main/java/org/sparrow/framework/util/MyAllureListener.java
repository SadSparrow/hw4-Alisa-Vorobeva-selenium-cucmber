package org.sparrow.framework.util;

import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.Status;
import io.cucumber.plugin.event.TestStepFinished;
import io.qameta.allure.Allure;
import io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.sparrow.framework.managers.DriverManager;

public class MyAllureListener extends AllureCucumber6Jvm {

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepFinished.class, testStepFinished -> {
            if (testStepFinished.getResult().getStatus().equals(Status.FAILED)) {
                Allure.getLifecycle().addAttachment("screenshot", "image/jpeg", null,
                        ((TakesScreenshot) DriverManager.getDriverManager().getDriver()).getScreenshotAs(OutputType.BYTES));
            }
        });
        super.setEventPublisher(publisher);
    }
}
