package webElementsExtensions.pageWebElementsBeta;

import org.openqa.selenium.*;

import java.util.List;

public abstract class PageElement implements WebElement {

    private WebElement wrappedElement;

    public void setRootElement(WebElement rootElement) {
        this.wrappedElement = rootElement;
    }

    protected WebElement getWrappedElement() {
        return this.wrappedElement;
    }

    @Override
    public void click() {
        this.wrappedElement.click();
    }

    @Override
    public void submit() {
        this.wrappedElement.submit();
    }

    @Override
    public void sendKeys(CharSequence... charSequences) {
        this.wrappedElement.sendKeys(charSequences);
    }

    @Override
    public void clear() {
        this.wrappedElement.clear();
    }

    @Override
    public String getTagName() {
        return this.wrappedElement.getTagName();
    }

    @Override
    public String getAttribute(String attributeName) {
        return this.wrappedElement.getAttribute(attributeName);
    }

    @Override
    public boolean isSelected() {
        return this.wrappedElement.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return this.wrappedElement.isEnabled();
    }

    @Override
    public String getText() {
        return this.wrappedElement.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return this.wrappedElement.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return this.wrappedElement.findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return this.wrappedElement.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return this.wrappedElement.getLocation();
    }

    @Override
    public Dimension getSize() {
        return this.wrappedElement.getSize();
    }

    @Override
    public Rectangle getRect() {
        return this.wrappedElement.getRect();
    }

    @Override
    public String getCssValue(String value) {
        return this.wrappedElement.getCssValue(value);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return this.wrappedElement.getScreenshotAs(outputType);
    }
}
