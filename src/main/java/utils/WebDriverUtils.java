package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverUtils {

    private static WebDriver driver;

    /**
     * @return initialized WebDriver, if init was not performed then chrome browser will be used as default
     */
    public static WebDriver getWebDriver() {
        if (driver == null) {
            getChromeDriver();
        }
        return driver;
    }

    /**
     * Method used to init driver, if this method will be skipped/removed from flow then chrome browser will be used as default
     *
     * @param type type of browser
     */
    public static void initDriver(BrowserType type) {
        switch (type) {
            case EDGE -> getEdgeDriver();
            case CHROME -> getChromeDriver();
            case FIREFOX -> getFirefoxDriver();
        }
    }

    private static void getChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-popup-blocking");
        driver = new ChromeDriver(options);
    }

    private static void getFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("disable-popup-blocking");
        driver = new FirefoxDriver(options);
    }

    private static void getEdgeDriver() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("disable-popup-blocking");
        driver = new EdgeDriver(options);
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
        driver = null;
    }
}
