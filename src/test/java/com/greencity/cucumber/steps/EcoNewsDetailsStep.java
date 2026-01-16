package com.greencity.cucumber.steps;
import com.greencity.ui.components.eco_news.EcoNewsDetailsCommentItemComponent;
import com.greencity.ui.components.eco_news.EcoNewsTableCardComponent;
import com.greencity.ui.pages.EcoNewsDetailsPage;
import com.greencity.ui.pages.EcoNewsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

public class EcoNewsDetailsStep {
    private Hooks hooks;

    public EcoNewsDetailsStep(Hooks hooks) {
        this.hooks = hooks;
    }

    @Then("the Eco News page is loaded successfully")
    public void the_eco_news_page_is_loaded() {
        new EcoNewsPage(hooks.getDriver()).waitUntilPageLoaded();
    }

    @When("the user clicks on Eco News with title containing {string} in the table")
    public void the_user_clicks_on_eco_news_with_title_containing_in_the_table(String text) {

        EcoNewsPage ecoNewsPage = new EcoNewsPage(hooks.getDriver());
        EcoNewsTableCardComponent tableCardComponent = ecoNewsPage
                .getOneTableCardByPartialTitle(text);
        hooks.getSoftAssert().assertNotNull(tableCardComponent);
        hooks.getSoftAssert().assertTrue(tableCardComponent.getTitle().contains(text));

        EcoNewsDetailsPage ecoNewsDetailsPage = tableCardComponent.goToDetails();
        boolean detailsContainsText = ecoNewsDetailsPage.getTitle().contains(text);
        hooks.getSoftAssert().assertTrue(detailsContainsText, "details page does not contain text " + text);
    }

    @Then("the Eco News details page is loaded")
    public void the_eco_news_details_page_is_loaded() {
        EcoNewsDetailsPage ecoNewsDetailsPage = new EcoNewsDetailsPage(hooks.getDriver());
        ecoNewsDetailsPage.waitUntilPageLoaded();
    }

    @Then("the Eco News details title is populated")
    public void the_eco_news_details_title_is_populated() {
        EcoNewsDetailsPage ecoNewsDetailsPage = new EcoNewsDetailsPage(hooks.getDriver());
        hooks.getSoftAssert().assertNotNull(ecoNewsDetailsPage.getTitle());
    }

    @Then("the Eco News details author is populated")
    public void the_eco_news_details_author_is_populated() {
        EcoNewsDetailsPage ecoNewsDetailsPage = new EcoNewsDetailsPage(hooks.getDriver());
        hooks.getSoftAssert().assertNotNull(ecoNewsDetailsPage.getAuthorName());
    }

    @Then("the Eco News details date is populated")
    public void the_eco_news_details_date_is_populated() {
        EcoNewsDetailsPage ecoNewsDetailsPage = new EcoNewsDetailsPage(hooks.getDriver());
        hooks.getSoftAssert().assertNotNull(ecoNewsDetailsPage.getDateInfo());
    }

    @Then("the Eco News details text is populated")
    public void the_eco_news_details_text_is_populated() {
        EcoNewsDetailsPage ecoNewsDetailsPage = new EcoNewsDetailsPage(hooks.getDriver());
        hooks.getSoftAssert().assertNotNull(ecoNewsDetailsPage.getText());
    }

    @Then("the Eco News details tags are populated")
    public void the_eco_news_details_tags_are_populated() {
        EcoNewsDetailsPage ecoNewsDetailsPage = new EcoNewsDetailsPage(hooks.getDriver());
        List<String> allTags = ecoNewsDetailsPage.getNewsTagsInfoComponent().getAllTags();
        hooks.getSoftAssert().assertTrue(allTags.size() > 0);
    }

    @Then("the Other interesting news section is populated")
    public void the_other_interesting_news_section_is_populated() {
        EcoNewsDetailsPage ecoNewsDetailsPage = new EcoNewsDetailsPage(hooks.getDriver());
        hooks.getSoftAssert().assertTrue(ecoNewsDetailsPage.getRecommendedNewsTableCardComponents().size() > 0);
    }

    @When("the user scrolls to the comments section")
    public void the_user_scrolls_to_the_comments_section() {
        EcoNewsDetailsPage ecoNewsDetailsPage = new EcoNewsDetailsPage(hooks.getDriver());
        ecoNewsDetailsPage.scrollToEndOfPage();
    }

    @When("the user clicks on the comment input field")
    public void the_user_clicks_on_the_comment_input_field() {
        EcoNewsDetailsPage ecoNewsDetailsPage = new EcoNewsDetailsPage(hooks.getDriver());
        ecoNewsDetailsPage.clickCommentInputElementButton();
    }

    @When("the user enters text {string}")
    public void the_user_enters_text(String comment) {
        EcoNewsDetailsPage ecoNewsDetailsPage = new EcoNewsDetailsPage(hooks.getDriver());
        ecoNewsDetailsPage.enterCommentText(comment);
    }

    @When("the user clicks the add Comment button")
    public void the_user_clicks_the_add_button() {
        EcoNewsDetailsPage ecoNewsDetailsPage = new EcoNewsDetailsPage(hooks.getDriver());
        ecoNewsDetailsPage.clickCommentButtonElement();
    }

    @Then("a comment with text {string} appears")
    public void a_comment_with_text_appears(String text) {
        EcoNewsDetailsPage ecoNewsDetailsPage = new EcoNewsDetailsPage(hooks.getDriver());
        EcoNewsDetailsCommentItemComponent commentItemByText = ecoNewsDetailsPage.findOneCommentItemByText(text);
        hooks.getSoftAssert().assertNotNull(commentItemByText, "Cannot find comment item by text " + text);
    }

    @Then("user name is populated for comment {string}")
    public void user_name_is_populated(String comment) {
        EcoNewsDetailsPage ecoNewsDetailsPage = new EcoNewsDetailsPage(hooks.getDriver());
        EcoNewsDetailsCommentItemComponent commentItemByText = ecoNewsDetailsPage.findOneCommentItemByText(comment);
        hooks.getSoftAssert().assertNotNull(commentItemByText.getAuthorName());
    }

    @When("the user clicks delete on comment with text {string}")
    public void the_user_clicks_delete_on_comment_with_text(String string) {
        EcoNewsDetailsPage ecoNewsDetailsPage = new EcoNewsDetailsPage(hooks.getDriver());
        EcoNewsDetailsCommentItemComponent commentItemByText = ecoNewsDetailsPage.findOneCommentItemByText(string);
        commentItemByText.clickDelete();
    }

    @When("the user confirms deletion in the popup")
    public void the_user_confirms_deletion_in_the_popup() {
        EcoNewsDetailsPage ecoNewsDetailsPage = new EcoNewsDetailsPage(hooks.getDriver());
        ecoNewsDetailsPage.clickPopupYesButton();
    }

    @Then("the popup disappears")
    public void the_popup_disappears() {
        EcoNewsDetailsPage ecoNewsDetailsPage = new EcoNewsDetailsPage(hooks.getDriver());
        boolean isPopupDisappear = ecoNewsDetailsPage.waitForPopupDisappears();
        hooks.getSoftAssert().assertTrue(isPopupDisappear, "popup is not disappear");
    }

    @Then("the comment with text {string} is not displayed")
    public void the_comment_with_text_is_not_displayed(String comment) {
        EcoNewsDetailsPage ecoNewsDetailsPage = new EcoNewsDetailsPage(hooks.getDriver());
        EcoNewsDetailsCommentItemComponent commentItemByText = ecoNewsDetailsPage.findOneCommentItemByText(comment);
        hooks.getSoftAssert().assertNull(commentItemByText, "Comment with text " + comment + " is still displayed");
    }

}