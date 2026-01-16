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
    public void the_user_is_logged_in_the_system() {
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

    @Given("the user clicks on {string} in the header menu")
    public void the_user_clicks_on_in_the_header(String menuItem) {
        if ("Eco News".equalsIgnoreCase(menuItem)) {
            new HomePage(hooks.getDriver())
                    .getEcoNewsLink()
                    .click();
        } else {
            throw new IllegalArgumentException(menuItem + " is not supported");
        }
    }
}
