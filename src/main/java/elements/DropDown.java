package elements;

import org.openqa.selenium.support.ui.Select;

public class DropDown extends BaseElement {

    public Select getSelect() {
        return new Select(getWrappedElement());
    }

    public void selectElementByValue(String value) {
        getSelect().selectByValue(value);
    }

    public void selectElementByIndex(int index) {
        getSelect().selectByIndex(index);
    }

    public void selectElementByVisibleText(String visibleText) {
        getSelect().selectByVisibleText(visibleText);
    }
}
