package com.greencity.cucumber.steps;

import com.greencity.ui.components.eco_news.EcoNewsTableCardComponent;
import com.greencity.ui.pages.EcoNewsDetailsPage;
import com.greencity.ui.pages.EcoNewsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EcoNewsDetailsStep {
    private Hooks hooks;

    public EcoNewsDetailsStep(Hooks hooks) {
        this.hooks = hooks;
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
        new EcoNewsDetailsPage(hooks.getDriver()).waitUntilPageLoaded();
    }

    @Then("the Eco News details title is populated")
    public void the_eco_news_details_title_is_populated() {

    }

    @Then("the Eco News details author is populated")
    public void the_eco_news_details_author_is_populated() {

    }

    @Then("the Eco News details date is populated")
    public void the_eco_news_details_date_is_populated() {

    }

    @Then("the Eco News details text is populated")
    public void the_eco_news_details_text_is_populated() {

    }

    @Then("the Eco News details tags are populated")
    public void the_eco_news_details_tags_are_populated() {

    }

    @Then("the Other interesting news section is populated")
    public void the_other_interesting_news_section_is_populated() {

    }


}
/*

 Scenario: existing details page is loaded
    When user click on Eco News with title containing 'test title' in table
    Then Eco News details page is loaded !
    And Eco News details title is populated
    And Eco News details author is populated
    And Eco News details date is populated
    And Eco News details text is populated
    And Eco News details tags is populated
    And Other interesting news are populated
 */
