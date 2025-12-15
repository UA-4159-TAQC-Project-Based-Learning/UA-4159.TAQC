package com.greencity.cucumber.steps;

import com.greencity.ui.pages.CreateNewsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;

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

    @Then("^the (.+) block should be visible$")
    public void the_block_should_be_visible(String blockName) {

        CreateNewsPage page = new CreateNewsPage(hooks.getDriver());
        WebElement root = resolveBlockRoot(page, blockName);
        hooks.getSoftAssert().assertTrue(root != null && root.isDisplayed(),
                blockName + " block is not visible");
    }

    private WebElement resolveBlockRoot(CreateNewsPage page, String blockName) {
        switch (blockName.toLowerCase()) {
            case "title":
                return page.getTitleInputRoot();
            case "tags":
                return page.getNewsTagsComponentRoot();
            case "add image":
                return page.getAddImageComponentRoot();
            case "content editor":
                return page.getContentEditorRoot();
            case "buttons":
                return page.getSubmitButtonsRoot();
            case "source":
                return page.getSourceInputRoot();
            default:
                hooks.getSoftAssert().fail("Unknown block: " + blockName);
                return null;
        }
    }


    @Then("^the (.+) label should be visible$")
    public void the_label_should_be_visible(String blockName) {

        CreateNewsPage page = new CreateNewsPage(hooks.getDriver());
        WebElement element = resolveRootBlockForLabel(page, blockName);
        hooks.getSoftAssert().assertTrue(element != null &&
                        element.isDisplayed(),
                blockName + " label is not visible");
    }

    private WebElement resolveRootBlockForLabel(CreateNewsPage page, String blockName) {
        switch (blockName.toLowerCase()) {
            case "title":
                return page.getTitleInput().getLabelElement();
            case "source":
                return page.getSourceInput().getLabelElement();
            default:
                hooks.getSoftAssert().fail(blockName + " label is not visible");
                return null;
        }
    }

    @Then("^the (.+) field info should be visible$")
    public void the_field_info_should_be_visible(String blockName) {

        CreateNewsPage page = new CreateNewsPage(hooks.getDriver());
        WebElement element = resolveRootBlockForFieldInfo(page, blockName);
        hooks.getSoftAssert().assertTrue(element != null &&
                        element.isDisplayed(),
                blockName + " field info is not visible");
    }

    private WebElement resolveRootBlockForFieldInfo(CreateNewsPage page, String blockName) {
        switch (blockName.toLowerCase()) {
            case "title":
                return page.getTitleInput().getFieldInfoElement();
            case "source":
                return page.getSourceInput().getFieldInfoElement();
            default:
                hooks.getSoftAssert().fail(blockName + " field info is not visible");
                return null;
        }
    }

    @Then("^the (.+) input field should be visible$")
    public void the_input_field_should_be_visible(String blockName) {

        CreateNewsPage page = new CreateNewsPage(hooks.getDriver());
        WebElement element = resolveRootBlockForInputField(page, blockName);
        hooks.getSoftAssert().assertTrue(element != null &&
                        element.isDisplayed(),
                blockName + " input field is not visible");
    }

    private WebElement resolveRootBlockForInputField(CreateNewsPage page, String blockName) {
        switch (blockName.toLowerCase()) {
            case "title":
                return page.getTitleInput().getFieldElement();
            case "source":
                return page.getSourceInput().getFieldElement();
            default:
                hooks.getSoftAssert().fail(blockName + " input field is not visible");
                return null;
        }
    }

    @When("the user selects the 'News' tag")
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
                "Tag 'News' is not selected");
    }

    @When("the user unselects the 'News' tag")
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

    @Then("the Image dropzone should be visible")
    public void the_image_dropzone_should_be_visible() {
        hooks.getSoftAssert().assertTrue(new CreateNewsPage(hooks.getDriver())
                        .getAddImageComponent()
                        .getImageContainer().isDisplayed(),
                "Image dropzone is not visible");
    }

    @Then("^the Content Editor field info should contain '([^']+)'$")
    public void the_content_editor_field_info_should_contain(String expectedText) {
        hooks.getSoftAssert().assertTrue(new CreateNewsPage(hooks.getDriver())
                .getContentEditor()
                .getFieldInfoElement()
                .getText().contains(expectedText),
                "Content Editor field info does not contain " + expectedText);
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
                "Author field does not match the logged-in username");
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
                "the Date field is not visible");
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
                .matches("^([A-Z][a-z]{2,}) ([1-9]|[12][0-9]|3[01]), \\d{4}$");
        hooks.getSoftAssert().assertTrue(isValidFormat,
                "Date format should match 'MMMM d, yyyy'");
    }

    @Then("^the Source field info should contain '([^']+)'$")
    public void the_source_field_info_should_contain(String expectedText) {
        String actualText = new CreateNewsPage(hooks.getDriver())
                        .getSourceInput()
                        .getFieldInfoElement()
                        .getText();
        hooks.getSoftAssert().assertTrue(
                actualText.contains(expectedText),
                "Source field info does not contain '" + expectedText
                        + "'. Actual: " + actualText
        );
    }


    @Then("the 'Cancel' button should be visible")
    public void the_cancel_button_should_be_visible() {
        hooks.getSoftAssert().assertTrue(new CreateNewsPage(hooks.getDriver())
                        .getCreateNewsButtonsComponent()
                        .getCancelButton()
                        .isDisplayed(),
                "'Cancel' button is not visible");
    }

    @Then("the 'Preview' button should be visible")
    public void the_preview_button_should_be_visible() {
        hooks.getSoftAssert().assertTrue(new CreateNewsPage(hooks.getDriver())
                        .getCreateNewsButtonsComponent()
                        .getPreviewButton()
                        .isDisplayed(),
                "'Preview' button is not visible");
    }

    @Then("the 'Publish' button should be visible")
    public void the_publish_button_should_be_visible() {
        hooks.getSoftAssert().assertTrue(new CreateNewsPage(hooks.getDriver())
                        .getCreateNewsButtonsComponent()
                        .getPublishButton()
                        .isDisplayed(),
                "'Publish' button is not visible");
    }

}
