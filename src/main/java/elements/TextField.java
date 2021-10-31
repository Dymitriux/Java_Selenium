package elements;


import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class TextField extends BaseElement {

    public TextField clearSendText(String text) {
        getWrappedElement().clear();
        getWrappedElement().sendKeys(text);
        return this;
    }

    public List<String> asStringList() {
        return getWrappedElementList().stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
