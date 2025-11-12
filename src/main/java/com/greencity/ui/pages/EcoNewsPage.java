package com.greencity.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EcoNewsPage extends BasePage{

    @FindBy(css = "h1.main-header")
    private WebElement mainHeader;

    //recheck later clickable element
    @FindBy(css = "span.search-img")
    private WebElement searchButton;

    @FindBy(css = ".place-input")
    private WebElement searchField;

    @FindBy(css = ".cross-position")
    private WebElement cancelSearchButton;

    //recheck later clickable element
    @FindBy(css = "span.bookmark-img")
    private WebElement bookmarkButton;

    //recheck later clickable element
    @FindBy(css = "div.container-img")
    private WebElement myEventsButton;

    @FindBy(id = "create-button")
    private WebElement createNewsButton;

    public EcoNewsPage(WebDriver driver) {
        super(driver);
    }

    public CreateNewsPage clickCreateNews() {
        createNewsButton.click();
        return new CreateNewsPage(driver);
    }

    public EcoNewsPage searchForNews(String searchText) {
        searchButton.click();
        searchField.sendKeys(searchText);
        return this;
    }

    public EcoNewsPage openBookmarks() {
        bookmarkButton.click();
        return this;
    }

    public EcoNewsPage openMyEvents() {
        myEventsButton.click();
        return this;
    }

    public EcoNewsPage closeSearch() {
        cancelSearchButton.click();
        return this;
    }

}
