package com.greencity.ui.pages;

import com.greencity.ui.components.buttons.EditNewsButtonsComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditEcoNewsPage extends BasePage {

    private final EditNewsButtonsComponent editNewsButtonsComponent;
    @Getter
    @FindBy(css = "div.submit-buttons")
    private WebElement editNewsButtonsContainer;

    public EditEcoNewsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

        editNewsButtonsComponent = new EditNewsButtonsComponent(driver, editNewsButtonsContainer);
    }

    public EditNewsButtonsComponent getEditNewsButtons() {
        return editNewsButtonsComponent;
    }
}
