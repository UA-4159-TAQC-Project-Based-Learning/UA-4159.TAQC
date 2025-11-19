package com.greencity.ui.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditNewsPage extends BasePage {

    public EditNewsPage(WebDriver driver) {
        super(driver);
    }

    @Getter
    private static final String EDIT_NEWS_LINK_XPATH = "//div[contains(text(), ' Edit news ')]";

    public EditNewsPage openEditEcoNewsPage() {
        WebElement editNewsButton = driver.findElement(By.xpath(EDIT_NEWS_LINK_XPATH));
        editNewsButton.click();
        return new EditNewsPage(driver);
    }

}
