package com.greencity.cucumber.steps;

import com.greencity.data.MandatoryFieldsNewsData;
import com.greencity.ui.components.EcoNewsListCardComponent;
import com.greencity.ui.components.InputFormComponent;
import com.greencity.ui.components.createNews.CancelNewsModal;
import com.greencity.ui.pages.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;

public class SourceFieldValidationStep {
    private final Hooks hooks;
    private CancelNewsModal cancelModal;
    private CreateNewsPage createNewsPage;

    public SourceFieldValidationStep(Hooks hooks) {
        this.hooks = hooks;
    }

    @When("enter {string} into the Source field")
    public void enter_url_into_the_source_field(String url) {
        createNewsPage = new CreateNewsPage(hooks.getDriver());
        InputFormComponent inputFormComponent = createNewsPage
                .getSourceInput()
                .typeText(url);
    }
    @Then("the Source field shows a validation warning")
    public void the_source_field_shows_a_validation_warning() {
        hooks.getSoftAssert().assertTrue(createNewsPage.getSourceInput().isWarningsForField(),
                "Source input should be invalid for incorrect URL format");
    }

    @Then("the Source field border color is red")
    public void the_source_field_border_color_is_red() {
        String borderColor = createNewsPage.getSourceInput().getBorderColorWhenWarning();
        hooks.getSoftAssert().assertEquals(borderColor, "rgb(255, 0, 0)", "Source border field should be red when invalid URL");
    }

    @Then("the Publish button is disabled")
    public void the_publish_button_is_disabled() {
        hooks.getSoftAssert().assertTrue(createNewsPage.getCreateNewsButtonsComponent().isPublishDisabled(),
        "Source input should be invalid for incorrect URL format");
    }
    @When("clear the Source field")
    public void clear_the_Source_field() {
        createNewsPage = new CreateNewsPage(hooks.getDriver());
        createNewsPage.clearSourceField();
    }

    @Then("published news contains entered source url {string}")
    public void published_news_contains_entered_source_url(String expectedUrl) {
        String expectedTitle = hooks.getTestValueProvider().getProperty("news.title.valid");

        EcoNewsPage ecoNewsPage = new EcoNewsPage(hooks.getDriver());
        ecoNewsPage.refreshPage();
        ecoNewsPage.waitUntilPageLoaded();

        EcoNewsListCardComponent searchResult = ecoNewsPage.findCardByTitleStable(expectedTitle);
        hooks.getSoftAssert().assertNotNull(searchResult, "Published news with title '" + expectedTitle + "' is not found in the news list");
        EcoNewsDetailsPage ecoNewsDetailsPage = searchResult.clickOnCardStable();

        EditEcoNewsPage editEcoNewsPage = ecoNewsDetailsPage.clickEditNewsButton();
        editEcoNewsPage.waitUntilPageLoaded();
        String actualSourceValue = editEcoNewsPage.getSourceValue().trim();
        hooks.getSoftAssert().assertEquals(actualSourceValue, expectedUrl, String.format("Entered URL during creating news is incorrect. Expected: '%s', but found: '%s'", expectedUrl, actualSourceValue));

    }
}
