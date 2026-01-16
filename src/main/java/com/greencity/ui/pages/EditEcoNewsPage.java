package com.greencity.ui.pages;

import com.greencity.ui.components.InputFormComponent;
import com.greencity.ui.components.buttons.EditNewsButtonsComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditEcoNewsPage extends BasePage {
    @Getter
    private final InputFormComponent sourceInput;

    private final EditNewsButtonsComponent editNewsButtonsComponent;
    @Getter
    @FindBy(css = "div.submit-buttons")
    private WebElement editNewsButtonsContainer;

    @Getter
    @FindBy(css = ".source-block")
    private WebElement sourceRoot;

    public EditEcoNewsPage(WebDriver driver) {
        super(driver);
        this.sourceInput = new InputFormComponent(driver, sourceRoot);
        editNewsButtonsComponent = new EditNewsButtonsComponent(driver, editNewsButtonsContainer);
    }

    public EditNewsButtonsComponent getEditNewsButtons() {
        return editNewsButtonsComponent;
    }

    @Step("Get value from Source input field")
    public String getSourceValue() {
        return sourceInput.getValue();
    }

}
