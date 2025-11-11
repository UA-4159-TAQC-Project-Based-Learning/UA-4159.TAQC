package com.greencity.ui.components.header;

import com.greencity.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HeaderComponent extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//img[@src='assets/img/logo.svg']")
    private WebElement logo;

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}