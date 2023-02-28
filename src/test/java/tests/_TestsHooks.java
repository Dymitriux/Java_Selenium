package tests;

import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import utils.BrowserType;
import utils.LoggingUtils;
import utils.WebDriverUtils;

public class _TestsHooks {

    public WebDriver driver;
    protected static final String LOG_IN_PAGE_URL = "https://www.saucedemo.com";
    protected Logger logger = LoggingUtils.getLogger(this.getClass());

    @BeforeAll
    static void beforeAll() {
        /* Available options: CHROME, EDGE, FIREFOX */
        WebDriverUtils.setBrowserType(BrowserType.CHROME);
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
