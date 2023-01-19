package elements.webElementsExtensions;

import org.openqa.selenium.*;

import java.util.List;

public class PageElement implements WebElement {

    private WebElement wrappedElement;
    private String elementName;

    /**
     * Name of this method should be included in ignoreMethods list in PageElementLocatorHandler class,
     * for more details check comments.
     * @param webElement passed from page element locator handlers
     */
    protected void setWrappedElement(WebElement webElement, String elementName) {
        this.wrappedElement = webElement;
        this.elementName = elementName != null ? elementName : "Element is in list";
    }

    protected WebElement getWrappedElement() {
        return wrappedElement;
    }

    public String getElementName() {
        return elementName;
    }

    @Override
    public void click() {
        this.wrappedElement.click();
    }

    @Override
    public void submit() {
        wrappedElement.submit();
    }

    @Override
    public void sendKeys(CharSequence... charSequences) {
        wrappedElement.sendKeys(charSequences);
    }

    @Override
    public void clear() {
        wrappedElement.clear();
    }

    @Override
    public String getTagName() {
        return wrappedElement.getTagName();
    }

    @Override
    public String getAttribute(String attributeName) {
        return wrappedElement.getAttribute(attributeName);
    }

    @Override
    public boolean isSelected() {
        return wrappedElement.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return wrappedElement.isEnabled();
    }

    @Override
    public String getText() {
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
        return wrappedElement.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return wrappedElement.getLocation();
    }

    @Override
    public Dimension getSize() {
        return wrappedElement.getSize();
    }

    @Override
    public Rectangle getRect() {
        return wrappedElement.getRect();
    }

    @Override
    public String getCssValue(String value) {
        return wrappedElement.getCssValue(value);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return wrappedElement.getScreenshotAs(outputType);
    }
}
