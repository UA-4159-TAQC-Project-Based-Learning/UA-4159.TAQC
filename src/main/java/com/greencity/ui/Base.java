package com.greencity.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class Base {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor threadJs;
    protected Actions actions;

    public Base(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.threadJs = (JavascriptExecutor) driver;
        this.actions = new Actions(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Step("Scroll to the element")
    public void scrollToElement(WebElement element) {
        actions.moveToElement(element).perform();
    }

    @Step("Scroll to the element")
    public void scrollToElementJs(WebElement element) {
        waitUntilElementVisible(element);
        try {
            threadJs.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
        } catch (Exception e) {
            throw e;
        }
        waitUntilElementClickable(element);
    }

    @Step("Scroll to the middle of the page")
    public void scrollToMiddlePage() {
        Number startY = (Number) threadJs.executeScript("return window.pageYOffset;");
        threadJs.executeScript("window.scrollTo(0, document.body.scrollHeight/2)");

        wait.until(driver -> {
            Number currentY = (Number) threadJs.executeScript("return window.pageYOffset;");
            return currentY.doubleValue() != startY.doubleValue();
        });
    }

    @Step("Scroll to the end of the page")
    public void scrollToEndOfPage() {
        sleep(1000);
        try {
            threadJs.executeScript("window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' });");
        } catch (Exception e) {

            throw e;
        }
    }

    @Step("Check if content is truncated or overflows")
    protected boolean isContentTruncatedOrOverflow(WebElement element) {
        String script = "var element = arguments[0];" + "var computedStyle = window.getComputedStyle(element);" + "var isOverflowing = element.scrollHeight > element.clientHeight || element.scrollWidth > element.clientWidth;" + "var isTextOverflowing = computedStyle.overflow === 'hidden' || computedStyle.textOverflow === 'ellipsis' || computedStyle.whiteSpace === 'nowrap';" + "return isOverflowing && !isTextOverflowing;";

        Boolean isOverflowing;

        try {
            isOverflowing = (Boolean) threadJs.executeScript(script, element);
        } catch (Exception ex) {

            throw ex;
        }
        return isOverflowing != null && isOverflowing;
    }

    @Step("Click on the element")
    protected void clickDynamicElement(WebElement element) {
        waitUntilElementVisible(element);
        try {
            threadJs.executeScript("arguments[0].click();", element);
        } catch (Exception e) {

            throw e;
        }
    }

    public void sleep(long millisSeconds) {
        try {
            Thread.sleep(millisSeconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void waitUntilElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilElementInvisible(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitUntilElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    @Step("Refresh the page")
    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void waitUntilAllElementsVisible(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    @Step("Wait until page is loaded")
    public void waitUntilPageLoaded() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
    }

    public static boolean hasClass(WebElement element, String className) {
        if (element == null) {
            return false;
        }
        String searchingClass = element.getAttribute("class");
        return searchingClass != null && searchingClass.contains(className);
    }

    // need it for tests where elements are dynamically changing
    public WebElement firstDisplayed(List<WebElement> elements) {
        if (elements == null || elements.isEmpty()) {
            throw new NoSuchElementException("No elements found for the given locator.");
        }
        for (WebElement el : elements) {
            try {
                if (el.isDisplayed()) return el;
            } catch (StaleElementReferenceException ignored) {
            }
        }
        return elements.get(0);
    }
}