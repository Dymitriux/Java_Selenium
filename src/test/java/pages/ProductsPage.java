package pages;

import elements.Button;
import elements.TextField;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitUtils;

import java.util.List;

public class ProductsPage extends BasePage {

    @FindBy(className = "shopping_cart_link")
    private Button shoppingCartLink;

    @FindBy(className = "inventory_item")
    private List<WebElement> productPageItemsList;

    @FindBy(className = "inventory_item_name")
    private List<TextField> productPageItemsNameList;

    public ProductsPage waitForCartLinkToBeDisplayed() {
        WaitUtils.waitForElementToBeVisible(shoppingCartLink);
        return this;
    }

    public ProductsPage printOutProducts() {
        for (TextField productName : productPageItemsNameList) {
            logger.info("Product name: " + productName.getText());
        }
        return this;
    }
}
