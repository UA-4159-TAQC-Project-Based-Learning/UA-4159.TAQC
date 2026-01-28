package com.greencity.ui.components.createNews;

import com.greencity.ui.components.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TextEditorComponent extends BaseComponent {

    // Intentionally not using @FindBy for the modal root to support the overloaded constructor
    private static final By TEXT_EDITOR_ROOT_LOCATOR = By.cssSelector(".textarea-wrapper");

    @FindBy(css = ".ql-editor[contenteditable='true']")
    private WebElement editableArea;

    public TextEditorComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public TextEditorComponent(WebDriver driver) {
        this(driver, new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(TEXT_EDITOR_ROOT_LOCATOR)));
    }

    public void typeText(String text) {
        waitUntilElementClickable(editableArea);
        clickDynamicElement(editableArea);
        editableArea.sendKeys(text);
    }

    public String getText() {
        return editableArea.getText();
    }
}
