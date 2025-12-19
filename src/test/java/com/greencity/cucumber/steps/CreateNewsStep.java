package com.greencity.cucumber.steps;

import com.greencity.ui.pages.CreateNewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateNewsStep {
    private Hooks hooks;
    private CreateNewsPage createNewsPage;

    public CreateNewsStep(Hooks hooks) {
        this.hooks = hooks;
    }

    @Given("the user opens the 'Create News' page")
    public void the_user_opens_the_create_news_page() {
        hooks.homePage = new HomePage(hooks.getDriver());
        hooks.homePage.refreshPage();
        createNewsPage = hooks.homePage
                .getHeader()
                .clickEcoNewsNavItem()
                .clickCreateNews();
    }

    @Given("the page is fully loaded")
    public void the_page_is_fully_loaded() {
        createNewsPage.waitUntilPageLoaded();
    }

    @Then("^the (.+) block should be visible$")
    public void the_block_should_be_visible(String blockName) {

        WebElement root = resolveBlockRoot(createNewsPage, blockName);
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

        WebElement element = resolveRootBlockForLabel(createNewsPage, blockName);
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

        WebElement element = resolveRootBlockForFieldInfo(createNewsPage, blockName);
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

        WebElement element = resolveRootBlockForInputField(createNewsPage, blockName);
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

    @Then("^the News tag should be selected$")
    public void the_News_tag_should_be_selected() {
        hooks.getSoftAssert().assertTrue(createNewsPage.getNewsTagsComponent()
                .isTagSelected("News"),
                "Tag News is not selected");
    }

    @When("the user unselects the News tag")
    public void the_user_unselects_the_News_tag() {
        createNewsPage.getNewsTagsComponent()
                .unselectTag("News");
    }

    @Then("the News tag should not be selected")
    public void the_News_tag_should_not_be_selected() {
        hooks.getSoftAssert().assertFalse(createNewsPage.getNewsTagsComponent()
                        .isTagSelected("News"),
                "Tag News stayed selected");
    }

    @Then("the Image dropzone should be visible")
    public void the_image_dropzone_should_be_visible() {
        hooks.getSoftAssert().assertTrue(createNewsPage.getAddImageComponent()
                        .getImageContainer().isDisplayed(),
                "Image dropzone is not visible");
    }

    @Then("^the Content Editor field info should contain '([^']+)'$")
    public void the_content_editor_field_info_should_contain(String expectedText) {
        hooks.getSoftAssert().assertTrue(createNewsPage.getContentEditor()
                .getFieldInfoElement()
                .getText().contains(expectedText),
                "Content Editor field info does not contain " + expectedText);
    }


    @Then("the Author field should not be empty")
    public void the_author_field_should_not_be_empty() {
        hooks.getSoftAssert().assertFalse(createNewsPage.getAuthorOfNews()
                        .getText().trim().isEmpty(),
                "the Author field is empty");
    }

    @Then("the Author field should match the logged-in username")
    public void the_author_field_should_match_the_logged_in_username() {
        String username = createNewsPage.getAuthorOfNews().getText().trim();
        hooks.getSoftAssert().assertEquals(username, hooks.getTestValueProvider().getLsUserName(),
                "Author field does not match the logged-in username");
    }

    @Then("the Author field should not be editable")
    public void the_author_field_should_not_be_editable() {
        boolean isEditable = Boolean.parseBoolean(createNewsPage.getAuthorOfNews()
                .getAttribute("contenteditable"));
        hooks.getSoftAssert().assertFalse(isEditable,
                "the Author field is editable");
    }

    @Then("the Date field should be visible")
    public void the_date_field_should_be_visible() {
        hooks.getSoftAssert().assertTrue(createNewsPage.getActualDate()
                        .isDisplayed(),
                "the Date field is not visible");
    }

    @Then("the Date field should not be empty")
    public void the_date_field_should_not_be_empty() {
        hooks.getSoftAssert().assertFalse(createNewsPage.getActualDate()
                        .getText().trim().isEmpty(),
                "the Date field is empty");
    }

    @Then("the Date field should not be editable")
    public void the_date_field_should_not_be_editable() {
        boolean isEditable = Boolean.parseBoolean(createNewsPage.getActualDate()
                .getAttribute("contenteditable"));
        hooks.getSoftAssert().assertFalse(isEditable,
                "the Date field is editable");
    }

    @Then("the Date field should match the format 'MMMM d, yyyy'")
    public void the_date_field_should_match_the_format() {
        boolean isValidFormat = createNewsPage.getActualDate()
                .getText().trim()
                .matches("^([A-Z][a-z]{2,}) ([1-9]|[12][0-9]|3[01]), \\d{4}$");
        hooks.getSoftAssert().assertTrue(isValidFormat,
                "Date format should match 'MMMM d, yyyy'");
    }

    @Then("^the Source field info should contain '([^']+)'$")
    public void the_source_field_info_should_contain(String expectedText) {
        String actualText = createNewsPage.getSourceInput()
                        .getFieldInfoElement()
                        .getText();
        hooks.getSoftAssert().assertTrue(
                actualText.contains(expectedText),
                "Source field info does not contain '" + expectedText
                        + "'. Actual: " + actualText
        );
    }


    @Then("the Cancel button should be visible")
    public void the_cancel_button_should_be_visible() {
        hooks.getSoftAssert().assertTrue(createNewsPage.getCreateNewsButtonsComponent()
                        .getCancelButton()
                        .isDisplayed(),
                "Cancel button is not visible");
    }

    @Then("the Preview button should be visible")
    public void the_preview_button_should_be_visible() {
        hooks.getSoftAssert().assertTrue(createNewsPage
                        .getCreateNewsButtonsComponent()
                        .getPreviewButton()
                        .isDisplayed(),
                "Preview button is not visible");
    }

    @Then("the Publish button should be visible")
    public void the_publish_button_should_be_visible() {
        hooks.getSoftAssert().assertTrue(createNewsPage
                        .getCreateNewsButtonsComponent()
                        .getPublishButton()
                        .isDisplayed(),
                "Publish button is not visible");
    }

    @When("the user clicks on the Title field")
    public void the_user_clicks_on_the_title_field() {
        createNewsPage.getTitleInput().clickOnField();
    }

    @When("the user enters an empty title")
    public void the_user_enters_an_empty_title() {
        createNewsPage.getTitleInput()
                .clickOnField()
                .typeText("");
    }

    @Then("the Title field should be marked as invalid")
    public void the_title_field_should_be_marked_as_invalid() {
        hooks.getSoftAssert().assertTrue(createNewsPage
                .getTitleInput()
                .isInvalidField(),
                "Title field is not marked as invalid");
    }

    @Then("the Publish button should be disabled in News creation page")
    public void the_publish_button_should_be_disabled() {
        hooks.getSoftAssert().assertTrue(createNewsPage
                        .getCreateNewsButtonsComponent()
                        .isPublishDisabled(),
                "Publish button is not disabled");
    }

    @Then("^the Title counter should display '([^']+)'$")
    public void the_title_counter_should_display_text(String text) {
        hooks.getSoftAssert().assertTrue(createNewsPage
                        .getTitleInput()
                        .getFieldInfoText()
                        .contains(text),
                "Title counter does not display " + text);
    }

    @When("the user enters a title longer than 170 characters")
    public void the_user_enters_a_title_longer_than_170_characters() {
        String longTitle = "A".repeat(172);
        createNewsPage.getTitleInput()
                .typeText(longTitle);
    }

    @Then("the Title field value should not exceed 170 characters")
    public void the_title_field_value_should_not_exceed_170_characters() {
        int actualLength = createNewsPage
                .getTitleInput()
                .getValue()
                .length();
        hooks.getSoftAssert().assertTrue(actualLength <=170,
                "in requirements field value <= 170 characters, " +
                        "characters but on the site implemented  is <=171");
    }

    @Then("the Title field should display a warning message")
    public void the_title_field_should_display_a_warning_message() {
        hooks.getSoftAssert().assertTrue(createNewsPage
                .getTitleInput()
                .hasWarningFieldInfo(),
                "Title warning message is not displayed");
    }

    @Given("^the user enters a valid title '([^']+)'$")
    public void the_user_enters_a_valid_title(String validTitle) {
        hooks.getSoftAssert().assertFalse(createNewsPage
                        .getTitleInput()
                        .clickOnField()
                        .typeText(validTitle)
                        .isInvalidField(),
                "Entered title is invalid");
    }

    @Then("the Title field should not display a warning message")
    public void the_title_field_should_not_display_a_warning_message() {
        hooks.getSoftAssert().assertFalse(createNewsPage
                        .getTitleInput()
                        .isInvalidField(),
                "the Title field is displayed a warning message");
    }

    @Then("the Main text field should remain empty")
    public void the_main_text_field_should_remain_empty() {
        hooks.getSoftAssert().assertTrue(createNewsPage
                        .getContentEditor()
                        .getInputAreaText()
                        .trim().isEmpty(),
                "the Main text field is not empty");
    }

    @Then("the Publish button should remain disabled")
    public void the_publish_button_should_remain_disabled() {
        hooks.getSoftAssert().assertTrue(createNewsPage
                        .getCreateNewsButtonsComponent()
                        .isPublishDisabled(),
                "the Publish button is enable");
    }

    @Given("the user enters valid main text of at least 20 characters")
    public void the_user_enters_valid_main_text_of_at_least_20_characters() {
        String validMainText = "B".repeat(21);
        createNewsPage.getContentEditor()
                        .clickOnMainText()
                        .typeText(validMainText);
    }

    @Given("the user selects the News tag")
    public void the_user_selects_the_news_tag() {
        createNewsPage.getNewsTagsComponent()
                        .selectTag("News");
    }

    @Then("the Publish button should be enabled")
    public void the_publish_button_should_be_enabled() {
        hooks.getSoftAssert().assertTrue(createNewsPage
                        .getCreateNewsButtonsComponent()
                        .isPublishEnabled(),
                "the Publish button is not enabled");
    }

    @When("the user enters {string} in the Content field")
    public void the_user_enters_text_in_the_content_field(String text) {
        createNewsPage.getContentEditor()
                .clickOnMainText()
                .typeText(text);
    }

    @Then ("an error message should appear in red with text {string}")
    public void an_error_message_should_appear_in_red_with_text(String expectedMessage) {
        String actualMessage = createNewsPage
                .getContentEditor()
                .getFieldInfoElement()
                .getText()
                .trim();

        hooks.getSoftAssert().assertEquals(actualMessage, expectedMessage,
                "an error message does not have expected message: " + expectedMessage);
    }

    @When("the user enters {string} characters in the Content field")
    public void the_user_enters_over_maximum_characters_in_the_content_field(String charactersNumber) {
        int overMaximumCharacters = Integer.parseInt(charactersNumber);

        String chunk = "R".repeat(1000);
        int fullChunks = overMaximumCharacters/1000;
        int remainderChunks = overMaximumCharacters%1000;

        createNewsPage.getContentEditor().clickOnMainText();
        for (int i = 0; i < fullChunks; i++) {
            createNewsPage.getContentEditor().typeText(chunk);
        }

        if (remainderChunks > 0) {
            createNewsPage.getContentEditor().typeText("R".repeat(remainderChunks));
        }

    }

    @Then ("the Content field value should be truncated to {string} characters")
    public void the_content_field_value_should_be_truncated_to_maximum_characters(String charactersNumber) {
        int expectedCharacterNumber = Integer.parseInt(charactersNumber);

        int actualCharacterNumber = createNewsPage
                .getContentEditor()
                .getInputAreaText()
                .trim()
                .length();

        hooks.getSoftAssert().assertTrue(actualCharacterNumber <= expectedCharacterNumber,
                "the Content field value has different number of characters- " +
                "expected: " + expectedCharacterNumber + ", but actual: " + actualCharacterNumber);
    }

    @Then ("no error message should be displayed")
    public void no_error_message_should_be_displayed() {
        hooks.getSoftAssert().assertFalse(createNewsPage
                        .getContentEditor()
                        .hasTextInputAreaWarning(),
                "no error message should be displayed");
    }

    @Given("the user selects three news tags")
    public void the_user_selects_three_news_tags() {
        CreateNewsPage page = new CreateNewsPage(hooks.getDriver());

        page.getNewsTagsComponent().selectTag("News");
        page.getNewsTagsComponent().selectTag("Events");
        page.getNewsTagsComponent().selectTag("Education");
    }

    @Then("the user should see the news successfully published notification")
    public void the_user_should_see_the_news_successfully_published_notification() {
        CreateNewsPage page = new CreateNewsPage(hooks.getDriver());

        new WebDriverWait(hooks.getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(page.getNewsPublishedMessage()));

        hooks.getSoftAssert().assertTrue(
                page.getNewsPublishedMessage()
                        .isDisplayed(),
                "News published notification is not displayed");
    }
}
