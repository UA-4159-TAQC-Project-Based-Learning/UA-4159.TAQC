package com.greencity.ui.components;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

@Getter
public class InputFormComponent extends AbstractInputComponent {

    @FindBy(css = "input, textarea")
    private WebElement fieldElement;

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

    public boolean isTextArea() {
        return fieldElement.getTagName().equalsIgnoreCase("textarea");
    }

    public boolean isInput() {
        return fieldElement.getTagName().equalsIgnoreCase("input");
    }

    public boolean isValidField() {
        return hasClass(fieldElement, "ng-valid");
    }

    public boolean hasWarningFieldInfo() {
        return hasClass(fieldInfoElement, "warning");
    }

    public boolean isInvalidField() {
        return hasClass(fieldElement, "ng-invalid");
    }

    public boolean isWarningsForField() {
        return hasClass(fieldElement, "field-warning");
    }

    public String getBorderColorWhenWarning() {
        wait.until(driver -> isWarningsForField());
        return fieldElement.getCssValue("border-color");
    }
}
