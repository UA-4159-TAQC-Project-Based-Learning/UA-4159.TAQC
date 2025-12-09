package com.greencity.ui.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class CreateNewsPreviewPage extends BasePage {

    @Getter
    @FindBy(css = ".back-button .button-link")
    private WebElement backLink;

    @Getter
    @FindBy(css = "form.submit-form button.primary-global-button")
    private WebElement publishButton;

    @Getter
    @FindBy (css = ".news-content .news-title-container .news-title")
    private WebElement newsTitle;

    @Getter
    @FindBy (css = ".tags .tags-item")
    private List<WebElement> tags;

    @Getter
    @FindBy (css = ".news-content .news-info-date")
    private WebElement date;

    @Getter
    @FindBy (css = ".news-content .news-info-author")
    private WebElement author;

    @Getter
    @FindBy (css = ".news-content .news-image")
    private WebElement image;

    @Getter
    @FindBy (css = ".news-content .news-text-container .news-text")
    private WebElement textContent;

    public CreateNewsPreviewPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPublishButtonPresent() {
        return publishButton.isDisplayed();
    }
}