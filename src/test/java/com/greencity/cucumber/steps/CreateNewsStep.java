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
    public  void the_title_block_should_be_visible() {
        hooks.getSoftAssert().assertTrue(new CreateNewsPage(hooks.getDriver())
                        .getTitleInputRoot()
                        .isDisplayed(),
                "Title block should be not visible");
    }
    @Then("the Title label should be visible")
    public  void the_title_label_should_be_visible() {
        boolean isVisible = new CreateNewsPage(hooks.getDriver())
                .getTitleInput()
                .getLabelElement()
                .isDisplayed();
        hooks.getSoftAssert().assertTrue(isVisible,
                "Title block should be not visible");
    }

    @Then("the Title field info should be visible")
    public  void the_title_field_info_should_be_visible() {
        boolean isVisible = new CreateNewsPage(hooks.getDriver())
                .getTitleInput()
                .getFieldInfoElement()
                .isDisplayed();
        hooks.getSoftAssert().assertTrue(isVisible,
                "Title field info should be visible");
    }

    @Then("the Title input field should be visible")
    public  void the_title_input_field_should_be_visible() {
        boolean isVisible = new CreateNewsPage(hooks.getDriver())
                .getTitleInput()
                .getFieldElement()
                .isDisplayed();
        hooks.getSoftAssert().assertTrue(isVisible,
                "Title block should be not visible");
    }










}
