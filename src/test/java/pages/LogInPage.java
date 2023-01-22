package pages;

import elements.Button;
import elements.TextField;
import elements.findByExtension.ExtendedFindBy;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends BasePage {

    @ExtendedFindBy(id = "user-name", friendlyName = "User name field")
    private TextField userNameField;

    @FindBy(id = "password")
    private TextField userPasswordField;

    @FindBy(id = "login-button")
    private Button loginButton;

    public LogInPage enterUserName(String userName) {
        System.out.println("Sending " + userName + " to " + userNameField.getElementName());
        userNameField.clearSendText(userName);
        return this;
    }

    public LogInPage enterUserPassword(String userPassword) {
        System.out.println("Sending " + userPassword + " to " + userPasswordField.getElementName());
        userPasswordField.clearSendText(userPassword);
        return this;
    }

    public ProductsPage clickOnLoginButton() {
        loginButton.click();
        return new ProductsPage();
    }
}
