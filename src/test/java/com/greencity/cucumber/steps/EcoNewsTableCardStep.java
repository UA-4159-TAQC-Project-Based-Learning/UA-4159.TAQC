package com.greencity.cucumber.steps;

import com.greencity.ui.components.eco_news.EcoNewsTableCardComponent;
import com.greencity.ui.components.newsFilter.FilterName;
import com.greencity.ui.components.newsFilter.NewsFilterComponent;
import com.greencity.ui.pages.EcoNewsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

public class EcoNewsTableCardStep {
    private Hooks hooks;

    public EcoNewsTableCardStep(Hooks hooks) {
        this.hooks = hooks;
    }

    @When("user clicks on News tag on the Create News Page")
    public void user_clicks_on_news_tag_on_the_create_news_page() {
        EcoNewsPage ecoNewsPage = new EcoNewsPage(hooks.getDriver());
        NewsFilterComponent newsFilterComponent = ecoNewsPage.getNewsFilterComponent();
        boolean clicked = newsFilterComponent.clickFilterByName(FilterName.NEWS);
        hooks.getSoftAssert().assertTrue(clicked, "News tag was not clicked");
    }

    @Then("Eco News table card is populated on the Create News Page")
    public void eco_news_table_card_is_populated_on_the_create_news_page() {
        EcoNewsPage ecoNewsPage = new EcoNewsPage(hooks.getDriver());
        List<EcoNewsTableCardComponent> allTableCards = ecoNewsPage.getAllTableCards();
        hooks.getSoftAssert().assertTrue(allTableCards.size() > 0,
                "Eco News table card list is empty on the Create News Page");
    }

    @Then("Eco News table card title is populated for all items")
    public void eco_news_table_card_title_is_populated_for_all_items() {
        EcoNewsPage ecoNewsPage = new EcoNewsPage(hooks.getDriver());
        List<EcoNewsTableCardComponent> allTableCards = ecoNewsPage.getAllTableCards();
        for (EcoNewsTableCardComponent tableCard : allTableCards) {
            String title = tableCard.getTitle();
            hooks.getSoftAssert().assertNotNull(title);
        }
    }

    @Then("Eco News table card author is populated for all items")
    public void eco_news_table_card_author_is_populated_for_all_items() {
        EcoNewsPage ecoNewsPage = new EcoNewsPage(hooks.getDriver());
        List<EcoNewsTableCardComponent> allTableCards = ecoNewsPage.getAllTableCards();
        for (EcoNewsTableCardComponent tableCard : allTableCards) {
            String author = tableCard.getAuthor();
            hooks.getSoftAssert().assertNotNull(author);
        }
    }

    @Then("Eco News table card date is populated for all items")
    public void eco_news_table_card_date_is_populated_for_all_items() {
        EcoNewsPage ecoNewsPage = new EcoNewsPage(hooks.getDriver());
        List<EcoNewsTableCardComponent> allTableCards = ecoNewsPage.getAllTableCards();
        for (EcoNewsTableCardComponent tableCard : allTableCards) {
            String dateCreated = tableCard.getDateCreated();
            hooks.getSoftAssert().assertNotNull(dateCreated, "News table card date was not populated");
        }
    }

    @Then("Eco News table card text is populated for all items")
    public void eco_news_table_card_text_is_populated_for_all_items() {
        EcoNewsPage ecoNewsPage = new EcoNewsPage(hooks.getDriver());
        List<EcoNewsTableCardComponent> allTableCards = ecoNewsPage.getAllTableCards();
        for (EcoNewsTableCardComponent tableCard : allTableCards) {
            String text = tableCard.getText();
            hooks.getSoftAssert().assertNotNull(text, "News table card text was not populated for all items");
        }
    }
}