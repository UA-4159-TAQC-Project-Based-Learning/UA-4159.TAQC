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
        WebElement link = rootElement.findElement(
                By.cssSelector("a[href='" + item.href() + "']"));
        clickDynamicElement(link);
    }
}
