package com.greencity.ui.components.header.actions;

import com.greencity.ui.elements.BaseElement;
import com.greencity.ui.utils.Language;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LanguageSwitcher extends BaseElement {

    @Getter
    @FindBy(css = "[aria-label='language switcher']")
    private WebElement languageToggle;

    public LanguageSwitcher(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    /**
     *
     * @param language - from Language Enum
     */
    @Step("Switch language to {language}")
    public void selectLanguage(Language language) {
        selectLanguage(language.label());
    }

    /**
     *
     * @param label - the language label exactly as displayed on the website
     *              e.g. "En" for English,
     *              "Uk" for Ukrainian
     */
    @Step("Switch language to '{label}'")
    public void selectLanguage(String label) {
        clickDynamicElement(languageToggle);

        WebElement option = rootElement.findElement(
                By.xpath(".//span[normalize-space()='" + label + "']"));
        clickDynamicElement(option);
    }
}
