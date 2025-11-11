package com.greencity.ui;

import com.greencity.ui.testrunners.BaseTestRunner;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BaseTest extends BaseTestRunner {
    @Test
    public void firstTest() {
        WebElement image = homePage.getHeader().getLogo();
        Assert.assertTrue(image.isDisplayed(), "The element is not displayed.");
    }
}