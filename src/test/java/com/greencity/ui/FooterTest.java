package com.greencity.ui;

import com.greencity.ui.testrunners.BaseTestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class FooterTest extends BaseTestRunner {

    private SoftAssert softAssert;

    @BeforeMethod
    public void beforeMethod() {
        softAssert = new SoftAssert();
    }

    @Test
    void testFooterLogoIsClickable() {
        WebElement logo = homePage.getFooter().getLogo();
        softAssert.assertTrue(logo.isEnabled(), "Footer logo should be clickable");
        softAssert.assertAll();
    }

    @Test
    void testNavigationItemsExist() {
        List<WebElement> navItems = homePage.getFooter().getNavigationItems();
        Assert.assertFalse(navItems.isEmpty(), "Footer navigation items should not be empty");

        for (WebElement item : navItems) {
            softAssert.assertTrue(item.isDisplayed(), "Navigation item should be displayed: " + item.getText());
            softAssert.assertTrue(item.isEnabled(), "Footer logo should be clickable: " + item.getText());
        }
        softAssert.assertAll();
    }

    @Test
    void testSocialNavigationItemsExist() {
        List<WebElement> socialItems = homePage.getFooter().getSocialNavigationItems();
        Assert.assertFalse(socialItems.isEmpty(), "Footer social navigation should not be empty");

        for (WebElement item : socialItems) {
            softAssert.assertTrue(item.isDisplayed(), "Social link should be visible");
            String href = item.getAttribute("href");
            softAssert.assertNotNull(href, "Social link should have an href");
            softAssert.assertFalse(item.findElements(By.xpath(".//img")).isEmpty(), "Social link should be image");
        }
        softAssert.assertAll();
    }

    @Test
    void testSiteNavigationItemsExist() {
        List<WebElement> siteItems = homePage.getFooter().getSiteNavigationItems();
        Assert.assertFalse(siteItems.isEmpty(), "Footer site navigation should not be empty");

        for (WebElement item : siteItems) {
            softAssert.assertTrue(item.isDisplayed(), "Site navigation link should be visible");
            String href = item.getAttribute("href");
            softAssert.assertNotNull(href, "Site navigation link should have an href");
            softAssert.assertFalse(href.isBlank(), "Site navigation link href should not be blank");
            softAssert.assertFalse(item.getText().isEmpty(), "Site navigation should be text");
        }
        softAssert.assertAll();
    }
}