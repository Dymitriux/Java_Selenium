package webElementsExtensions.customWebElements.customElements.concreteElements;

import webElementsExtensions.customWebElements.customElements.superElements.CustomWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * A button.
 **/
public class WebButton extends CustomWebElement {

    /**
     * Constructor.
     *
     * @param webDriver The webdriver usd to interact with the webbrowser.
     * @param by        The locator used to identify the element(s) on the website.
     **/
    public WebButton(WebDriver webDriver, By by) {
        super(webDriver, by);
    }

    /**
     * Clicks on the button.
     **/
    public void click() {
        getWebDriver().findElement(getBy()).click();
    }
}
