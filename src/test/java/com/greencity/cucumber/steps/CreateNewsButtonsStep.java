package com.greencity.cucumber.steps;

import com.greencity.data.MandatoryFieldsNewsData;
import com.greencity.ui.components.createNews.CancelNewsModal;
import com.greencity.ui.pages.CreateNewsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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
        createNewsPage.waitUntilPageLouder();
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
                String.format("Title in cancel dialog modal is '%s' that doesn't matched expected one: '%s'",
                        actualText, expectedText
                )
        );
    }
    @Then("the dialog should contain a 'Continue editing' button, a 'Yes, cancel' button and a close icon")
    public void the_dialog_should_contain_a_сontinue_editing_button_a_yes_cancel_button_and_a_close_icon() {
        java.util.List<String> missing = cancelModal.getMissingControls();
        hooks.getSoftAssert().assertTrue(
                missing.isEmpty(),
                "Dialog modal buttons are not all visible: " + missing
        );
    }

}
