package com.greencity.ui.components.newsFilter;

import com.greencity.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Optional;


public class NewsFilterComponent extends BaseComponent {
    @Getter
    @FindBy(xpath = ".//a[contains(@class,'global-tag')]")
    private List<WebElement> filterButtons;

    public NewsFilterComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public boolean clickFilterByName(FilterName filterName) {
        return findFilterButton(filterName)
                .map(button -> {
                    button.click();
                    return true;
                })
                .orElse(false);
    }

    public boolean areAllFiltersVisible() {
        return filterButtons != null
                && !filterButtons.isEmpty()
                && filterButtons.stream().allMatch(WebElement::isDisplayed);
    }

    private Optional<WebElement> findFilterButton(FilterName filterName) {
        String expected = filterName.getValue().trim().toLowerCase();

        return filterButtons.stream()
                .filter(button -> {
                    WebElement span = button.findElement(By.tagName("span"));
                    return span.getText().trim().toLowerCase().equals(expected);
                })
                .findFirst();
    }

    public boolean isFilterActive(FilterName filterName) {
        return findFilterButton(filterName)
                .map(button -> {
                    String classAttr = button.getAttribute("class");
                    return classAttr != null && classAttr.contains("global-tag-clicked");
                })
                .orElse(false);
    }

    public NewsFilterComponent resetFilters() {
        for (WebElement button : filterButtons) {
            String classAttr = button.getAttribute("class");
            if (classAttr != null && classAttr.contains("global-tag-clicked")) {
                button.click();
            }
        }
        return this;
    }
}