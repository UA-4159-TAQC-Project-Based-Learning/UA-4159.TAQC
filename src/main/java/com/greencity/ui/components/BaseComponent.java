package com.greencity.ui.components;

import com.greencity.ui.Base;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public abstract class BaseComponent extends Base {

    @Getter
    protected WebElement rootElement;

    public BaseComponent(WebDriver driver, WebElement rootElement) {
        super(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);
        this.rootElement = rootElement;
    }
}