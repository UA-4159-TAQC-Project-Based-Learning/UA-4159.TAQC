package com.greencity.ui.pages;

import com.greencity.ui.components.InputFormComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewsPage extends BasePage {

    @FindBy(css = ".title-block")
    private WebElement titleBlock;

    @FindBy(css = ".source-block")
    private WebElement sourceBlock;

    @Getter
    private final InputFormComponent titleInput;

    @Getter
    private final InputFormComponent sourceInput;

    public CreateNewsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.titleInput = new InputFormComponent(driver, titleBlock);
        this.sourceInput = new InputFormComponent(driver, sourceBlock);
    }


}