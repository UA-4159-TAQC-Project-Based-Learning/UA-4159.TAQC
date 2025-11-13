package com.greencity.ui.components.home;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.pages.ecoNewsPage.EcoNewsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EcoNewsSectionComponent extends BaseComponent {

    @FindBy(xpath = ".//h2")
    private WebElement ecoNewsTitle;

    @FindBy(xpath = ".//a")
    private WebElement readAllNewsLink;

    public EcoNewsSectionComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    //----------ecoNewsTitle----------
    public String getTitleText() {
        return ecoNewsTitle.getText().trim();
    }

    //----------ReadAllLink----------
    public String getReadAllNewsLinkText() {
        return readAllNewsLink.getText().trim();
    }
    public String getReadAllNewsLinkHref() {
        return readAllNewsLink.getAttribute("href");
    }

    //----------Business logic----------
    public EcoNewsPage clickReadAllNews() {
        waitUntilElementClickable(readAllNewsLink);
        clickDynamicElement(readAllNewsLink);
        return new EcoNewsPage(driver);
    }
}
