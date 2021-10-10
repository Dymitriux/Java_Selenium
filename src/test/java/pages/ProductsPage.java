package pages;

import elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitUtils;

import java.util.List;

public class ProductsPage extends BasePage {

    @FindBy(className = "shopping_cart_link")
    private Button shoppingCartLink;

    @FindBy(className = "inventory_item")
    private List<WebElement> productPageItemsList;

    public ProductsPage waitForCartLinkToBeDisplayed() {
        WaitUtils.waitForElementToBeVisible(shoppingCartLink);
        return this;
    }

    public ProductsPage printOutProducts() {
        String productName;
        for (WebElement webElement : productPageItemsList) {
            productName = webElement.findElement(By.className("inventory_item_name")).getText();
            logger.info("Product name: " + productName);
        }
        return this;
    }
}
