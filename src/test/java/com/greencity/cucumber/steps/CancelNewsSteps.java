package com.greencity.cucumber.steps;

import com.greencity.ui.components.createNews.CancelNewsModal;
import com.greencity.ui.components.createNews.CreateNewsButtonsComponent;
import com.greencity.ui.pages.CreateNewsPage;
import com.greencity.ui.pages.EcoNewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import com.greencity.ui.utils.NavItem;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CancelNewsSteps {

    private final Hooks hooks;

    private CreateNewsPage createNewsPage;
    private EcoNewsPage ecoNewsPage;
    private CancelNewsModal cancelNewsModal;

    private long popupAppearanceMs;

    public CancelNewsSteps(Hooks hooks) {
        this.hooks = hooks;
    }

    @Given("the user opens Eco News via header")
    public void openEcoNewsViaHeader() {
        ecoNewsPage = (EcoNewsPage) new HomePage(hooks.getDriver())
                .getHeader()
                .openNavItem(NavItem.ECO_NEWS);
        ecoNewsPage.waitUntilPageLouder();
    }

    @Given("the user opens the Create News page")
    public void openCreateNewsPage() {
        createNewsPage = ecoNewsPage.clickCreateNews();
    }

    @Given("the Create News page is fully loaded")
    public void createNewsPageLoaded() {
        createNewsPage.waitUntilPageLouder();
    }

    @Then("the Cancel button should be visible")
    public void cancelButtonVisible() {
        CreateNewsButtonsComponent buttons = createNewsPage.getCreateNewsButtonsComponent();
        hooks.getSoftAssert().assertTrue(
                buttons.getCancelButton().isDisplayed(),
                "Cancel button should be visible on Create News page."
        );
    }

    @Then("the Cancel button should be enabled")
    public void cancelButtonEnabled() {
        CreateNewsButtonsComponent buttons = createNewsPage.getCreateNewsButtonsComponent();
        hooks.getSoftAssert().assertTrue(
                buttons.getCancelButton().isEnabled(),
                "Cancel button should be enabled on Create News page."
        );
    }

    @When("the user enters title {string} and content {string}")
    public void enterTitleAndContent(String title, String content) {
        String uniqueTitle = getTimestamp(title);
        String uniqueContent = getTimestamp(content);

        createNewsPage.enterTitleAndContent(uniqueTitle, uniqueContent);
    }

    @When("the user clicks Cancel on Create News")
    public void clickCancelOnCreateNews() {
        cancelNewsModal = createNewsPage.openCancelModal().waitUntilOpened();
    }

    @Then("the cancel confirmation popup should be displayed")
    public void popupDisplayed() {
        hooks.getSoftAssert().assertTrue(
                cancelNewsModal.getCancelNewsChangesModalTitle().isDisplayed(),
                "Cancel confirmation popup title should be visible."
        );
    }

    @Then("the popup should contain Continue editing button")
    public void popupHasContinueEditing() {
        hooks.getSoftAssert().assertTrue(
                cancelNewsModal.getContinueNewsEditingModalButtonAlternative().isDisplayed(),
                "'Continue editing' button should be visible in the popup."
        );
    }

    @Then("the popup should contain Yes cancel button")
    public void popupHasYesCancel() {
        hooks.getSoftAssert().assertTrue(
                cancelNewsModal.getYesCancelChangesModalButtonAlternative().isDisplayed(),
                "'Yes, cancel' button should be visible in the popup."
        );
    }

    @When("the user confirms cancel in the popup")
    public void confirmCancelInPopup() {
        cancelNewsModal.getYesCancelChangesModalButtonAlternative().click();
        ecoNewsPage = new EcoNewsPage(hooks.getDriver());
        ecoNewsPage.waitUntilPageLoaded();
    }

    @Then("the user should be redirected to Eco News page")
    public void redirectedToEcoNews() {
        hooks.getSoftAssert().assertTrue(
                ecoNewsPage.ecoNewsPageIsOpened(),
                "User should be redirected back to Eco News page after confirming cancel."
        );
    }

    @Then("the current URL should contain Eco News path")
    public void urlContainsEcoNewsPath() {
        String currentUrl = hooks.getDriver().getCurrentUrl();
        Assert.assertNotNull(currentUrl);
        hooks.getSoftAssert().assertTrue(
                currentUrl.contains(NavItem.ECO_NEWS.href()),
                "URL should contain Eco News path. Actual: " + currentUrl
        );
    }

    @Then("the news card with title {string} should not exist in Eco News list")
    public void cardShouldNotExist(String title) {
        hooks.getSoftAssert().assertNull(
                ecoNewsPage.findCardByTitle(title),
                "Cancelled news should NOT appear in Eco News list."
        );
    }

    @When("the user opens Create News page from Eco News")
    public void openCreateNewsFromEcoNews() {
        createNewsPage = ecoNewsPage.clickCreateNews();
        createNewsPage.waitUntilPageLoaded();
    }

    @Then("the Create News title field should be empty")
    public void titleFieldEmpty() {
        String actualTitleValue = createNewsPage.getTitleInput().getValue();
        hooks.getSoftAssert().assertEquals(
                actualTitleValue, "",
                "Title input should be empty after reopening Create News."
        );
    }

    @Then("the Create News content editor should be empty")
    public void contentEditorEmpty() {
        String actualContentValue = createNewsPage.getTextEditor().getText();
        hooks.getSoftAssert().assertTrue(
                actualContentValue == null || actualContentValue.isBlank(),
                "Content editor should be empty after reopening Create News."
        );
    }

    @When("the user clicks Continue editing in the popup")
    public void clickContinueEditing() {
        cancelNewsModal.getContinueNewsEditingModalButtonAlternative().click();
        cancelNewsModal.waitForModalClosed();
    }

    @Then("the user should stay on Create News page")
    public void stayOnCreateNews() {
        hooks.getSoftAssert().assertTrue(
                createNewsPage.getPageTitle().isDisplayed(),
                "User should stay on Create News page."
        );
    }

    @Then("the Create News title should equal {string}")
    public void titleShouldEqual(String expectedTitle) {
        String actualTitle = createNewsPage.getTitleInput().getValue();
        hooks.getSoftAssert().assertEquals(
                actualTitle, expectedTitle,
                "Title input value should remain unchanged."
        );
    }

    @Then("the Create News content should equal {string}")
    public void contentShouldEqual(String expectedContent) {
        String actualContent = createNewsPage.getTextEditor().getText();
        hooks.getSoftAssert().assertEquals(
                actualContent, expectedContent,
                "Content editor value should remain unchanged."
        );
    }

    @When("the user clicks outside cancel popup")
    public void clickOutsidePopup() {
        cancelNewsModal.closeByClickingOutsidePopUp();
    }

    @When("the user closes cancel popup via cross icon")
    public void closeViaCrossIcon() {
        cancelNewsModal.getCrossIconForCloseChangesModalAlternative().click();
        cancelNewsModal.waitForModalClosed();
    }

    @When("the user clicks header nav item {string} without navigation")
    public void clickHeaderNavItemWithoutNavigation(String navItem) {
        NavItem item = NavItem.valueOf(navItem);
        createNewsPage.getHeader().clickNavItemWithoutNavigation(item);

        cancelNewsModal = new CancelNewsModal(hooks.getDriver()).waitUntilOpened();
    }

    @Given("the Create News URL should contain {string}")
    public void createNewsUrlShouldContain(String expectedPart) {
        String initialUrl = hooks.getDriver().getCurrentUrl();
        Assert.assertNotNull(initialUrl);
        hooks.getSoftAssert().assertTrue(
                initialUrl.contains(expectedPart),
                "Precondition failed. URL: " + initialUrl
        );
    }

    @When("the user clicks header nav item {string}")
    public void clickHeaderNavItem(String navItem) {
        NavItem item = NavItem.valueOf(navItem);
        createNewsPage.getHeader().openNavItem(item);
    }

    @Then("the current URL should not contain {string}")
    public void urlShouldNotContain(String value) {
        String currentUrl = hooks.getDriver().getCurrentUrl();
        hooks.getSoftAssert().assertFalse(
                currentUrl.contains(value),
                "URL should NOT contain: " + value + ". Actual: " + currentUrl
        );
    }

    @Then("the current URL should contain {string}")
    public void urlShouldContain(String value) {
        String currentUrl = hooks.getDriver().getCurrentUrl();
        hooks.getSoftAssert().assertTrue(
                currentUrl.contains(value),
                "URL should contain: " + value + ". Actual: " + currentUrl
        );
    }

    @When("the user clicks Cancel and measures popup appearance time")
    public void clickCancelAndMeasureTime() {
        CreateNewsButtonsComponent buttons = createNewsPage.getCreateNewsButtonsComponent();

        long start = System.currentTimeMillis();
        buttons.getCancelButton().click();

        cancelNewsModal = createNewsPage.getCancelNewsModal();

        popupAppearanceMs = System.currentTimeMillis() - start;
    }

    @Then("the cancel popup should be visible")
    public void cancelPopupShouldBeVisible() {
        hooks.getSoftAssert().assertTrue(
                cancelNewsModal.isVisible(),
                "Cancel popup did not become visible."
        );
    }

    @Then("the popup should appear within {int} ms")
    public void popupShouldAppearWithinMs(int limitMs) {
        hooks.getSoftAssert().assertTrue(
                popupAppearanceMs <= limitMs,
                "Popup appeared in " + popupAppearanceMs + " ms (should be <= " + limitMs + " ms)."
        );
    }

    private String getTimestamp(String value) {
        if (value == null) return null;
        if (value.contains("{ts}")) {
            return value.replace("{ts}", String.valueOf(System.currentTimeMillis()));
        }
        return value;
    }

}
