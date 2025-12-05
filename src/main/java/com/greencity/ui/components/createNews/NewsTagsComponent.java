package com.greencity.ui.components.createNews;

import com.greencity.ui.components.BaseComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class NewsTagsComponent extends BaseComponent {

    @Getter
    @FindBy(css = "a.global-tag")
    private List<WebElement> tagButtons;

    @Getter
    @FindBy(css = ".tags-block h3")
    private List<WebElement> tagsSectionTitle;

    @Getter
    @FindBy(css = ".tags-block p")
    private List<WebElement> tagsValidationMessage;

    public NewsTagsComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Select tag '{tagName}'")
    public void selectTag(String tagName) {
        for (WebElement tag : tagButtons) {
            if (tag.getText().trim().equalsIgnoreCase(tagName)) {
                tag.click();
                return;
            }
        }
    }

    @Step("Unselect tag '{tagName}'")
    public void unselectTag(String tagName) {
        for (WebElement tag : tagButtons) {
            if (tag.getText().trim().equalsIgnoreCase(tagName)
                    && tag.getAttribute("class").contains("global-tag-clicked")) {
                tag.click();
                return;
            }
        }
    }


    @Step("Check if tag '{tagName}' is selected")
    public boolean isTagSelected(String tagName) {
        for (WebElement tag : tagButtons) {
            if (tag.getText().trim().equalsIgnoreCase(tagName)) {
                return tag.getAttribute("class").contains("global-tag-clicked");
            }
        }
        return false;
    }

    @Step("Get list of all tags")
    public List<String> getAllTags() {
        List<String> tags = new ArrayList<>();
        for (WebElement tag : tagButtons) {
            tags.add(tag.getText());
        }
        return tags;
    }

    @Step("Get all selected tags")
    public List<String> getSelectedTags() {
        List<String> selected = new ArrayList<>();
        for (WebElement tag : tagButtons) {
            if (tag.getAttribute("class").contains("global-tag-clicked")) {
                selected.add(tag.getText());
            }
        }
        return selected;
    }




}
