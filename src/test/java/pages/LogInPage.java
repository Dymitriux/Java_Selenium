package pages;

import elements.Button;
import elements.TextField;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends BasePage {

    @FindBy(id = "user-name")
    private TextField userNameField;

    @FindBy(id = "password")
    private TextField userPasswordField;

    @FindBy(id = "login-button")
    private Button loginButton;

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
}
