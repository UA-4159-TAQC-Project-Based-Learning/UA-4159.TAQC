package com.greencity.cucumber.steps;

import com.greencity.data.MandatoryFieldsNewsData;
import com.greencity.ui.components.createNews.CancelNewsModal;
import com.greencity.ui.pages.CreateNewsPage;
import com.greencity.ui.pages.EcoNewsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CreateNewsButtonsStep {
    private Hooks hooks;
    private CancelNewsModal cancelModal;
    private CreateNewsPage createNewsPage;

    public CreateNewsButtonsStep(Hooks hooks) {this.hooks = hooks;}


    @Given("all required fields for the news draft are filled with valid data")
    public void all_required_fields_for_the_news_draft_are_filled_with_valid_data() {
        createNewsPage = new CreateNewsPage(hooks.getDriver());
        MandatoryFieldsNewsData newsData = hooks.getTestValueProvider().getValidMandatoryFieldsNewsData();

        createNewsPage.fillMandatoryFields(
                newsData.getTitle(),
                newsData.getTags(),
                newsData.getContentText()
        );
    }

    @Given("a confirmation dialog is visible")
    public void a_confirmation_dialog_is_visible() {
        //CreateNewsPage createNewsPage = new CreateNewsPage(hooks.getDriver());
        cancelModal = createNewsPage.openCancelModal();
        hooks.getSoftAssert().assertTrue(cancelModal.isVisible(), "Confirmation dialog is not visible");
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

    @When("click the Yes cancel button")
    public void click_the_Yes_cancel_button() {
        EcoNewsPage ecoNewsPage = cancelModal.clickYesCancelButton();
        ecoNewsPage.waitUntilPageLouder();
    }

    @Then("the confirmation dialog should be closed")
    public void the_confirmation_dialog_should_be_closed() {
        hooks.getWait().pollingEvery(Duration.ofMillis(200)).until(ExpectedConditions.invisibilityOfElementLocated(CancelNewsModal.getMODAL_ROOT_LOCATOR()));
        boolean isModalInvisible = hooks.getDriver()
                                .findElements(CancelNewsModal.getMODAL_ROOT_LOCATOR())
                                .isEmpty();
        hooks.getSoftAssert().assertTrue(isModalInvisible, "Confirmation dialog should not be visible");
    }

    @Then("the Create News page is opened with my draft preserved")
    public void the_create_news_page_is_opened_with_my_draft_preserved() {
        //CreateNewsPage createNewsPage = new CreateNewsPage(hooks.getDriver());
        MandatoryFieldsNewsData expectedData = hooks.getTestValueProvider().getValidMandatoryFieldsNewsData();

        hooks.getSoftAssert().assertTrue(createNewsPage.getPageTitle().isDisplayed(), "Create News page is not opened");

        String actualTitle = createNewsPage.getTitleInput().getFieldElement().getAttribute("value");
        hooks.getSoftAssert().assertEquals(
                actualTitle,
                expectedData.getTitle(),
                "Title value was not preserved after the dialog is closed"
        );

        String actualContent = createNewsPage.getContentEditor().getInputAreaText();
        hooks.getSoftAssert().assertEquals(
                actualContent,
                expectedData.getContentText(),
                "Content text was not preserved after the dialog is closed"
        );

        List<String> actualTags = createNewsPage.getNewsTagsComponent().getSelectedTags();
        hooks.getSoftAssert().assertEquals(
                actualTags,
                expectedData.getTags(),
                "Selected tags are not preserved after the dialog is closed"
        );
    }

    @Then("the user is navigated away from the Create News page to the Eco News page")
    public  void the_user_is_navigated_away_from_the_Create_News_page_to_the_Eco_News_page() {
        String actualUrl = hooks.getDriver().getCurrentUrl();
        String expectedUrl = hooks.getTestValueProvider().getBaseUIUrl() + "/news";
        hooks.getSoftAssert().assertEquals(actualUrl, expectedUrl, "Expected Eco news page is not opened");
    }


}
