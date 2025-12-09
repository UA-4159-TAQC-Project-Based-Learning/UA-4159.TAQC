package com.greencity.cucumber.steps;

import com.greencity.ui.components.header.core.HeaderComponent;
import com.greencity.ui.pages.BasePage;
import com.greencity.ui.pages.homepage.HomePage;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeaderStep {
    private Hooks hooks;

    public HeaderStep(Hooks hooks) {
        this.hooks = hooks;
    }

    @Given("the user is logged in the system")
    public void the_user_opens_the_create_news_page() {
        new HomePage(hooks.getDriver())
                .getHeader()
                .clickSignIn()
                .typeEmail(hooks.getTestValueProvider().getUserEmail())
                .typePassword(hooks.getTestValueProvider().getUserPassword())
                .submit();


        new WebDriverWait(hooks.getDriver(), Duration.ofSeconds(10))
                .until(d -> {
                    WebElement freshHeaderRoot = d.findElement(By.xpath(BasePage.getHEADER_ROOT_LOCATOR_XPATH()));
                    HeaderComponent freshHeader = new HeaderComponent(d, freshHeaderRoot);
                    return freshHeader.isLoggedIn();
                });
    }
}
