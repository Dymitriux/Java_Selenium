package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import pages.ProductsPage;

@DisplayName("Products page Tests")
public class ProductPageTests extends _TestsHooks {

    @BeforeEach
    public void beforeTest() {
        driver.manage().addCookie(new Cookie("session-username", "standard_user"));
        driver.manage().addCookie(new Cookie("session-password", "secret_sauce"));
        driver.get(testData.getInventoryUrl());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Products page products number tests")
    @Description("Print out products and confirm number of products ")
    public void printOutAndCountProductsTest() {
        ProductsPage productsPage = new ProductsPage();
        productsPage
                .printOutProducts()
                .assertNumberOfProducts(6);
    }
}
