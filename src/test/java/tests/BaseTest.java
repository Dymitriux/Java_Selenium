package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import utils.BrowserType;
import utils.WebDriverUtils;

public class BaseTest {

    public WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        /* Available options: CHROME, EDGE, FIREFOX */
        WebDriverUtils.setBrowserType(BrowserType.EDGE);
    }

    @BeforeEach
    public void beforeEach() {
        driver = WebDriverUtils.getWebDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void afterEach() {
        WebDriverUtils.quitDriver();
    }
}
