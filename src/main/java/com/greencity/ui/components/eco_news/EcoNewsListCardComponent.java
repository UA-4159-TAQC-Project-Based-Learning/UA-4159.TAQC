package com.greencity.ui.components.eco_news;

import com.greencity.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EcoNewsListCardComponent extends BaseComponent {

    @Getter
    @FindBy(css = ".filter-tag")
    private WebElement tag;

    @Getter
    @FindBy(css = ".eco-news_list-content-title")
    private WebElement title;

    @Getter
    @FindBy(css = ".eco-news_list-content-text")
    private WebElement description;

    @Getter
    @FindBy(css = ".eco-news_data-text-date")
    private WebElement date;

    @FindBy(css = ".eco-news_person")
    private WebElement author;

    @Getter
    @FindBy(css = ".button-news-card button")
    private WebElement moreButton;

    @FindBy(css = ".eco-news_list-img")
    private WebElement newsImage;

    @FindBy(css = ".news-flags")
    private WebElement addToFavoritesButton;




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
        String name = author.getText().trim();
        if (name.isEmpty()){
            return "Anonymous";
        }
        return name;
    }

    public String getDateText() {
        return date.getText().trim();
    }

    public String getCategoriesText() {
        return tag.getText().trim();
    }


    public void setAddToFavoritesButton(){
        addToFavoritesButton.click();
    }

    public void openEcoNews() {
        title.click();
    }

    public void clickMore(){
        moreButton.click();
    }


}
