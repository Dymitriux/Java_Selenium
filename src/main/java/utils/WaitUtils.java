package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class WaitUtils {

    private static WebDriver driver = WebDriverUtils.getWebDriver();

    private static WebDriverWait getWebDriverWait() {
        return getWebDriverWaitWithTimeout(30);
    }

    private static WebDriverWait getWebDriverWaitWithTimeout(long timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    public static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static WebElement waitForElementToBeVisible(WebElement webElement) {
        return getWebDriverWait().until(ExpectedConditions.visibilityOf(webElement));
    }

    public static WebElement waitForElementToBeVisible(By byLocator) {
        return getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(byLocator));
    }

    public static WebElement waitForElementToBeVisible(WebElement webElement, long timeout) {
        return getWebDriverWaitWithTimeout(timeout).until(ExpectedConditions.visibilityOf(webElement));
    }

    public static WebElement waitForElementToBeVisible(By byLocator, long timeout) {
        return getWebDriverWaitWithTimeout(timeout).until(ExpectedConditions.visibilityOfElementLocated(byLocator));
    }
}
