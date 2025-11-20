package com.greencity.ui.pages;

import com.greencity.ui.components.InputFormComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class CreateNewsPreviewPage extends BasePage {

    @Getter
    @FindBy(css = ".back-button .button-link")
    private WebElement BackLink;

    @Getter
    @FindBy(css = "form.submit-form button.primary-global-button")
    private WebElement PublishButton;

    @Getter
    @FindBy (css = ".news-content .news-title-container .news-title")
    private WebElement NewsTitle;

    @Getter
    @FindBy (css = ".tags .tags-item")
    private List<WebElement> Tags;

    @Getter
    @FindBy (css = ".news-content .news-info-date")
    private WebElement Date;

    @Getter
    @FindBy (css = ".news-content .news-info-author")
    private WebElement Author;

    @Getter
    @FindBy (css = ".news-content .news-image")
    private WebElement Image;

    @Getter
    @FindBy (css = ".news-content .news-text-container .news-text")
    private WebElement Text;

    public CreateNewsPreviewPage(WebDriver driver) {
        super(driver);
    }
}