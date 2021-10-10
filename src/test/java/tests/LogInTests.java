package tests;

import org.junit.jupiter.api.Test;
import pages.LogInPage;

public class LogInTests extends BaseTest {

    private static final String LOG_IN_PAGE_URL = "https://www.saucedemo.com/";

    @Test
    public void testOne() {
        driver.get(LOG_IN_PAGE_URL);

        LogInPage logInPage = new LogInPage();
        logInPage
                .enterUserName("standard_user")
                .enterUserPassword("secret_sauce")
                .clickOnLoginButton()
                .waitForCartLinkToBeDisplayed()
                .printOutProducts();
    }
}
