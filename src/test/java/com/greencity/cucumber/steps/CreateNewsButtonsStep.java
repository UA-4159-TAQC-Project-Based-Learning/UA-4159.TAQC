package com.greencity.cucumber.steps;

import com.greencity.data.MandatoryFieldsNewsData;
import com.greencity.ui.components.createNews.CancelNewsModal;
import com.greencity.ui.pages.CreateNewsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

public class CreateNewsButtonsStep {
    private Hooks hooks;
    private CancelNewsModal cancelModal;

    public CreateNewsButtonsStep(Hooks hooks) {this.hooks = hooks;}


    @Given("all required fields for the news draft are filled with valid data")
    public void all_required_fields_for_the_news_draft_are_filled_with_valid_data() {
        CreateNewsPage createNewsPage = new CreateNewsPage(hooks.getDriver());
        MandatoryFieldsNewsData newsData = hooks.getTestValueProvider().getValidMandatoryFieldsNewsData();

        createNewsPage.fillMandatoryFields(
                newsData.getTitle(),
                newsData.getTags(),
                newsData.getContentText()
        );
    }

    @When("click the Cancel button")
    public void click_the_cancel_button() {
        CreateNewsPage createNewsPage = new CreateNewsPage(hooks.getDriver());
        createNewsPage.waitUntilPageLoaded();
        cancelModal = createNewsPage.openCancelModal();
    }

    @Then("a confirmation dialog should be visible")
    public void a_confirmation_dialog_should_be_visible() {
        boolean isVisible = cancelModal.isVisible();
        hooks.getSoftAssert().assertTrue(isVisible, "Confirmation dialog modal is not visible");
    }

    @Then("the dialog title should indicate a warning about cancelling changes")
    public void the_dialog_title_should_indicate_a_warning_about_cancelling_changes() {
        String expectedText = "All created content will be lost.";
        String actualText = cancelModal.getModalTitleText();
        hooks.getSoftAssert().assertEquals(actualText, expectedText,
                String.format("Title in cancel dialog modal is '%s' that doesn't match expected one: '%s'",
                        actualText, expectedText
                )
        );
    }
    @Then("the cancel news dialog should contain all buttons")
    public void the_cancel_news_dialog_should_contain_all_buttons() {
        List<String> missingButtons = new ArrayList<>();

        if (!cancelModal.isContinueEditingVisible()) {
            missingButtons.add("'Continue editing' button");
        }
        if (!cancelModal.isYesCancelVisible()) {
            missingButtons.add("'Yes, cancel' button");
        }
        if (!cancelModal.isCloseIconVisible()) {
            missingButtons.add("Close icon");
        }

        hooks.getSoftAssert().assertTrue(missingButtons.isEmpty(), "Some buttons are not visible in Cancel modal: " + missingButtons);
    }

    @When("click the Continue editing button")
    public void click_the_continue_editing_button() {
        cancelModal.clickContinueEditingButton();
    }

    @Then("the confirmation dialog should be closed")
    public void the_confirmation_dialog_should_be_closed() {
        hooks.getSoftAssert().assertFalse(cancelModal.isVisible(), "Confirmation dialog should not be visible");
    }


}
