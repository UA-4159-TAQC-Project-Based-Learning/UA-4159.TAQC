package com.greencity.cucumber.steps;

import com.greencity.ui.pages.CreateNewsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class CreateNewsStep {
    private Hooks hooks;

    public CreateNewsStep(Hooks hooks) {
        this.hooks = hooks;
    }

    @Given("the user opens the 'Create News' page")
    public void the_user_opens_the_create_news_page() {
        hooks.getDriver().get(hooks.getTestValueProvider().getBaseUIUrl() + "/news/create-news");
    }

    @Given("the page is fully loaded")
    public void the_page_is_fully_loaded() {
        new CreateNewsPage(hooks.getDriver()).waitUntilPageLouder();
    }

    @Then("the Title block should be visible")
    public void the_title_block_should_be_visible() {
        hooks.getSoftAssert().assertTrue(new CreateNewsPage(hooks.getDriver())
                        .getTitleInputRoot()
                        .isDisplayed(),
                "Title block does not visible");
    }
    @Then("the Title label should be visible")
    public void the_title_label_should_be_visible() {
        boolean isVisible = new CreateNewsPage(hooks.getDriver())
                .getTitleInput()
                .getLabelElement()
                .isDisplayed();
        hooks.getSoftAssert().assertTrue(isVisible,
                "Title block does not visible");
    }

    @Then("the Title field info should be visible")
    public void the_title_field_info_should_be_visible() {
        boolean isVisible = new CreateNewsPage(hooks.getDriver())
                .getTitleInput()
                .getFieldInfoElement()
                .isDisplayed();
        hooks.getSoftAssert().assertTrue(isVisible,
                "Title field info does not visible");
    }

    @Then("the Title input field should be visible")
    public void the_title_input_field_should_be_visible() {
        boolean isVisible = new CreateNewsPage(hooks.getDriver())
                .getTitleInput()
                .getFieldElement()
                .isDisplayed();
        hooks.getSoftAssert().assertTrue(isVisible,
                "Title block does not visible");
    }

    @Then("the Tags block should be visible")
    public void the_tags_block_should_be_visible() {
        hooks.getSoftAssert().assertTrue(new CreateNewsPage(hooks.getDriver())
                        .getNewsTagsComponentRoot().isDisplayed(),
                "The tags block does not visible");
    }

    @Then("the user selects the 'News' tag")
    public void the_user_selects_the_News_tag() {
        new CreateNewsPage(hooks.getDriver())
                .getNewsTagsComponent()
                .selectTag("News");
    }

    @Then("the 'News' tag should be selected")
    public void the_News_tag_should_be_selected() {
        hooks.getSoftAssert().assertTrue(new CreateNewsPage(hooks.getDriver())
                .getNewsTagsComponent()
                .isTagSelected("News"),
                "Tag 'News' does not selected");
    }

    @Then("the user unselects the 'News' tag")
    public void the_user_unselects_the_News_tag() {
        new CreateNewsPage(hooks.getDriver())
                .getNewsTagsComponent()
                .unselectTag("News");
    }

    @Then("the 'News' tag should not be selected")
    public void the_News_tag_should_not_be_selected() {
        hooks.getSoftAssert().assertFalse(new CreateNewsPage(hooks.getDriver())
                        .getNewsTagsComponent()
                        .isTagSelected("News"),
                "Tag 'News' stayed selected");
    }

    @Then("the Add Image block should be visible")
    public void the_add_image_block_should_be_visible() {
        hooks.getSoftAssert().assertTrue(new CreateNewsPage(hooks.getDriver())
                .getAddImageComponentRoot().isDisplayed(),
                "Add Image block does not visible");
    }

    @Then("the Image dropzone should be visible")
    public void the_image_dropzone_should_be_visible() {
        hooks.getSoftAssert().assertTrue(new CreateNewsPage(hooks.getDriver())
                        .getAddImageComponent()
                        .getImageContainer().isDisplayed(),
                "Image dropzone does not visible");
    }

    @Then("the Content Editor should be visible")
    public void the_content_editor_should_be_visible() {
        hooks.getSoftAssert().assertTrue(new CreateNewsPage(hooks.getDriver())
                        .getContentEditorRoot().isDisplayed(),
                "Content Editor block does not should visible");
    }

//    @Then("the Content Editor field info should contain '{string}'")
//    public void the_content_editor_field_info_should_contain(String expectedText) {
//        hooks.getSoftAssert().assertTrue(new CreateNewsPage(hooks.getDriver())
//                .getContentEditor()
//                .getFieldInfoElement()
//                .getText().contains(expectedText),
//                "Content Editor field info is not contain " + expectedText);
//    }

    @Then("the Content Editor field info should contain '63 206'")
    public void the_content_editor_field_info_should_contain() {
        hooks.getSoftAssert().assertTrue(new CreateNewsPage(hooks.getDriver())
                        .getContentEditor()
                        .getFieldInfoElement()
                        .getText().contains("63 206"),
                "Content Editor field info does not contain '63 206'");
    }

    @Then("the Author field should not be empty")
    public void the_author_field_should_not_be_empty() {
        hooks.getSoftAssert().assertFalse(new CreateNewsPage(hooks.getDriver())
                        .getAuthorOfNews().getText().trim().isEmpty(),
                "the Author field is empty");
    }

    @Then("the Author field should match the logged-in username")
    public void the_author_field_should_match_the_logged_in_username() {
        String username = new CreateNewsPage(hooks.getDriver())
                        .getAuthorOfNews().getText().trim();
        hooks.getSoftAssert().assertEquals(username, hooks.getTestValueProvider().getLsUserName(),
                "Title block should does not visible");
    }

    @Then("the Author field should not be editable")
    public void the_author_field_should_not_be_editable() {
        boolean isEditable = Boolean.parseBoolean(new CreateNewsPage(hooks.getDriver())
                .getAuthorOfNews().
                getAttribute("contenteditable"));
        hooks.getSoftAssert().assertFalse(isEditable,
                "the Author field is editable");
    }

    @Then("the Date field should be visible")
    public void the_date_field_should_be_visible() {
        hooks.getSoftAssert().assertTrue(new CreateNewsPage(hooks.getDriver())
                        .getActualDate().isDisplayed(),
                "the Date field does not visible");
    }

    @Then("the Date field should not be empty")
    public void the_date_field_should_not_be_empty() {
        hooks.getSoftAssert().assertFalse(new CreateNewsPage(hooks.getDriver())
                        .getActualDate().getText().trim().isEmpty(),
                "the Date field is empty");
    }

    @Then("the Date field should not be editable")
    public void the_date_field_should_not_be_editable() {
        boolean isEditable = Boolean.parseBoolean(new CreateNewsPage(hooks.getDriver())
                .getActualDate().
                getAttribute("contenteditable"));
        hooks.getSoftAssert().assertFalse(isEditable,
                "the Date field is editable");
    }

    @Then("the Date field should match the format 'MMMM d, yyyy'")
    public void the_date_field_should_match_the_format() {
        boolean isValidFormat = new CreateNewsPage(hooks.getDriver())
                .getActualDate().getText().trim()
                .matches("^(^[A-Z][a-z]{2,}) ([1-9]|[12][0-9]|3[01]), \\d{4}$");
        hooks.getSoftAssert().assertTrue(isValidFormat,
                "Date format should match 'MMMM d, yyyy'");
    }

    @Then("the Source block should be visible")
    public void the_source_block_should_be_visible() {
        hooks.getSoftAssert().assertTrue(new CreateNewsPage(hooks.getDriver())
                        .getSourceInputRoot()
                        .isDisplayed(),
                "Source block does not visible");
    }

    @Then("the Source label should be visible")
    public void the_source_label_should_be_visible() {
        boolean isVisible = new CreateNewsPage(hooks.getDriver())
                .getSourceInput()
                .getLabelElement()
                .isDisplayed();
        hooks.getSoftAssert().assertTrue(isVisible,
                "Source block should does not visible");
    }

    @Then("the Source field info should be visible")
    public void the_source_field_info_should_be_visible() {
        boolean isVisible = new CreateNewsPage(hooks.getDriver())
                .getSourceInput()
                .getFieldInfoElement()
                .isDisplayed();
        hooks.getSoftAssert().assertTrue(isVisible,
                "Source field info does not visible");
    }

    @Then("the Source input field should be visible")
    public void the_source_input_field_should_be_visible() {
        boolean isVisible = new CreateNewsPage(hooks.getDriver())
                .getSourceInput()
                .getFieldElement()
                .isDisplayed();
        hooks.getSoftAssert().assertTrue(isVisible,
                "Source block does not visible");
    }

    @Then("^the Source field info should contain '([^']+)'$")
    public void the_source_field_info_should_contain(String expectedText) {
        String actualText = new CreateNewsPage(hooks.getDriver())
                        .getSourceInput()
                        .getFieldInfoElement()
                        .getText();
        hooks.getSoftAssert().assertTrue(
                actualText.contains(expectedText),
                "Source field info does not contain '" + expectedText + "'. Actual: " + actualText
        );
    }

    @Then("the buttons block should be visible")
    public void the_buttons_block_should_be_visible() {
        hooks.getSoftAssert().assertTrue(new CreateNewsPage(hooks.getDriver())
                        .getSubmitButtonsRoot()
                        .isDisplayed(),
                "Source block does not visible");
    }


    @Then("the 'Cancel' button should be visible")
    public void the_cancel_button_should_be_visible() {
        hooks.getSoftAssert().assertTrue(new CreateNewsPage(hooks.getDriver())
                        .getCreateNewsButtonsComponent()
                        .getCancelButton()
                        .isDisplayed(),
                "'Cancel' button does not visible");
    }

    @Then("the 'Preview' button should be visible")
    public void the_preview_button_should_be_visible() {
        hooks.getSoftAssert().assertTrue(new CreateNewsPage(hooks.getDriver())
                        .getCreateNewsButtonsComponent()
                        .getPreviewButton()
                        .isDisplayed(),
                "'Preview' button does not visible");
    }

    @Then("the 'Publish' button should be visible")
    public void the_publish_button_should_be_visible() {
        hooks.getSoftAssert().assertTrue(new CreateNewsPage(hooks.getDriver())
                        .getCreateNewsButtonsComponent()
                        .getPublishButton()
                        .isDisplayed(),
                "'Publish' button does not visible");
    }

}
