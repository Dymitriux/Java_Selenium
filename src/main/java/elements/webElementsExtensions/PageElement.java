package elements.webElementsExtensions;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import utils.LoggingUtils;

import java.util.Arrays;
import java.util.List;

public class PageElement implements WebElement {

    private WebElement wrappedElement;
    private String elementName;
    protected Logger logger = LoggingUtils.getLogger("WebElementsExtension");

    /**
     * Name of this method should be included in ignoreMethods list in PageElementLocatorHandler class,
     * for more details check comments.
     * @param webElement passed from page element locator handlers
     */
    protected void setWrappedElement(WebElement webElement, String elementName) {
        this.wrappedElement = webElement;
        this.elementName = elementName;
    }

    protected WebElement getWrappedElement() {
        return wrappedElement;
    }

    public String getElementName() {
        return elementName;
    }

    @Override
    public void click() {
        logger.debug("Performing click on [{}] element.", elementName);
        this.wrappedElement.click();
    }

    @Override
    public void submit() {
        logger.debug("Performing submit on [{}] element.", elementName);
        wrappedElement.submit();
    }

    @Override
    public void sendKeys(CharSequence... charSequences) {
        logger.debug("Sending [{}] to [{}] field.", Arrays.toString(charSequences), elementName);
        wrappedElement.sendKeys(charSequences);
    }

    @Override
    public void clear() {
        logger.debug("Performing clear on [{}] element.", elementName);
        wrappedElement.clear();
    }

    @Override
    public String getTagName() {
        logger.debug("Getting tag from [{}] element.", elementName);
        return wrappedElement.getTagName();
    }

    @Override
    public String getAttribute(String attributeName) {
        logger.debug("Getting attribute from [{}] element.", elementName);
        return wrappedElement.getAttribute(attributeName);
    }

    @Override
    public boolean isSelected() {
        logger.debug("Checking if [{}] element is selected.", elementName);
        return wrappedElement.isSelected();
    }

    @Override
    public boolean isEnabled() {
        logger.debug("Checking if [{}] element is enabled.", elementName);
        return wrappedElement.isEnabled();
    }

    @Override
    public String getText() {
        logger.debug("Getting text from [{}] element.", elementName);
        return wrappedElement.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return wrappedElement.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return wrappedElement.findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        logger.debug("Checking if [{}] element is displayed.", elementName);
        return wrappedElement.isDisplayed();
    }

    @Override
    public Point getLocation() {
        logger.debug("Getting location of [{}] element.", elementName);
        return wrappedElement.getLocation();
    }

    @Override
    public Dimension getSize() {
        logger.debug("Getting location of [{}] element.", elementName);
        return wrappedElement.getSize();
    }

    @Override
    public Rectangle getRect() {
        logger.debug("Getting location of [{}] element.", elementName);
        return wrappedElement.getRect();
    }

    @Override
    public String getCssValue(String value) {
        logger.debug("Getting CSS value from [{}] element.", elementName);
        return wrappedElement.getCssValue(value);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        logger.debug("Getting screenshot on [{}] element.", elementName);
        return wrappedElement.getScreenshotAs(outputType);
    }
}
