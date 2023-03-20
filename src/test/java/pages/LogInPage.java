package pages;

import elements.Button;
import elements.TextField;
import elements.findByExtension.ExtendedFindBy;
import org.openqa.selenium.support.FindBy;
import utils.WaitUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogInPage extends _PageInit {

    @ExtendedFindBy(id = "user-name", friendlyName = "User name field")
    private TextField userNameField;

    @FindBy(id = "password")
    private TextField userPasswordField;

    @FindBy(id = "login-button")
    private Button loginButton;

    @FindBy(className = "error-message-container")
    private TextField failedLoginMessage;

    public LogInPage enterUserName(String userName) {
        userNameField.clearSendText(userName);
        return this;
    }

    public LogInPage enterUserPassword(String userPassword) {
        userPasswordField.clearSendText(userPassword);
        return this;
    }

    public ProductsPage clickOnLoginButton() {
        loginButton.click();
        return new ProductsPage();
    }

    public void assertErrorMessage(String expectedErrorMessage) {
        String actualErrorMessage = WaitUtils.waitForElementToBeVisible(failedLoginMessage).getText();
        assertEquals(expectedErrorMessage, actualErrorMessage, "Actual error message is not equal to expected.");
    }
}
