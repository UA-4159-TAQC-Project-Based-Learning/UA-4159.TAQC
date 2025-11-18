package com.greencity.ui.pages;

import com.greencity.ui.components.EcoNewsListCardComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class EcoNewsPage extends BasePage {


    @Getter
    protected EcoNewsListCardComponent ecoNewsListCardComponent;

    @FindBy(css = ".eco-news_list-view-wrp")
    private List<WebElement> ecoNewsListRoots;

    public EcoNewsPage(WebDriver driver) {
        super(driver);

    }

    public List<EcoNewsListCardComponent> getAllCards() {
        return ecoNewsListRoots.stream()
                .map(root -> new EcoNewsListCardComponent(driver, root))
                .collect(Collectors.toList());
    }

    public EcoNewsListCardComponent findCardByTitle(String title) {
        return getAllCards().stream()
                .filter(card -> card.getTitleText().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);

    }

}
