package com.greencity.ui.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AbstractInputComponent  extends BaseComponent{

    @FindBy(css = ".title-wrapper h3")
    protected WebElement labelElement;

    @FindBy(css = ".field-info")
    protected WebElement fieldInfoElement;


    public AbstractInputComponent(WebDriver driver, WebElement rootElement){
        super(driver, rootElement);
    }

    public String getLabelText() {
        return labelElement.getText().trim();
    }

    public String getFieldInfoText() {
        return fieldInfoElement.getText().trim();
    }

    public boolean hasClass(WebElement element, String className) {
        if (element == null) {
            return false;
        }
        String searchingClass = element.getAttribute("class");
        return searchingClass != null && searchingClass.contains(className);
    }

    public boolean hasWarningFieldInfo() {
        return hasClass(fieldInfoElement, "warning");
    }

}
