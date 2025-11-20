package com.greencity.ui.pages;

import com.greencity.ui.components.EcoNewsListCardComponent;
import com.greencity.ui.components.eco_news.EcoNewsTableCardComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    protected EcoNewsListCardComponent ecoNewsListCardComponent;

    @Getter
    @FindBy(id = "create-button")
    private WebElement createNewsButton;

    @FindBy(xpath = "//ul[contains(@class, 'gallery-view-active')]/li")
    private List<WebElement> tableCardsElements;
    @FindBy(css = ".eco-news_list-view-wrp")
    private List<WebElement> ecoNewsListRoots;

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

    public List<EcoNewsListCardComponent> getAllCards() {
        return ecoNewsListRoots.stream()
                .map(root -> new EcoNewsListCardComponent(driver, root))
                .collect(Collectors.toList());
    }

    public EcoNewsPage openMyEvents() {
        myEventsButton.click();
        return this;
    }

    public EcoNewsListCardComponent findCardByTitle(String title) {
        return getAllCards().stream()
                .filter(card -> card.getTitleText().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    public EcoNewsPage closeSearch() {
        cancelSearchButton.click();
        return this;
    }

    public List<EcoNewsTableCardComponent> getAllTableCards() {
        List<EcoNewsTableCardComponent> tableCards = new ArrayList<>();
        for (WebElement element : tableCardsElements) {
            EcoNewsTableCardComponent item = new EcoNewsTableCardComponent(driver, element);
            tableCards.add(item);
        }
        return tableCards;
    }

    public EcoNewsTableCardComponent getOneTableCardByTitle(String title) {
        List<EcoNewsTableCardComponent> listOfTableCards = this.getAllTableCards();
        for (EcoNewsTableCardComponent item : listOfTableCards) {
            if (title.equalsIgnoreCase(item.getTitle())) {
                return item;
            }
        }
        return null;
    }
}
