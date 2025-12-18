package com.greencity.ui.components;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class ContentEditorComponent extends AbstractInputComponent {

    @FindBy (css = ".ql-editor p")
    private WebElement textInputArea;

    @FindBy (css = "button.gl-bold")
    private WebElement boldButton;

    @FindBy (css = "button.gl-italic")
    private WebElement italicButton;

    @FindBy (css = "button.ql-underline")
    private WebElement underLineButton;

    @FindBy (css = "button.ql-strike")
    private WebElement strikeButton;

    @FindBy (css = "button.ql-blockquote")
    private WebElement quoteButton;

    @FindBy (css = "button.ql-code-block")
    private WebElement codeButton;

    @FindBy (css = "button.ql-header[value='1']")
    private WebElement headerOneButton;

    @FindBy (css = "button.ql-header[value='2']")
    private WebElement headerTwoButton;

    @FindBy (css = "button.ql-list[value='ordered']")
    private WebElement listOrderedButton;

    @FindBy (css = "button.ql-list[value='bullet']")
    private WebElement listBulletButton;

    @FindBy (css = "button.ql-script[value='sub']")
    private WebElement subscriptButton;

    @FindBy (css = "button.ql-script[value='super']")
    private WebElement superscriptButton;

    @FindBy (css = "button.ql-indent[value='-1']")
    private WebElement decreaseIndentButton;

    @FindBy (css = "button.ql-indent[value='+1']")
    private WebElement increaseIndentButton;

    @FindBy (css = "button.ql-direction[value='rtl']")
    private WebElement rightToLeftButton;

    @FindBy (css = "quill-counter")
    private WebElement notEnoughCharacterMessage;


    //in progress



    public ContentEditorComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }


    public ContentEditorComponent clear() {
        textInputArea.clear();
        return this;
    }

    @Step ("Click on main text in content editor")
    public ContentEditorComponent clickOnMainText() {
        textInputArea.click();
        return this;
    }

    @Step ("Get placeholder text on main text in content editor")
    public String getPlaceholderValue() {
        return textInputArea.getAttribute("data-placeholder");
    }

    @Step("Typing text '{text}' into content editor")
    public ContentEditorComponent typeText(String text) {
        textInputArea.click();
        textInputArea.sendKeys(text);
        return this;
    }

    @Step ("Get value on main text in content editor")
    public String getInputAreaText() {
        return textInputArea.getText().trim();
    }

    @Step("Check if content editor shows 'not enough characters' warning")
    public boolean isEnoughCharacterMainText() {
        return hasClass(notEnoughCharacterMessage, "warning");
    }

    @Step ("Check is field has warning message")
    public boolean hasTextInputAreaWarning() {
        return hasClass(textInputArea, "warning");
    }

}
