package com.greencity.ui;

import com.greencity.ui.testrunners.BaseTestRunner;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FooterTest extends BaseTestRunner {

    @Test
    @DisplayName("Footer logo is clickable")
    void testFooterLogoIsClickable() {
        WebElement logo = homePage.getFooter().getLogo();
        Assert.assertTrue(logo.isEnabled(), "Footer logo should be clickable");
    }

    @Test
    @DisplayName("Footer navigation items exist and are visible")
    void testNavigationItemsExist() {
        List<WebElement> navItems = homePage.getFooter().getNavigationItems();
        Assert.assertFalse(navItems.isEmpty(), "Footer navigation items should not be empty");

        for (WebElement item : navItems) {
            Assert.assertTrue(item.isDisplayed(), "Navigation item should be displayed: " + item.getText());
            Assert.assertTrue(item.isEnabled(), "Footer logo should be clickable: " + item.getText());
        }
    }

    @Test
    @DisplayName("Footer social links exist and are valid")
    void testSocialNavigationItemsExist() {
        List<WebElement> socialItems = homePage.getFooter().getSocialNavigationItems();
        Assert.assertFalse(socialItems.isEmpty(), "Footer social navigation should not be empty");

        for (WebElement item : socialItems) {
            Assert.assertTrue(item.isDisplayed(), "Social link should be visible");
            String href = item.getAttribute("href");
            Assert.assertNotNull(href, "Social link should have an href");
            Assert.assertTrue(item.isDisplayed(), "Social link should be visible");
            Assert.assertFalse(item.findElements(By.xpath(".//img")).isEmpty(), "Social link should be image");
        }
    }

    @Test
    @DisplayName("Footer site navigation items exist and are valid")
    void testSiteNavigationItemsExist() {
        List<WebElement> siteItems = homePage.getFooter().getSiteNavigationItems();
        Assert.assertFalse(siteItems.isEmpty(), "Footer site navigation should not be empty");

        for (WebElement item : siteItems) {
            Assert.assertTrue(item.isDisplayed(), "Site navigation link should be visible");
            String href = item.getAttribute("href");
            Assert.assertNotNull(href, "Site navigation link should have an href");
            Assert.assertFalse(href.isBlank(), "Site navigation link href should not be blank");
            Assert.assertFalse(item.getText().isEmpty(), "Site navigation should be text");
        }
    }
}