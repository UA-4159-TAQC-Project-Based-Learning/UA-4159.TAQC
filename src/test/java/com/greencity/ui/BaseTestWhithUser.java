package com.greencity.ui;

import com.greencity.ui.testrunners.TestRunnerWithUser;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class BaseTestWhithUser extends TestRunnerWithUser {
    @Test
    public void firstTest() {
        String name = homePage.getHeader().getControls().userMenuComponent().getUserFirstAndLastNameAsText();
        assertTrue(name.contains(testValueProvider.getLsUserName()), "The element is not displayed.");
    }
}
