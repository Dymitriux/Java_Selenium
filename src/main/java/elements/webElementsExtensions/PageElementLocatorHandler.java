package elements.webElementsExtensions;

import javassist.util.proxy.MethodHandler;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class PageElementLocatorHandler implements MethodHandler {

    private final ElementLocator locator;
    /* Methods to be skipped on invocation to secure that infinite loop will not occur on method invocation */
    private final List<String> ignoreMethods = Arrays.asList("setWrappedElement", "hashCode", "toString");

    public PageElementLocatorHandler(ElementLocator locator) {
        this.locator = locator;
    }

    @Override
    public Object invoke(Object o, Method method, Method proceed, Object[] objects) throws Throwable {
        if (ignoreMethods.contains(method.getName())) {
            return proceed.invoke(o, objects);
        }

        WebElement element = locator.findElement();

        PageElement pageElement = (PageElement) o;
        /* Name of this method (setWrappedElement) need to be included in ignoreMethods list
        to secure that infinite loop will not occur on method invocation */
        pageElement.setWrappedElement(element);

        try {
            return proceed.invoke(o, objects);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}
