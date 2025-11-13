package com.greencity.ui.components.newsFilter;

import com.greencity.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class NewsFilterComponent extends BaseComponent {

    @FindBy(xpath = ".//div[@aria-label='filter by items']//button//span")
    private List<WebElement> filterButtons;

    public NewsFilterComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickFilterByName(String name) {
        for (WebElement button : filterButtons) {
            if (button.getText().trim().equalsIgnoreCase(name)) {
                //if (button.getText().trim().toLowerCase().contains(name.toLowerCase()))
                button.click();
                return;
            }
        }
        throw new RuntimeException("No filter button found with name: " + name);
    }

    public boolean areAllFiltersVisible() {
        return filterButtons.stream().allMatch(WebElement::isDisplayed);
    }

    public boolean isFilterActive(String name) {
        for (WebElement button : filterButtons) {
            if (button.getText().trim().equalsIgnoreCase(name)) {
                return button.getAttribute("class").contains("active");
            }
        }
        return false;
    }

}


//    @Getter
//    @FindBy(xpath = ".//span[contains(text(),'News')]")
//    private WebElement newsFilter;
//
//    @Getter
//    @FindBy(xpath = ".//span[contains(text(),'Events')]")
//    private WebElement eventsFilter;
//
//    @Getter
//    @FindBy(xpath = ".//span[contains(text(),'Education')]")
//    private WebElement educationFilter;
//
//    @Getter
//    @FindBy(xpath = ".//span[contains(text(),'Initiatives')]")
//    private WebElement initiativesFilter;
//
//    @Getter
//    @FindBy(xpath = ".//span[contains(text(),'Ads')]")
//    private WebElement adsFilter;
//
//    public void clickNews() {
//        newsFilter.click(); }
//
//    public void clickEvents() {
//        eventsFilter.click(); }
//
//    public void clickEducation() {
//        educationFilter.click(); }
//
//    public void clickAds() {
//        adsFilter.click(); }
//
//    public void clickInitiatives() {
//        initiativesFilter.click(); }