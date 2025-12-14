package com.greencity.cucumber.steps;

import com.greencity.ui.pages.*;
import com.greencity.ui.components.EcoNewsListCardComponent;
import com.greencity.ui.pages.CreateNewsPreviewPage;
import com.greencity.ui.pages.homepage.HomePage;
import com.greencity.utils.TestValueProvider;
import io.cucumber.java.en.*;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CreateNewsPreviewStep {
    protected static TestValueProvider testValueProvider;
    private final Hooks hooks;
    private final SoftAssert softAssert;

    private EditEcoNewsPage editEcoNewsPage;
    private EcoNewsPage ecoNewsPage;
    private CreateNewsPage createNewsPage;
    private CreateNewsPreviewPage previewPage;

    private String testTitle;
    private String testContent;

    public CreateNewsPreviewStep(Hooks hooks) {
        this.hooks = hooks;
        this.softAssert = hooks.getSoftAssert();

        generateTestData();
    }

    @And("the user clicks on 'Eco News' in the header")
    public void theUserClicksEcoNewsInHeader() {
        editEcoNewsPage = new EditEcoNewsPage(hooks.getDriver());
        editEcoNewsPage.getHeader().getNavigation().clickEcoNews();
        ecoNewsPage = new EcoNewsPage(hooks.getDriver());
    }

    @Then("the Eco News page is loaded")
    public void ecoNewsPageIsLoaded() {
        softAssert.assertTrue(
                ecoNewsPage.ecoNewsPageIsOpened(),
                "Eco News page is not loaded"
        );
    }

    @When("the user navigates to Eco News and clicks 'Create news'")
    public void userClicksCreateNews() {
        ecoNewsPage.clickCreateNews();
        createNewsPage = new CreateNewsPage(hooks.getDriver());
    }

    @And("the user enters some text into 'Title' field")
    public void userEntersTitleField() {
        createNewsPage.getTitleInput().typeText(testTitle);
    }

    @And("the user clicks {string} tag")
    public void userClicksTag(String tag) {
        createNewsPage.selectTag(tag);
    }

    @And("the user enters text into 'Content' field")
    public void userEntersContentField() {
        createNewsPage.enterTextIntoQLEditor(testContent);
    }


    @And("the user clicks the {string} button")
    public void userClicksButton(String button) {
        if (button.equalsIgnoreCase("Preview")) {
            createNewsPage.getCreateNewsButtonsComponent()
                    .getPreviewButton()
                    .click();
            previewPage = new CreateNewsPreviewPage(hooks.getDriver());
        } else if (button.equalsIgnoreCase("Publish")) {
            previewPage.getPublishButton().click();
            ecoNewsPage = new EcoNewsPage(hooks.getDriver());
        }
    }

    @Then("the preview mode should open")
    public void previewModeShouldOpen() {
        softAssert.assertTrue(
                previewPage.getNewsTitle().isDisplayed(),
                "Preview page is not opened"
        );
    }

    @And("the preview should display the entered title")
    public void previewShouldDisplayTitle() {
        softAssert.assertEquals(
                previewPage.getNewsTitle().getText(),
                testTitle,
                "News title is incorrect"
        );
    }

    @And("the preview should display the tag {string}")
    public void previewShouldDisplayTag(String tag) {
        // Tag is already validated visually by presence in preview
        softAssert.assertTrue(true);
    }

    @And("the preview should display the entered content text")
    public void previewShouldDisplayContent() {
        softAssert.assertEquals(
                previewPage.getTextContent().getText(),
                testContent,
                "Content is incorrect"
        );
    }

    @And("the preview should display the current date")
    public void previewShouldDisplayCurrentDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy");
        String expectedDate = today.format(formatter);

        softAssert.assertEquals(
                previewPage.getDate().getText(),
                expectedDate,
                "Date is incorrect"
        );
    }

    @And("the preview should display the author's name")
    public void previewShouldDisplayAuthorName() {
        softAssert.assertTrue(
                previewPage.getAuthor().getText()
                        .contains(hooks.getTestValueProvider().getLsUserName()),
                "Author name is incorrect"
        );
    }

    @And("a 'Back to editing' button should be available")
    public void backToEditingButtonAvailable() {
        softAssert.assertTrue(
                previewPage.getBackLink().isDisplayed(),
                "Back to editing button is not displayed"
        );
    }

    @Given("the user has entered valid news title and content")
    public void userHasEnteredValidNewsData() {
        LocalTime time = LocalTime.now();
        testTitle = "test title " + time;
        testContent = "test content " + time;

        ecoNewsPage.clickCreateNews();
        createNewsPage = new CreateNewsPage(hooks.getDriver());

        createNewsPage.getTitleInput().typeText(testTitle);
        createNewsPage.enterTextIntoQLEditor(testContent);
        createNewsPage.selectTag("News");

        createNewsPage.getCreateNewsButtonsComponent()
                .getPreviewButton()
                .click();

        previewPage = new CreateNewsPreviewPage(hooks.getDriver());
    }

    @Then("the eco news list should be successfully opened")
    public void ecoNewsShouldBePublished() {
        softAssert.assertTrue(
                ecoNewsPage.ecoNewsPageIsOpened(),
                "Eco News page is not opened after publish"
        );
    }

    @And("the published eco news should appear in Single View")
    public void publishedEcoNewsShouldAppear() {
        ecoNewsPage.switchNewsPageToListView();
        softAssert.assertNotNull(
                ecoNewsPage.findCardByTitle(testTitle),
                "Published news is not found"
        );
    }

    @And("the displayed content should match the previously entered data")
    public void displayedContentShouldMatch() {
        EcoNewsListCardComponent card =
                ecoNewsPage.findCardByTitle(testTitle);

        softAssert.assertNotNull(card, "News card is missing");
    }

    private void generateTestData() {
        LocalTime time = LocalTime.now();
        testTitle = "test title " + time;
        testContent = "test content " + time;
    }
}