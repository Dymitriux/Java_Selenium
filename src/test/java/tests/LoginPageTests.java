package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LogInPage;

@DisplayName("Login page Tests")
public class LoginPageTests extends _TestsHooks {

    @BeforeEach
    public void beforeTest() {
        driver.get(LOG_IN_PAGE_URL);
    }

    @Test
    @DisplayName("Correct data login")
    @Description("Log in with correct user")
    @Severity(SeverityLevel.BLOCKER)
    public void correctDataLoginTest() {
        LogInPage logInPage = new LogInPage();
        logInPage
                .enterUserName("standard_user")
                .enterUserPassword("secret_sauce")
                .clickOnLoginButton()
                .assertCorrectLogin();
    }

    @Test
    @Description("Try to log in with incorrect user")
    @Severity(SeverityLevel.NORMAL)
    @Disabled
    public void incorrectDataLoginTest() {
        LogInPage logInPage = new LogInPage();
        logInPage
                .enterUserName("not_existing_user")
                .enterUserPassword("secret_sauce")
                .clickOnLoginButton();
        logInPage.assertErrorMessage("Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    @Description("Try to log in with locked user")
    @Severity(SeverityLevel.NORMAL)
    public void lockedUserLoginTest() {
        LogInPage logInPage = new LogInPage();
        logInPage
                .enterUserName("locked_out_user")
                .enterUserPassword("secret_sauce")
                .clickOnLoginButton();
        logInPage.assertErrorMessage("Epic sadface: Sorry, this user has been locked out.");
    }
}
