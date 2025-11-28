package com.greencity.ui.components;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class ContentEditorComponent extends BaseComponent {

    @FindBy(css = ".title-wrapper h3")
    private WebElement labelElement;

    @FindBy(css = ".field-info")
    private WebElement fieldInfoElement;

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



    public ContentEditorComponent(WebDriver driver, WebElement rootElement){
        super(driver, rootElement);
    }

    public String getLabelText() {
        return labelElement.getText().trim();
    }

    public String getFieldInfoText() {
        return fieldInfoElement.getText().trim();
    }

    public ContentEditorComponent clear() {
        textInputArea.clear();
        return this;
    }

    public ContentEditorComponent clickOnMainText() {
        textInputArea.click();
        return this;
    }

    public String getMainTextValue() {
        return textInputArea.getAttribute("value");
    }

    public ContentEditorComponent typeText(String text) {
        textInputArea.click();
        textInputArea.sendKeys(text);
        return this;
    }

    public String getInputAreaText() {
        return textInputArea.getText().trim();
    }

}
