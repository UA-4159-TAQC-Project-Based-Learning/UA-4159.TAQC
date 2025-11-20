package com.greencity.ui.components.buttons;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.pages.ecoNewsPreviewPage.PreviewEcoNewsPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class EditNewsButtonsComponent extends BaseComponent {

    @Getter
    @FindBy(css = "button.primary-global-button")
    private WebElement saveChangesButton;

    @Getter
    @FindBy(css = "button.tertiary-global-button")
    private WebElement cancelChangesButton;

    @Getter
    @FindBy(css = "button.secondary-global-button")
    private WebElement previewNewsButton;

    public EditNewsButtonsComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);

    }
    public PreviewEcoNewsPage clickPreview() {
        previewNewsButton.click();
        return new PreviewEcoNewsPage(driver);
    }
}
