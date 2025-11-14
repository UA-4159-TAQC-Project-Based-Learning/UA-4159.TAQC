package com.greencity.ui.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EcoNewsPage extends BasePage {

    @Getter
    @FindBy(css = "h1.main-header")
    private WebElement mainHeader;

    @Getter
    @FindBy(css = "div.container-img:has(.search-img)")
    private WebElement searchButton;

    @Getter
    @FindBy(css = ".place-input")
    private WebElement searchField;

    @Getter
    @FindBy(css = ".cross-position")
    private WebElement cancelSearchButton;

    @Getter
    @FindBy(css = "div.container-img:has(.bookmark-img)")
    private WebElement bookmarkButton;

    @Getter
    @FindBy(css = "div.container-img:has(.my-events-img)")
    private WebElement myEventsButton;

    @Getter
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
