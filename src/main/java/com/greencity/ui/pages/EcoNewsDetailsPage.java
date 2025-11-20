package com.greencity.ui.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class EcoNewsDetailsPage extends BasePage {

    @Getter
    private static final String AUTHOR_NEWS_URL = "https://www.greencity.cx.ua/#/greenCity/news/158";
    @FindBy(css = "div.edit-news")
    private WebElement editNewsButton;

    @Getter
    @FindBy(xpath = "//div[@class='back-button']/a")
    private WebElement backButton;

    @Getter
    @FindBy(xpath = "//div[@class='tags']/div")
    private List<WebElement> allTagsElements;

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

    public EcoNewsDetailsPage(WebDriver driver) {
        super(driver);
    }

    public EditEcoNewsPage openEditNewsPage() {
        driver.get(AUTHOR_NEWS_URL);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(editNewsButton));
        editNewsButton.click();
        return new EditEcoNewsPage(driver);
    }

    public EcoNewsPage clickBackButton() {
        this.backButton.click();
        return new EcoNewsPage(driver);
    }

    public String getAuthorName(){
        return authorInfoElement.getText();
    }

    public Integer getNumberOfLikes() {
        return Integer.parseInt(numbersOfLikesElement.getText());
    }

    public String getTitle(){
        return titleElement.getText();
    }

    public String getDateInfo(){
        return dateInfoElement.getText();
    }


}
