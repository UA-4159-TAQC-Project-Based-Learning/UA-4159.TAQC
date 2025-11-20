package com.greencity.ui;

import com.greencity.ui.testrunners.BaseTestRunner;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class BaseTest extends BaseTestRunner {
    @Test
    public void firstTest() {
        WebElement image = homePage.getHeader().getLogo();
        assertTrue(image.isDisplayed(), "The element is not displayed.");
    }
}