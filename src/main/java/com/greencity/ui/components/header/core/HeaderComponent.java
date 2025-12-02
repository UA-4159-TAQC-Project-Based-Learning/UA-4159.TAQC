package com.greencity.ui.components.header.core;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.components.header.actions.HeaderControls;
import com.greencity.ui.components.header.navbar.NavBar;
import com.greencity.ui.components.loginModalComponent.LoginModalComponent;
import com.greencity.ui.pages.BasePage;
import com.greencity.ui.pages.EcoNewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import com.greencity.ui.utils.NavItem;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HeaderComponent extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//img[@src='assets/img/logo.svg']")
    private WebElement logo;

    @Getter
    @FindBy(css = ".header_navigation-menu-left")
    private WebElement leftNavRoot;

    @Getter
    @FindBy(css = ".header_navigation-menu-right")
    private WebElement rightNavRoot;

    // mobile view only
    @Getter
    @FindBy(css = ".header_burger-btn .menu-icon")
    private WebElement burgerIcon;

    // displayed only after burger click (mobile)
    @FindBy(css = ".header_navigation-menu-left-col")
    private WebElement mobileMenuRoot;

    @Getter
    private final NavBar navBar;

    @Getter
    private final HeaderControls controls;

    @Getter
    private final Navigation navigation;

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        navBar = new NavBar(driver, leftNavRoot);
        navigation = new Navigation(driver, leftNavRoot);
        controls = new HeaderControls(driver, rightNavRoot);
    }

    public HomePage clickLogo() {
        clickDynamicElement(logo);
        return new HomePage(driver);
    }

    public boolean hasAuthPanel() {
        return controls.hasAuthPanel();
    }

    public boolean isLoggedIn() {
        return controls.hasUserMenu();
    }

    /**
     *
     * @param item - the navigation item to open (e.g. ECO_NEWS, EVENTS, PLACES, etc.)
     * @return - a BasePage child representing the page that should be opened
     * <p>
     * TODO: once all POMs are created, this function should return pages for each NavItem specified in NavItem enum.
     */
    public BasePage openNavItem(NavItem item) {
        navBar.open(item);

        BasePage selectedPage = switch (item) {
            case ECO_NEWS -> new EcoNewsPage(driver);
            // TODO case EVENTS -> new EventsPage(driver);
            // TODO case PLACES -> new PlacesPage(driver);
            // TODO case ABOUT_US -> new AboutUsPage(driver);
            // TODO case MY_SPACE -> {
            // TODO    if(isLoggedIn()) {
            // TODO        new ProfilePage(driver);
            // TODO    } else {
            // TODO        new LoginModal(driver, rootElement);
            // TODO    }
            // TODO }
            // TODO case UBS_COURIER -> new UbsPage(driver);
            default -> throw new IllegalArgumentException("Unknown item " + item);
        };

        return selectedPage;
    }

    public LoginModalComponent clickSignIn() {
        controls.clickSignIn();
        return new LoginModalComponent(driver);
    }

    // TODO: change return type once SignUpModal component is ready
    public void clickSignUp() {
        if (!isLoggedIn()) {
            controls.clickSignUp();
        }
    }

    public HomePage signOut() {
        if (controls.hasUserMenu()) {
            controls.userMenuComponent().signOut();
        }
        return new HomePage(driver);
    }
}