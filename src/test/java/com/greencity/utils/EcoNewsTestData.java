package com.greencity.utils;

import com.greencity.ui.utils.NavItem;
import org.testng.annotations.DataProvider;

public class EcoNewsTestData {

    @DataProvider(name = "createNewsDataForCancelTests")
    public static Object[][] createNewsDataForCancelTests() {
        return new Object[][]{
                { "title example", "content example" },
                { "valid title", "valid content example" },
                { "", ""}
        };
    }

    @DataProvider(name = "uniqueTitleAndContentToCancel")
    public static Object[][] uniqueTitleAndContentToCancel() {
        String uniqueTitle = "news-to-cancel-" + System.currentTimeMillis();
        String uniqueContent = "Content body for " + uniqueTitle;

        return new Object[][]{
                { uniqueTitle, uniqueContent }
        };
    }

    @DataProvider(name = "navItemsForCancelTest")
    public static Object[][] navItemsForCancelTest() {
        return new Object[][]{
                {NavItem.ECO_NEWS},
                {NavItem.LOGO}
        };
    }

}
