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
    private static BrowserType browserType = BrowserType.CHROME;
    private static final String[] OPTIONS_ARGUMENTS = new String[]{"disable-popup-blocking", "--remote-allow-origins=*"};

    /**
     * If setBrowserType method was not called then Chrome browser will be initialized.
     * @return initialized WebDriver
     */
    public static WebDriver getWebDriver() {
        if (driver == null) {
            initDriver(browserType);
        }
        return driver;
    }

    public static void setBrowserType(BrowserType type) {
        browserType = type;
    }

    private static void initDriver(BrowserType type) {
        switch (type) {
            case EDGE -> setEdgeDriver();
            case CHROME -> setChromeDriver();
            case FIREFOX -> setFirefoxDriver();
        }
    }

    private static void setChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(OPTIONS_ARGUMENTS);
        driver = new ChromeDriver(options);
    }

    private static void setFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments(OPTIONS_ARGUMENTS);
        driver = new FirefoxDriver(options);
    }

    private static void setEdgeDriver() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments(OPTIONS_ARGUMENTS);
        driver = new EdgeDriver(options);
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
        driver = null;
    }
}
