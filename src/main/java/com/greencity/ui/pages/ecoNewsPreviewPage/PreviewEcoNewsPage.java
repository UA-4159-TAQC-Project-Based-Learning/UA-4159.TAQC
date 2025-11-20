package com.greencity.ui.pages.ecoNewsPreviewPage;

import com.greencity.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PreviewEcoNewsPage extends BasePage {

    public PreviewEcoNewsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isOpened() {
        return true;
    }
}
