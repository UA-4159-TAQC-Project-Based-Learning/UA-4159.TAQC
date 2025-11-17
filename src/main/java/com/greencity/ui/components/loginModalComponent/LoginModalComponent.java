package com.greencity.ui.components.loginModalComponent;

import com.greencity.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginModalComponent extends BaseComponent {

    public LoginModalComponent(WebDriver driver, WebElement loginModalRoot) {
        super(driver, loginModalRoot);
    }

}
