package com.greencity.ui.components;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EcoNewsListCardComponent extends BaseComponent{

    @Getter

    @FindBy(css = ".filter-tag")
    private WebElement tag;

    @FindBy(css = ".title-list")
    private WebElement title;

    @FindBy(css = ".list-text")
    private WebElement description;

    @FindBy(css = ".user-data-text-date:not(.user)")
    private WebElement date;

    @FindBy(css = ".user-data-text-date.user")
    private WebElement author;

    @FindBy(xpath = "//p[contains(@class, 'user-data-like')][img[@alt='comments']]")
    private WebElement commentsCounter;

    @FindBy(xpath = "//p[contains(@class, 'user-data-like')][img[@alt='likes']]")
    private WebElement likesCounter;

    @FindBy(css = ".list-image-content")
    private WebElement newsImage;

    @FindBy(css = ".news-flags.favourite-button")
    private WebElement addToFavorites;



    public EcoNewsListCardComponent (WebDriver driver, WebElement rootElement){
        super(driver, rootElement);
    }


    public String getTitleText() {
        return title.getText().trim();
    }

    public String getDescriptionText() {
        return description.getText().trim();
    }

    public String getAuthorText() {
        return author.getText().trim();
    }

    public String getDateText() {
        return date.getText().trim();
    }

    public String getCategoriesText() {
        return tag.getText().trim();
    }

    public int getCommentsCountValue() {
        return Integer.parseInt(commentsCounter.getText().trim());
    }

    public int getLikesCountValue() {
        return Integer.parseInt(likesCounter.getText().trim());
    }

    public void openEcoNews() {
        title.click();
    }

    public void saveToFavorites() {
        addToFavorites.click();
    }

}
