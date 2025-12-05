package com.greencity.ui.components;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
@Getter
public abstract class AbstractInputComponent  extends BaseComponent {

    @FindBy(css = ".title-wrapper h3")
    protected WebElement labelElement;

    @FindBy(css = ".field-info")
    protected WebElement fieldInfoElement;


    public AbstractInputComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step ("Get name of field")
    public String getLabelText() {
        return labelElement.getText().trim();
    }

    @Step ("Get field info text")
    public String getFieldInfoText() {
        return fieldInfoElement.getText().trim();
    }

    @Step ("Check is field has warning message")
    public boolean hasWarningFieldInfo() {
        return hasClass(fieldInfoElement, "warning");
    }

}
