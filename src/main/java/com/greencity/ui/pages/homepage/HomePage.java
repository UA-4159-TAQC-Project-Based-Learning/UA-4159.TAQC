package com.greencity.ui.pages.homepage;

import com.greencity.ui.pages.BasePage;
import com.greencity.ui.pages.EcoNewsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public EcoNewsPage openEcoNewsPage() {
        WebElement ecoNewsLink = driver.findElement(By.xpath("//a[@routerlink='/greenCity/news']"));
        ecoNewsLink.click();
        return new EcoNewsPage(driver);
    }

}