package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LogInPage;

public class LoginPageTests extends _TestsHooks {

    @BeforeEach
    public void beforeTest() {
        driver.get(LOG_IN_PAGE_URL);
    }

    @Test
    public void correctDataLoginTest() {
        LogInPage logInPage = new LogInPage();
        logInPage
                .enterUserName("standard_user")
                .enterUserPassword("secret_sauce")
                .clickOnLoginButton()
                .assertCorrectLogin();
    }

    @Test
    public void incorrectDataLoginTest() {
        LogInPage logInPage = new LogInPage();
        logInPage
                .enterUserName("not_existing_user")
                .enterUserPassword("secret_sauce")
                .clickOnLoginButton();
        logInPage.assertErrorMessage("Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void lockedUserLoginTest() {
        LogInPage logInPage = new LogInPage();
        logInPage
                .enterUserName("locked_out_user")
                .enterUserPassword("secret_sauce")
                .clickOnLoginButton();
        logInPage.assertErrorMessage("Epic sadface: Sorry, this user has been locked out.");
    }
}
