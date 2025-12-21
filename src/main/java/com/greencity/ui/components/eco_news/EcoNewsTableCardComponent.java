package com.greencity.ui.components.eco_news;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.pages.EcoNewsDetailsPage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EcoNewsTableCardComponent extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//div[contains(@class, 'news-flags')]")
    private WebElement favoriteBtn;

    @Getter
    @FindBy(css = ".list-image-content")
    private WebElement imageElement;

    @Getter
    @FindBy(xpath = ".//div[@class='list-gallery-content']//div[@class='filter-tag']")
    private WebElement headerLinkElement;

    @Getter
    @FindBy(css = ".title-list")
    private WebElement titleElement;

    @Getter
    @FindBy(css = ".list-text")
    private WebElement textElement;

    @Getter
    @FindBy(xpath = ".//div[@class='user-data']")
    private WebElement userDataInfoElement;

    @Getter
    @FindBy(xpath = "//div[@class='user-data-added-news']/p/img[contains(@src, 'calendar')]/following-sibling::span")
    private WebElement createdDateElement;

    @Getter
    @FindBy(xpath = "//div[@class='user-data-added-news']/p/img[contains(@src, 'profile')]/following-sibling::span")
    private WebElement authorElement;

    @Getter
    @FindBy(xpath = ".//p[@class='user-data-like']/img[@alt='comments']/following-sibling::span[@class='numerosity']")
    private WebElement commentsCounterElement;

    @Getter
    @FindBy(xpath = ".//p[@class='user-data-like']/img[@alt='likes']/following-sibling::span[@class='numerosity']")
    private WebElement likesCounterElement;

    public EcoNewsTableCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Go to eco news details page from table card")
    public EcoNewsDetailsPage goToDetails() {
        headerLinkElement.click();
        return new EcoNewsDetailsPage(driver);
    }

    public EcoNewsTableCardComponent addToFavorite() {
        favoriteBtn.click();
        return this;
    }

    public String getDateCreated() {
        return createdDateElement.getText();
    }

    public String getAuthor() {
        return authorElement.getText();
    }

    public String getText() {
        return textElement.getText();
    }

    public String getTitle() {
        return titleElement.getText().trim();
    }

}
