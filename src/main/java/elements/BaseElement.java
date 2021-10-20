package elements;

import webElementsExtensions.pageWebElementsBeta.PageElement;

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
}
