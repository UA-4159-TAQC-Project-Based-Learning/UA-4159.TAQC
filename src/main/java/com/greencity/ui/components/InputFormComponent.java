package com.greencity.ui.components;

import io.qameta.allure.Step;
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

    @Step("Clear input field")
    public InputFormComponent clear() {
        fieldElement.click();
        fieldElement.clear();
        return this;
    }

    @Step("Click on input field")
    public InputFormComponent clickOnField() {
        fieldElement.click();
        return this;
    }

    @Step("Get value from input field")
    public String getValue() {
        return fieldElement.getAttribute("value");
    }

    @Step("Typing text '{text}' into input field")
    public InputFormComponent typeText(String text) {
        fieldElement.click();
        fieldElement.clear();
        fieldElement.sendKeys(text);
        return this;
    }

    @Step("Get placeholder text from input field")
    public String getPlaceholderText() {
        return fieldElement.getAttribute("placeholder");
    }

    @Step("Check if field is textarea")
    public boolean isTextArea() {
        return fieldElement.getTagName().equalsIgnoreCase("textarea");
    }

    @Step("Check if field is input")
    public boolean isInput() {
        return fieldElement.getTagName().equalsIgnoreCase("input");
    }

    @Step("Check if field is valid")
    public boolean isValidField() {
        return hasClass(fieldElement, "ng-valid");
    }

    public boolean hasWarningFieldInfo() {
        return hasClass(fieldInfoElement, "warning");
    }

    @Step("Check if field is invalid")
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
