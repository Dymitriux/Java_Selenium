package webElementsExtensions.pageWebElementsExperimental;

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

    // Inject the thing that can lookup a specific element at a later time
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

//        logger.debug("[{}] intercepted method [{}] on object [{}]", field, method, o);
        if (o instanceof PageElementExample2) {
            if (!method.getName().equals("setRootElement") && !method.getName().equals("setWebDriver")) {
                PageElementExample2 pageElementExample2 = (PageElementExample2) o;

                WebElement element = locateElement();

                pageElementExample2.setRootElement(element);
//                    pageElement.setWebDriver(webDriver);
            }

            try {
                return methodProxy.invokeSuper(o, objects);
            } catch (InvocationTargetException e) {
                throw e.getCause();
            }

        } else if (o instanceof WebElement ) {// only handle first displayed
            WebElement displayedElement = locateElement();

            if (displayedElement != null) {
//                logger.info("found first displayed. invoking method");
                return method.invoke(displayedElement, objects);
            } else {
//                logger.info("unable to detect first displayed");
            }
        }
        return null;
    }
}
