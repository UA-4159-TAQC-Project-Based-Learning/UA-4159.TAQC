package com.greencity.ui.components.home;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.pages.EcoNewsPage;
import io.qameta.allure.Step;
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

    @Step("Get Eco News section title text")
    public String getTitleText() {
        return ecoNewsTitle.getText().trim();
    }

    @Step("Get 'Read All News' link text")
    public String getReadAllNewsLinkText() {
        return readAllNewsLink.getText().trim();
    }

    @Step("Get 'Read All News' link href attribute")
    public String getReadAllNewsLinkHref() {
        return readAllNewsLink.getAttribute("href");
    }

    @Step("Click 'Read All News' link and Open Eco News page")
    public EcoNewsPage clickReadAllNews() {
        waitUntilElementClickable(readAllNewsLink);
        clickDynamicElement(readAllNewsLink);
        waitUntilPageLouder();
        return new EcoNewsPage(driver);
    }
}
