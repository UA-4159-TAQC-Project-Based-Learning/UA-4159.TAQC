package com.greencity.ui.components.newsFilter;

import com.greencity.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class NewsFilterComponent extends BaseComponent {
    @Getter
    @FindBy(xpath = ".//a[contains(@class,'global-tag')]")
    private List<WebElement> filterButtons;

    public NewsFilterComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public NewsFilterComponent clickFilterByName(FilterName filterName) {
        for (WebElement button : filterButtons) {
            WebElement span = button.findElement(By.xpath(".//span"));
            if (span.getText().trim().equalsIgnoreCase(filterName.getValue())) {
                button.click();
                return this;
            }
        }
        return this;
    }

    public boolean areAllFiltersVisible() {
        return filterButtons.stream().allMatch(WebElement::isDisplayed);
    }

    public boolean isFilterActive(FilterName filterName) {
        for (WebElement button : filterButtons) {
            WebElement span = button.findElement(By.xpath(".//span"));
            if (span.getText().trim().equalsIgnoreCase(filterName.getValue())) {
                return button.getAttribute("class").contains("global-tag-clicked");
            }
        }
        return false;
    }

    public NewsFilterComponent resetFilters() {
        for (WebElement button : filterButtons) {
            if (button.getAttribute("class").contains("global-tag-clicked")) {
                button.click();
            }
        }
        return this;
    }
}