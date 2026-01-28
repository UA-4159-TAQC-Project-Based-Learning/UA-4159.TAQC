package com.greencity.ui.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TextSizeDropdownComponent extends BaseComponent {

    @FindBy(css = "span.ql-size.ql-picker")
    private WebElement selectSizePicker;

    @FindBy(css = "#ql-picker-options-6 .ql-picker-item")
    private List<WebElement> sizeOptions;


    public TextSizeDropdownComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public TextSizeDropdownComponent openDropdown() {
        selectSizePicker.click();
        return this;
    }

    public TextSizeDropdownComponent selectSize(String value) {
        openDropdown();
        sizeOptions.stream()
                .filter(option -> value.equals(option.getAttribute("data-value")))
                .findFirst()
                .ifPresent(WebElement::click);
        return this;
    }

    public String getSelectedSize() {
        openDropdown();
        return sizeOptions.stream()
                .filter(element -> element.getAttribute("class").contains("ql-selected"))
                .map(element -> element.getAttribute("data-value"))
                .findFirst()
                .orElse(null);
    }

    public List<String> getAvailableSizes() {
        openDropdown();
        return sizeOptions.stream()
                .map(el -> el.getAttribute("data-value"))
                .toList();
    }

    public boolean isDropdownOpen() {
        String classes = selectSizePicker.getAttribute("class");
        return classes != null && classes.contains("ql-expanded");
    }

    public TextSizeDropdownComponent resetToDefault() {
        openDropdown();
        sizeOptions.stream()
                .filter(option -> "normal".equalsIgnoreCase(option.getAttribute("data-value")))
                .findFirst()
                .ifPresent(WebElement::click);
        return this;
    }




}
