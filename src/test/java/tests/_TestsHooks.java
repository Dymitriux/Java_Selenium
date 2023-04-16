package tests;

import helpers.TestStatus;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import utils.BrowserType;
import utils.PropertiesReader;
import utils.Utils;
import utils.WebDriverUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class _TestsHooks {

    protected WebDriver driver;
    protected PropertiesReader testData;

    @RegisterExtension
    TestStatus testStatus = new TestStatus();

    @BeforeAll
    public void beforeAll() {
        /* Available options: CHROME, EDGE, FIREFOX */
        WebDriverUtils.setBrowserType(BrowserType.CHROME);
        testData = new PropertiesReader("src/test/resources/TestData.properties");
    }

    @BeforeEach
    public void beforeEach() {
        driver = WebDriverUtils.getWebDriver();
        driver.manage().window().maximize();
        driver.get(testData.getBaseUrl());
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
