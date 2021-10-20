package webElementsExtensions.pageWebElementsBeta;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class PageElementLocatorHandler implements MethodInterceptor {

    private final ElementLocator locator;

    public PageElementLocatorHandler(ElementLocator locator) {
        this.locator = locator;
    }

    private static Set<String> ignoredMethods = new HashSet<>() {
        {
            add("toString");
            add("hashCode");
        }
    };

    private WebElement locateElement() {
        return locator.findElement();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        if (ignoredMethods.contains(method.getName())) {
            return methodProxy.invokeSuper(o, objects);
        }

        if (o instanceof PageElement) {
            if (!method.getName().equals("setRootElement")) {
                PageElement pageElement = (PageElement) o;

                WebElement element = locateElement();

                pageElement.setRootElement(element);
            }

            try {
                return methodProxy.invokeSuper(o, objects);
            } catch (InvocationTargetException e) {
                throw e.getCause();
            }

        } else if (o instanceof WebElement) {
            WebElement displayedElement = locateElement();

            if (displayedElement != null) {
                return method.invoke(displayedElement, objects);
            }
        }
        return null;
    }
}
