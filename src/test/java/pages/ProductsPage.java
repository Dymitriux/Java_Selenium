package pages;

import elements.Button;
import elements.TextField;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static utils.WaitUtils.waitForElementToBeVisible;

public class ProductsPage extends _PageInit {

    @FindBy(className = "shopping_cart_link")
    private Button shoppingCartLink;

    @FindBy(className = "inventory_item")
    private List<WebElement> productPageItemsList;

    @FindBy(className = "inventory_item_name")
    private List<TextField> productPageItemsNameList;

    @FindBy(className = "inventory_item_name")
    private TextField firstProduct;

    public void assertCorrectLogin() {
        assertDoesNotThrow(() -> waitForElementToBeVisible(shoppingCartLink, 3),
                "Product page after login action was not displayed.");
    }

    public ProductsPage printOutProducts() {
        waitForElementToBeVisible(firstProduct);
        for (TextField productName : productPageItemsNameList) {
            logger.info("Product name: {}", productName.getText());
        }
        return this;
    }
}
