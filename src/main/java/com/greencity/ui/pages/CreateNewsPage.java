package com.greencity.ui.pages;

import com.greencity.ui.components.CreateNewsInputComponent;
import com.greencity.ui.components.createNews.AddImageComponent;
import com.greencity.ui.components.createNews.CancelNewsModal;
import com.greencity.ui.components.createNews.CreateNewsButtonsComponent;
import com.greencity.ui.components.createNews.NewsTagsComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateNewsPage extends BasePage {

    @Getter
    private final CreateNewsInputComponent createNewsInputComponent;
    @Getter
    private final AddImageComponent addImageComponent;
    @Getter
    private final NewsTagsComponent newsTagsComponent;
    @Getter
    private final CreateNewsButtonsComponent createNewsButtonsComponent;

    @FindBy(css = "div.popup-dialog-container")
    WebElement CancelNewsModalRoot;

    @Getter
    @FindBy(css = "h2.title-header")
    private WebElement pageTitle;
    @Getter
    @FindBy(css = "p.title-description")
    private WebElement pageTitleDescription;
    @Getter
    @FindBy(css = "div.form-container")
    private WebElement inputsComponentRoot;
    @Getter
    @FindBy(css = "div.image-block")
    private WebElement addImageComponentRoot;
    @Getter
    @FindBy(css = "div.tags-block")
    private WebElement newsTagsComponentRoot;
    @Getter
    @FindBy(css = "div.submit-buttons")
    private WebElement submitButtonsRoot;

    public CreateNewsPage(WebDriver driver) {
        super(driver);
        this.createNewsInputComponent = new CreateNewsInputComponent(driver, inputsComponentRoot);
        this.addImageComponent = new AddImageComponent(driver, addImageComponentRoot);
        this.newsTagsComponent = new NewsTagsComponent(driver, newsTagsComponentRoot);
        this.createNewsButtonsComponent = new CreateNewsButtonsComponent(driver, submitButtonsRoot);
    }

    public CancelNewsModal getCancelNewsModal() {
        wait.until(ExpectedConditions.visibilityOf(CancelNewsModalRoot));
        return new CancelNewsModal(driver, CancelNewsModalRoot);
    }

}