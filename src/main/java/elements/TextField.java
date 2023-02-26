package elements;


public class TextField extends BaseElement {

    public TextField clearSendText(String text) {
        clear();
        sendKeys(text);
        return this;
    }
}
