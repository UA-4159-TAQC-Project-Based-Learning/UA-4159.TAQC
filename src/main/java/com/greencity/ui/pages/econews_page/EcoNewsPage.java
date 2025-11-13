package com.greencity.ui.pages.econews_page;

import com.greencity.ui.components.eco_news.EcoNewsListCardComponent;
import com.greencity.ui.components.eco_news.EcoNewsListComponent;
import com.greencity.ui.pages.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EcoNewsPage extends BasePage {

    @Getter
    protected EcoNewsListComponent ecoNewsListComponent;

    @Getter
    protected EcoNewsListCardComponent ecoNewsListCardComponent;

    @FindBy(css = ".list")
    private WebElement ecoNewsListRoot;

    public EcoNewsPage(WebDriver driver){
        super(driver);
        this.ecoNewsListComponent = new EcoNewsListComponent(driver, ecoNewsListRoot);

    }

    public List<EcoNewsListCardComponent> getAllCards(){
        return ecoNewsListComponent.getCardComponents();
    }


}
