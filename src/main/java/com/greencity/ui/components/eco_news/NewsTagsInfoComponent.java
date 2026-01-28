package com.greencity.ui.components.eco_news;

import com.greencity.ui.components.BaseComponent;
import io.qameta.allure.Step;
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

    @Step("Check if tag '{tagName}' is present")
    public boolean hasTag(String tagName) {
        for (WebElement tag : allTagsElements) {
            if (tag.getText().trim().equals(tagName))
                return true;
        }
        return false;
    }

}
