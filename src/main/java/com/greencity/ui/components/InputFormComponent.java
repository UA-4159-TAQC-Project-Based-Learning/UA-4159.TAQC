package com.greencity.ui.components;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Getter
public class InputFormComponent extends BaseComponent {

    private final WebElement labelElement;

    private final WebElement fieldInfoElement;

    private final WebElement fieldElement;

    public InputFormComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.labelElement = rootElement.findElement(By.cssSelector(".title-wrapper h3"));
        this.fieldInfoElement = rootElement.findElement(By.cssSelector(".field-info"));
        this.fieldElement = rootElement.findElement(By.cssSelector("input, textarea"));

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

    public InputFormComponent setValue(String text) {
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
