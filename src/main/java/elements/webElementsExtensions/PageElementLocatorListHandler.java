package elements.webElementsExtensions;

import javassist.util.proxy.MethodHandler;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class PageElementLocatorListHandler implements MethodHandler {

    private final ElementLocator locator;
    private final String elementName;
    private Class<? extends PageElement> listType;
    private int counter;

    public PageElementLocatorListHandler(ElementLocator locator, Class<? extends PageElement> listType, String elementName) {
        this.locator = locator;
        this.listType = listType;
        this.elementName = elementName;
    }

    @Override
    public Object invoke(Object o, Method method, Method proceed, Object[] objects) throws Throwable {
        List<PageElement> wrappedList = new ArrayList<>();

        for (WebElement element : locator.findElements()) {
            counter ++;
            PageElement instanceOf = createInstanceOf(element);
            wrappedList.add(instanceOf);
        }

        try {
            return method.invoke(wrappedList, objects);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    @SuppressWarnings("unchecked")
    private <T extends PageElement> T createInstanceOf(WebElement element)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        PageElement pageElement = listType.getConstructor().newInstance();
        pageElement.setWrappedElement(element, elementName + " " + counter);

        return (T) pageElement;
    }
}
