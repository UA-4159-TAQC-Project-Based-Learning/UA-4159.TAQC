package com.greencity.ui.pages;

import com.greencity.ui.components.EcoNewsListCardComponent;
import com.greencity.ui.components.eco_news.EcoNewsTableCardComponent;
import com.greencity.ui.components.newsFilter.NewsFilterComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EcoNewsPage extends BasePage {

    @Getter
    public NewsFilterComponent newsFilterComponent;
    protected EcoNewsListCardComponent ecoNewsListCardComponent;
    @FindBy(css = "div.ul-eco-buttons")
    private WebElement newsFilterContainer;
    @FindBy(css = "div.list-gallery")
    private WebElement randomNewsCard;
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

    @FindBy(xpath = "//ul[contains(@class, 'gallery-view-active')]/li")
    private List<WebElement> tableCardsElements;

    @Getter
    @FindBy(xpath = "//div[contains(text(), ' Edit news ')]")
    private WebElement editNewsLink;

    @FindBy(css = ".eco-news_list-view-wrp")
    private List<WebElement> ecoNewsListRoots;

    @Getter
    @FindBy(css = "div.title-list.word-wrap")
    private WebElement randomNewsTitle;

    public EcoNewsPage(WebDriver driver) {
        super(driver);

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(newsFilterContainer));

        newsFilterComponent = new NewsFilterComponent(driver, newsFilterContainer);
    }

    @Step("Click 'Create News' button")
    public CreateNewsPage clickCreateNews() {
        createNewsButton.click();
        return new CreateNewsPage(driver);
    }

    @Step("Search for news: '{searchText}'")
    public EcoNewsPage searchForNews(String searchText) {
        searchButton.click();
        searchField.sendKeys(searchText);
        return this;
    }

    @Step("Open bookmarks section")
    public EcoNewsPage openBookmarks() {
        bookmarkButton.click();
        return this;
    }

    @Step("Get all list view news cards")
    public List<EcoNewsListCardComponent> getAllCards() {
        return ecoNewsListRoots.stream().map(root -> new EcoNewsListCardComponent(driver, root)).collect(Collectors.toList());
    }

    @Step("Open 'My Events' section")
    public EcoNewsPage openMyEvents() {
        myEventsButton.click();
        return this;
    }

    @Step("Find news card by title: '{title}'")
    public EcoNewsListCardComponent findCardByTitle(String title) {
        return getAllCards().stream().filter(card -> card.getTitleText().equalsIgnoreCase(title)).findFirst().orElse(null);
    }

    @Step("Close search")
    public EcoNewsPage closeSearch() {
        cancelSearchButton.click();
        return this;
    }

    @Step("Get all table view news cards")
    public List<EcoNewsTableCardComponent> getAllTableCards() {
        List<EcoNewsTableCardComponent> tableCards = new ArrayList<>();
        for (WebElement element : tableCardsElements) {
            EcoNewsTableCardComponent item = new EcoNewsTableCardComponent(driver, element);
            tableCards.add(item);
        }
        return tableCards;
    }

    @Step("Get table card by title: '{title}'")
    public EcoNewsTableCardComponent getOneTableCardByTitle(String title) {
        List<EcoNewsTableCardComponent> listOfTableCards = this.getAllTableCards();
        for (EcoNewsTableCardComponent item : listOfTableCards) {
            if (title.equalsIgnoreCase(item.getTitle())) {
                return item;
            }
        }
        return null;
    }


    @Step("Check if Eco News Page is opened")
    public boolean EcoNewsPageIsOpened() {
        try {
            return randomNewsTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
