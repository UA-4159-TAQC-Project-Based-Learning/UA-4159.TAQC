package com.greencity.ui.pages;

import com.greencity.ui.components.eco_news.EcoNewsTableCardComponent;
import com.greencity.ui.components.eco_news.NewsTagsInfoComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class EcoNewsDetailsPage extends BasePage {


    @FindBy(css = "div.edit-news")
    private WebElement editNewsButton;

    @Getter
    @FindBy(xpath = "//div[@class='back-button']/a")
    private WebElement backButton;

    @Getter
    @FindBy(css = "div.tags")
    private WebElement tagsRoot;

    @Getter
    @FindBy(css = "div.news-title")
    private WebElement titleElement;

    @Getter
    @FindBy(css = "div.news-info-date")
    private WebElement dateInfoElement;

    @Getter
    @FindBy(css = "div.news-info-author")
    private WebElement authorInfoElement;

    @Getter
    @FindBy(css = "img.news_like")
    private WebElement likeImgElement;

    @Getter
    @FindBy(css = "span.numerosity_likes")
    private WebElement numbersOfLikesElement;

    @Getter
    @FindBy(css = "img.news-image-img")
    private WebElement imageElement;

    @Getter
    @FindBy(css = "div.news-text")
    private WebElement newsTextElement;

    @Getter
    @FindBy(xpath = "//a/img[@alt='twitter']")
    private WebElement twitterLinkElement;

    @Getter
    @FindBy(xpath = "//a/img[@alt='linkedin']")
    private WebElement linkedinLinkElement;

    @Getter
    @FindBy(xpath = "//a/img[@alt='facebook']")
    private WebElement facebookLinkElement;

    @Getter
    @FindBy(css = "div.list-gallery")
    private List<WebElement> recommendedNewsElements;

    @Getter
    @FindBy(id = "total-count")
    private WebElement totalCountCommentElement;

    @Getter
    @FindBy(css = "div.comment-textarea")
    private WebElement commentInputElement;

    @Getter
    @FindBy(css = "app-add-comment .primary-global-button")
    private WebElement commentButtonElement;

    public EcoNewsDetailsPage(WebDriver driver) {
        super(driver);

//        newsTagsInfoComponent = new NewsTagsInfoComponent(driver, tagsRoot);
    }

    public NewsTagsInfoComponent getNewsTagsInfoComponent() {
        return new NewsTagsInfoComponent(driver, tagsRoot);
    }

    public EcoNewsPage clickBackButton() {
        this.backButton.click();
        return new EcoNewsPage(driver);
    }

    @Step("Validate author")
    public String getAuthorName() {
        return authorInfoElement.getText();
    }

    public Integer getNumberOfLikes() {
        try {
            return Integer.parseInt(numbersOfLikesElement.getText().trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Step("Validate title")
    public String getTitle() {
        return titleElement.getText();
    }

    public String getDateInfo() {
        return dateInfoElement.getText();
    }

    public String getText() {
        return newsTextElement.getText();
    }

    public EditEcoNewsPage clickEditNewsButton() {
        this.editNewsButton.click();
        return new EditEcoNewsPage(driver);
    }

    public List<EcoNewsTableCardComponent> getRecommendedNewsTableCardComponents() {
        List<EcoNewsTableCardComponent> result = new ArrayList<>();
        for (WebElement element : recommendedNewsElements) {
            EcoNewsTableCardComponent card = new EcoNewsTableCardComponent(driver, element);
            result.add(card);
        }
        return result;
    }

    public Integer getNumberOfComments() {
        try {
            return Integer.parseInt(totalCountCommentElement.getText().trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public EcoNewsDetailsPage clickCommentInputElementButton() {
        this.commentInputElement.click();
        return this;
    }

    public EcoNewsDetailsPage clickCommentButtonElement() {
        this.commentButtonElement.click();
        return this;
    }
}
