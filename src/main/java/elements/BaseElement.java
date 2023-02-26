package elements;

import elements.webElementsExtensions.PageElement;

public class BaseElement extends PageElement {

    public boolean isElementDisplayed() {
        try {
            return isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementSelected() {
        try {
            return isSelected();
        } catch (Exception e) {
            return false;
        }
    }
}
