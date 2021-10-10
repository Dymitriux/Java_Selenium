package elements;


public class Button extends BaseElement {

    public void click() {
        getWrappedElement().click();
    }
}
