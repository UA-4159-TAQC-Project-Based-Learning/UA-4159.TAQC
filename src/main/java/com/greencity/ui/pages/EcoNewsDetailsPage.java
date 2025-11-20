package com.greencity.ui.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EcoNewsDetailsPage extends BasePage {

    @Getter
    private static final String AUTHOR_NEWS_URL = "https://www.greencity.cx.ua/#/greenCity/news/158";
    @FindBy(css = "div.edit-news")
    private WebElement editNewsButton;


    public EcoNewsDetailsPage(WebDriver driver) {
        super(driver);
    }

    public EditEcoNewsPage openEditNewsPage() {
        driver.get(AUTHOR_NEWS_URL);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(editNewsButton));
        editNewsButton.click();
        return new EditEcoNewsPage(driver);
    }

}
