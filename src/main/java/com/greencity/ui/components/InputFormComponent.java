package com.greencity.ui.components;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class InputFormComponent extends BaseComponent {

    @FindBy(css = ".title-wrapper h3")
    private WebElement labelElement;

    @FindBy(css = ".field-info")
    private WebElement fieldInfoElement;

    @FindBy(css = "input, textarea")
    private WebElement fieldElement;

    @FindBy(css = ".textarea-wrapper quill-editor")
    private ContentEditorComponent contentEditor;

    public InputFormComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public InputFormComponent clear() {
        fieldElement.click();
        fieldElement.clear();
        return this;
    }

    public InputFormComponent clickOnField() {
        fieldElement.click();
        return this;
    }

    public String getValue() {
        return fieldElement.getAttribute("value");
    }

    public InputFormComponent typeText(String text) {
        fieldElement.click();
        fieldElement.clear();
        fieldElement.sendKeys(text);
        return this;
    }

    public String getPlaceholderText() {
        return fieldElement.getAttribute("placeholder");
    }

    public String getLabelText() {
        return labelElement.getText().trim();
    }

    public String getFieldInfoText() {
        return fieldInfoElement.getText().trim();
    }

    public boolean isTextArea() {
        return fieldElement.getTagName().equalsIgnoreCase("textarea");
    }

    public boolean isInput() {
        return fieldElement.getTagName().equalsIgnoreCase("input");
    }

    public boolean hasClass(WebElement element, String className) {
        if (element == null) {
            return false;
        }
        String searchingClass = element.getAttribute("class");
        return searchingClass != null && searchingClass.contains(className);
    }

    public boolean isValid() {
        return hasClass(fieldElement, "ng-valid");
    }

    public boolean isInvalid() {
        return hasClass(fieldElement, "ng-invalid");
    }


}
