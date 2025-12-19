package com.greencity.cucumber.steps;

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

}