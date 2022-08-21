package elements;


public class TextField extends BaseElement {

    public TextField clearSendText(String text) {
        getWrappedElement().clear();
        getWrappedElement().sendKeys(text);
        return this;
    }
}
