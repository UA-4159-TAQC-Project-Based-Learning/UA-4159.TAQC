package com.greencity.ui.pages.ubsPage;

import com.greencity.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UbsPage extends BasePage {
    //root

    @FindBy(xpath = ".//h1")
    private WebElement headerBannerTitle;

    public UbsPage(WebDriver driver) {
        super(driver);
    }
}
