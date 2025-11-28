package com.greencity.ui.pages.homepage;

import com.greencity.ui.components.home.EcoNewsSectionComponent;
import com.greencity.ui.components.home.HeaderBannerComponent;
import com.greencity.ui.components.home.StatisticRowComponent;
import com.greencity.ui.components.home.SubscribeSectionComponent;
import com.greencity.ui.components.loginModalComponent.LoginModalComponent;
import com.greencity.ui.pages.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static com.greencity.ui.components.home.HeaderBannerComponent.LOGIN_MODAL_ROOT_LOCATOR;

public class HomePage extends BasePage {

    @Getter
    private final HeaderBannerComponent headerBanner;
    @Getter
    private final EcoNewsSectionComponent ecoNewsSection;
    @Getter
    private final SubscribeSectionComponent subscribeSection;
    @FindBy(css = "header#header")
    private WebElement headerBannerRoot;
    @Getter
    @FindBy(xpath = "//div[@class='stat-row']")
    private List<WebElement> statisticRowsRoot;
    private List<StatisticRowComponent> statisticRows;
    @FindBy(xpath = "//section[@id='events']")
    private WebElement ecoNewsSectionRoot;
    @FindBy(xpath = "//div[@id='subscribe']")
    private WebElement subscribeSectionRoot;
    @Getter
    @FindBy(xpath = "//a[contains(@href, '#/greenCity/news')]")
    private WebElement ecoNewsLink;
//    @FindBy(xpath = "//span[text()='Sign up ']")
//    private WebElement signUpButton;
//    @FindBy(xpath = "//img[@alt='sing in button']")
//    private WebElement signInButton;

//    public LoginModalComponent openLoginModal() {
//        signInButton.click();
//        WebElement loginModalRoot = wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_MODAL_ROOT_LOCATOR));
//        return new LoginModalComponent(driver, loginModalRoot);
//    }

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