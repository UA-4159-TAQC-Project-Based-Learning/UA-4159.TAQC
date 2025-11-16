package com.greencity.ui.components.header.navbar;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.utils.NavItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NavBar extends BaseComponent {

    public NavBar(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void open(NavItem item) {
        String href;

        switch (item) {
            case ECO_NEWS:
                href = "#/greenCity/news";
                break;
            case EVENTS:
                href = "#/greenCity/events";
                break;
            case PLACES:
                href = "#/greenCity/places";
                break;
            case ABOUT_US:
                href = "#/greenCity/about";
                break;
            case MY_SPACE:
                href = "#/greenCity/profile";
                break;
            case UBS_COURIER:
                href = "#/ubs";
                break;
            default:
                throw new IllegalArgumentException("Unknown navigation item: " + item);
        }

        WebElement link = rootElement.findElement(By.cssSelector("a[href='" + href + "']"));
        clickDynamicElement(link);
    }
}
