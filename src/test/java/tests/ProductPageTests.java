package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import pages.ProductsPage;

@DisplayName("Products page Tests")
public class ProductPageTests extends _TestsHooks {

    @BeforeEach
    public void beforeTest() {
        driver.get(LOG_IN_PAGE_URL);
        driver.manage().addCookie(new Cookie("session-username", "standard_user"));
        driver.manage().addCookie(new Cookie("session-password", "secret_sauce"));
        driver.get(LOG_IN_PAGE_URL + "/inventory.html");
    }

    @Test
    @Description("Print out products")
    public void printOutProductsTest() {
        ProductsPage productsPage = new ProductsPage();
        productsPage
                .printOutProducts();
    }
}
