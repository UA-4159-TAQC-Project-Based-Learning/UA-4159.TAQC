package com.greencity.cucumber.steps;

import com.greencity.ui.pages.CreateNewsPage;
import com.greencity.ui.pages.EcoNewsDetailsPage;
import com.greencity.ui.pages.EcoNewsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CreateNewsTagSelectionStep {
    private final Hooks hooks;
    private final CreateNewsPage createNewsPage;
    private EcoNewsPage ecoNewsPage;
    private EcoNewsDetailsPage ecoNewsDetailsPage;
    private String enteredTitle;

    public CreateNewsTagSelectionStep(Hooks hooks) {
        this.hooks = hooks;
        this.createNewsPage = new CreateNewsPage(hooks.getDriver());
    }

    private List<String> parseTags(String tags) {
        return Arrays.stream(tags.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    @When("the user selects the tags: {string}")
    public void the_user_selects_the_tags(String tags) {
        for (String tag : parseTags(tags)) {
            createNewsPage.selectTag(tag);
        }
    }

    @When("the user enters the title {string}")
    public void the_user_enters_the_title(String title) {
        enteredTitle = title;
        createNewsPage.getTitleInput().typeText(title);
    }

    @When("the user enters the main text {string}")
    public void the_user_enters_the_main_text(String mainText) {
        createNewsPage.getContentEditor().typeText(mainText);
    }

    @When("the user clicks the Publish button")
    public void the_user_clicks_the_publish_button() {
        ecoNewsPage = createNewsPage
                .getCreateNewsButtonsComponent()
                .clickPublish();
    }

    @Then("the news should be published successfully")
    public void the_news_should_be_published_successfully() {
        hooks.getSoftAssert().assertNotNull(
                ecoNewsPage,
                "After clicking Publish user should be redirected to EcoNewsPage"
        );

        this.ecoNewsDetailsPage = ecoNewsPage
                .getOneTableCardByTitle(enteredTitle)
                .goToDetails();

        hooks.getSoftAssert(). assertEquals(
                ecoNewsDetailsPage.getTitle(), enteredTitle,
                "Published news title does not match expected");
    }

    @Then("the published news should contain the tags: {string}")
    public void the_published_news_should_contain_the_tags(String tags) {
        for (String tag : parseTags(tags)) {
            hooks.getSoftAssert().assertTrue(
                    ecoNewsDetailsPage.getNewsTagsInfoComponent().hasTag(tag),
                    "Expected tag '" + tag + "' was not found on published news"
            );
        }
    }

    @Then("the published news should not contain tags: {string}")
    public void the_published_news_should_not_contain_tags(String tags) {
        for (String tag : parseTags(tags)) {
            hooks.getSoftAssert().assertFalse(
                    ecoNewsDetailsPage.getNewsTagsInfoComponent().hasTag(tag),
                    "Unexpected tag '" + tag + "' was found on published news"
            );
        }
    }

    @Then("only 3 tags should remain selected: {string}")
    public void only_3_tags_should_remain_selected(String tags) {
        List<String> expected = parseTags(tags);
        List<String> actual = createNewsPage.getNewsTagsComponent().getSelectedTags();

        hooks.getSoftAssert().assertEquals(
                actual.size(), 3,
                "User should not be able to select more than 3 tags"
        );

        hooks.getSoftAssert().assertEquals(
                actual, expected,
                "Selected tags do not match expected after selecting more than 3"
        );
    }

    @Then("the selected tags should not include: {string}")
    public void the_selected_tags_should_not_include_tags(String tags) {
        List<String> actual = createNewsPage.getNewsTagsComponent().getSelectedTags();

        for (String tag : parseTags(tags)) {
            hooks.getSoftAssert().assertFalse(
                    actual.contains(tag),
                    "Unexpected tag '" + tag + "' is selected"
            );
        }
    }

    @Then("the Publish button should be disabled")
    public void the_Publish_button_should_be_disabled() {
        boolean publishEnabled = createNewsPage
                .getCreateNewsButtonsComponent()
                .isPublishEnabled();

        hooks.getSoftAssert().assertFalse(publishEnabled,
                "Expected Publish button to be disabled when tags are not selected");
    }

}
