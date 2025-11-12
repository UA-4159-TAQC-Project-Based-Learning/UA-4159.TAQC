package com.greencity.ui.components.footer;

import com.greencity.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FooterComponent extends BaseComponent {
    @Getter
    @FindBy(xpath = ".//a[.//img[@src='assets/img/logo-l.svg']]")
    private WebElement logo;

    @Getter
    @FindBy(xpath = ".//nav//a")
    private List<WebElement> navigationItems;

    @Getter
    @FindBy(xpath = ".//nav[@role='navigation']//ul[@class='footer_left-side']//a")
    private List<WebElement> siteNavigationItems;

    @Getter
    @FindBy(xpath = ".//nav//ul//li[@class='footer_social-wrp']//a")
    private List<WebElement> socialNavigationItems;

    public FooterComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}