package com.greencity.ui.pages;


import com.greencity.ui.components.ContentEditorComponent;
import com.greencity.ui.components.InputFormComponent;
import com.greencity.ui.components.createNews.NewsTagsComponent;
import com.greencity.ui.components.createNews.*;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CreateNewsPage extends BasePage {

    @Getter
    private final InputFormComponent titleInput;
    @Getter
    private final InputFormComponent sourceInput;
    @Getter
    private final ContentEditorComponent contentEditor;
    @Getter
    private final AddImageComponent addImageComponent;
    @Getter
    private final NewsTagsComponent newsTagsComponent;
    @Getter
    private final CreateNewsButtonsComponent createNewsButtonsComponent;
    @FindBy(css = "div.popup-dialog-container")
    WebElement CancelNewsModalRoot;
    @Getter
    @FindBy(css = ".title-block")
    private WebElement titleInputRoot;
    @Getter
    @FindBy(css = ".source-block")
    private WebElement sourceInputRoot;
    @Getter
    @FindBy(css = ".textarea-wrapper")
    private WebElement contentEditorRoot;
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
    @FindBy(css = "div.ql-editor")
    private WebElement qlEditor;
    @Getter
    @FindBy(css = "div.submit-buttons")
    private WebElement submitButtonsRoot;
    @Getter
    @FindBy(css = ".date p:nth-of-type(even) span:nth-of-type(even)")
    private WebElement authorOfNews;
    @Getter
    @FindBy(css = ".date p:first-of-type span:nth-of-type(even)")
    private WebElement actualDate;

    public CreateNewsPage(WebDriver driver) {
        super(driver);
        this.titleInput = new InputFormComponent(driver, titleInputRoot);
        this.sourceInput = new InputFormComponent(driver, sourceInputRoot);
        this.contentEditor = new ContentEditorComponent(driver, contentEditorRoot);
        this.addImageComponent = new AddImageComponent(driver, addImageComponentRoot);
        this.newsTagsComponent = new NewsTagsComponent(driver, newsTagsComponentRoot);
        this.createNewsButtonsComponent = new CreateNewsButtonsComponent(driver, submitButtonsRoot);
    }

    @Step("Open 'Cancel News' dialog modal")
    public CancelNewsModal getCancelNewsModal() {
        // wait.until(ExpectedConditions.visibilityOf(CancelNewsModalRoot));
        CancelNewsModal modal = new CancelNewsModal(driver, CancelNewsModalRoot);
        modal.waitForModalVisible();
        return modal;
    }

    public CreateNewsPage enterTitleAndContent(String title, String content) {
        titleInput.typeText(title);
        getTextEditor().typeText(content);
        return this;
    }

    public CancelNewsModal openCancelModal() {
        createNewsButtonsComponent.getCancelButton().click();
        return getCancelNewsModal();
    }

    // Created a separate TextEditorComponent because the editor is not a regular input field
    public TextEditorComponent getTextEditor() {
        return new TextEditorComponent(driver);
    }

    @Step("Fill mandatory fields for news creation: title, tags, and content")
    public CreateNewsPage fillMandatoryFields(String title, List<String> tags, String contentText) {
        titleInput.typeText(title);
        contentEditor.typeText(contentText);

        List<String> selectedTags = newsTagsComponent.getSelectedTags();
        for (String tag : selectedTags) {
            newsTagsComponent.unselectTag(tag);
        }

        for (String tag : tags) {
            newsTagsComponent.selectTag(tag);
        }
        return this;
    }
    @Step("Select tag: {tagName}")
    public CreateNewsPage selectTag(String tagName) {
        newsTagsComponent.selectTag(tagName);
        return this;
    }

}