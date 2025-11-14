package com.greencity.ui.pages;

import com.greencity.ui.components.newsFilter.NewsFilterComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EcoNewsPage extends BasePage {

    public EcoNewsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div.ul-eco-buttons")
    private WebElement filterContainer;

    public NewsFilterComponent getFilterComponent() {
        return new NewsFilterComponent(driver, filterContainer);
    }

    @FindBy(css = "div.title-list.word-wrap")
    private WebElement firstNewsCard;

    public String getFirstNewsTitle() {
        return firstNewsCard.getText().trim();
    }
}
