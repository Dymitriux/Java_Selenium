package tests;

import helpers.TestStatus;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import utils.BrowserType;
import utils.LoggingUtils;
import utils.Utils;
import utils.WebDriverUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class _TestsHooks {

    public WebDriver driver;
    protected static final String LOG_IN_PAGE_URL = "https://www.saucedemo.com";
    protected Logger logger = LoggingUtils.getLogger(this.getClass());

    @RegisterExtension
    TestStatus testStatus = new TestStatus();

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
    public void afterEach(TestInfo testInfo) throws IOException {
        if (testStatus.isFailed) {
            String path = Utils.takeScreenshot(testInfo.getDisplayName(), driver);
            Allure.attachment("screenshot", Files.newInputStream(Paths.get(path)));
        }
        WebDriverUtils.quitDriver();
    }
}
