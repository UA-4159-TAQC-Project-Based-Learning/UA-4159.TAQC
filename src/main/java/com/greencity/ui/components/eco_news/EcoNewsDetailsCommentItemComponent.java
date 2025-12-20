package com.greencity.ui.components.eco_news;

import com.greencity.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EcoNewsDetailsCommentItemComponent extends BaseComponent {

    @Getter
    @FindBy(css = ".author-name")
    private WebElement authorElement;

    @Getter
    @FindBy(css = ".comment-text")
    private WebElement commentTextElement;

    @Getter
    @FindBy(css = "button.delete")
    private WebElement deleteButtonElement;


    public EcoNewsDetailsCommentItemComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getAuthorName() {
        return authorElement.getText();
    }

    public String getCommentText() {
        return commentTextElement.getText();
    }

    public void clickDelete() {
        deleteButtonElement.click();
    }

}
