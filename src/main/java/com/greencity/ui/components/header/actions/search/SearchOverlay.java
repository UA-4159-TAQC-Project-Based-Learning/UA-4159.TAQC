package com.greencity.ui.components.header.actions.search;

import com.greencity.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchOverlay extends BaseComponent {

    @Getter
    @FindBy(css = "input[class*='search']")
    private WebElement searchInput;

    @Getter
    @FindBy(css = "img[class*='close']")
    private WebElement closeButton;

    @Getter
    @FindBy(css = "[role='progressbar']")
    private WebElement loader;

    // TODO - Items Found

    public SearchOverlay(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public boolean isOpen(){
        return searchInput.isDisplayed();
    }

    public void type(String text) {
        waitUntilElementVisible(searchInput);
        clickDynamicElement(searchInput);
        searchInput.clear();
        searchInput.sendKeys(text);
    }

    public void submitQuery() {
        searchInput.sendKeys((Keys.ENTER));
    }

    public void close() {
        clickDynamicElement(closeButton);
    }
}
