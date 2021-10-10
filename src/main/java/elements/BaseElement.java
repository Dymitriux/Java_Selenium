package elements;

import webElementsExtensions.pageWebElements.PageElement;
import org.openqa.selenium.WebElement;

public class BaseElement extends PageElement {

    public boolean isElementDisplayed() {
        try {
            return getWrappedElement().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementSelected() {
        try {
            return getWrappedElement().isSelected();
        } catch (Exception e) {
            return false;
        }
    }

//    protected WebElement getWrappedElement() {
//        return wrappedElement;
//    }
}
