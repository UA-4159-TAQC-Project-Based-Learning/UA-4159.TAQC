package com.greencity.ui.pages.ubsPage;

import com.greencity.ui.pages.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UbsPage extends BasePage {
    @Getter
    @FindBy(xpath = ".//h1")
    private WebElement headerBannerUbsTitle;

    public UbsPage(WebDriver driver) {
        super(driver);
    }
}
