package com.greencity.ui.components.eco_news;

import com.greencity.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NewsTagsInfoComponent extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//div[contains(@class,'tags-item')]")
    private List<WebElement> allTagsElements;

    public NewsTagsInfoComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public boolean hasTag(String tagName) {
        for (WebElement tag : allTagsElements) {
            if (tag.getText().trim().equals(tagName))
                return true;
        }
        return false;
    }

}
