package com.greencity.ui.pages.homepage;

import com.greencity.ui.pages.BasePage;
import com.greencity.ui.pages.EcoNewsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    private static final String ECO_NEWS_LINK_XPATH = "//a[contains(@href, '#/greenCity/news')]";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public EcoNewsPage openEcoNewsPage() {
        WebElement ecoNewsLink = driver.findElement(By.xpath(ECO_NEWS_LINK_XPATH));
        ecoNewsLink.click();
        return new EcoNewsPage(driver);
    }
}