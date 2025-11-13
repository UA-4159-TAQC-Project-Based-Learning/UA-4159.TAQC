package com.greencity.ui.components.eco_news;

import com.greencity.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EconewsTableCardComponent extends BaseComponent {

    @Getter
    @FindBy(css = ".news-flags.favourite-button")
    private WebElement favoriteBtn;

    @Getter
    @FindBy(css = ".list-image-content")
    private WebElement imageElement;

    @Getter
    @FindBy(xpath = "//div[@class='list-gallery-content']//div[@class='filter-tag']")
    private WebElement headerLinkElement;

    @Getter
    @FindBy(css = ".title-list")
    private WebElement titleElement;

    @Getter
    @FindBy(css = ".list-text")
    private WebElement textElement;

    @Getter
    @FindBy(xpath = "//div[@class='user-data']")
    private WebElement userDataInfoElement;

    @Getter
    @FindBy(xpath = "//p[@class='user-data-like']/img[@alt='comments']/following-sibling::span[@class='numerosity']")
    private WebElement comentsCounterElement;

    @Getter
    @FindBy(xpath = "//p[@class='user-data-like']/img[@alt='likes']/following-sibling::span[@class='numerosity']")
    private WebElement likesCounterElement;


    public EconewsTableCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void goToDetails() {
        headerLinkElement.click();
    }

    public void addToFavorite() {
        favoriteBtn.click();
    }

}
