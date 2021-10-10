package webElementsExtensions.customWebElements.customElements.concreteElements;

import webElementsExtensions.customWebElements.customElements.superElements.CustomWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * A label.
 **/
public class WebLabel extends CustomWebElement {

    /**
     * Constructor.
     *
     * @param webDriver The webDriver usd to interact with the webbrowser.
     * @param by        The locator used to identify the element(s) on the website.
     **/
    public WebLabel(WebDriver webDriver, By by) {
        super(webDriver, by);
    }

    /* ----- Methods ----- */

    /**
     * Returns the text of the label.
     **/
    public String getText() {
        return getWebDriver().findElement(getBy()).getText();
    }
}
