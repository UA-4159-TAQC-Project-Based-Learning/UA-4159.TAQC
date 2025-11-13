package com.greencity.ui.components.eco_news;

import com.greencity.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class EcoNewsListComponent extends BaseComponent {

    @FindBy(css = ".list-view-li-active")
    private List<WebElement> allListCards;

    public EcoNewsListComponent(WebDriver driver, WebElement rootElement){
        super(driver, rootElement);
    }

    public List<EcoNewsListComponent> getCardComponents(){
        return allListCards.stream()
                .map(allListCards -> new EcoNewsListComponent(driver, allListCards))
                .collect(Collectors.toList());
    }

}