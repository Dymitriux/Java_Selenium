package elements;

import webElementsExtensions.pageWebElementsExperimental.PageElementExample2;

public class BaseElement extends PageElementExample2 {

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
