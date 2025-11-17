package com.greencity.ui.pages.homepage;

import com.greencity.ui.components.home.EcoNewsSectionComponent;
import com.greencity.ui.components.home.HeaderBannerComponent;
import com.greencity.ui.components.home.StatisticRowComponent;
import com.greencity.ui.components.home.SubscribeSectionComponent;
import com.greencity.ui.pages.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {

    @Getter
    private final HeaderBannerComponent headerBanner;
    @FindBy(css = "header#header")
    private WebElement headerBannerRoot;
    @Getter
    @FindBy(xpath = "//div[@class='stat-row']")
    private List<WebElement> statisticRowsRoot;
    private List<StatisticRowComponent> statisticRows;

    @FindBy(xpath = "//section[@id='events']")
    private WebElement ecoNewsSectionRoot;
    @Getter
    private EcoNewsSectionComponent ecoNewsSection;

    @FindBy(xpath = "//div[@id='subscribe']")
    private WebElement subscribeSectionRoot;
    @Getter
    private SubscribeSectionComponent subscribeSection;


    public HomePage(WebDriver driver) {
        super(driver);
        this.headerBanner = new HeaderBannerComponent(driver, headerBannerRoot);
        this.ecoNewsSection = new EcoNewsSectionComponent(driver, ecoNewsSectionRoot);
        this.subscribeSection = new SubscribeSectionComponent(driver, subscribeSectionRoot);
    }

    public List<StatisticRowComponent> getAllStatisticRows() {
        if (statisticRows == null) {
            statisticRows = statisticRowsRoot.stream().map(root -> new StatisticRowComponent(driver, root)).toList();
        }
        return statisticRows;
    }

    public StatisticRowComponent getStatisticRow(int index) {
        return getAllStatisticRows().get(index);
    }
}