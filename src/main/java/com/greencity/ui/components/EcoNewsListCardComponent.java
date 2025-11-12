package com.greencity.ui.components;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EcoNewsListCardComponent extends BaseComponent{

    @Getter
    protected WebElement rootElement;

    public EcoNewsListCardComponent (WebDriver driver, WebElement rootElement){
        super(driver, rootElement);
    }

}
