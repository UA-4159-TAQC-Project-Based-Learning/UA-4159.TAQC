package com.greencity.ui.pages;

import com.greencity.ui.components.InputFormComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;


public class CreateNewsPage extends BasePage {

    @Getter
    @FindBy(css = ".title-block")
    private InputFormComponent titleInput;

    @Getter
    @FindBy(css = ".source-block")
    private InputFormComponent sourceInput;

    @Getter
    @FindBy (css = ".textarea-wrapper")
    private InputFormComponent contentEditor;

    public CreateNewsPage(WebDriver driver) {
        super(driver);
    }


}