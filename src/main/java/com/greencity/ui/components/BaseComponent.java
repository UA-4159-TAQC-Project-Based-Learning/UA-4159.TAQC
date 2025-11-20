package com.greencity.ui.components;

import com.greencity.ui.Base;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BaseComponent extends Base {

    @Getter
    protected WebElement rootElement;
    protected WebDriver driver;

    public BaseComponent(WebDriver driver, WebElement rootElement) {
        super(driver);
        this.rootElement = rootElement;
    }

    public BaseComponent(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}