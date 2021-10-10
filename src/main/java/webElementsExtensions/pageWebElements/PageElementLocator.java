package webElementsExtensions.pageWebElements;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import utils.LoggingUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class PageElementLocator {

    public static class ElementHandler implements MethodInterceptor {

        public Logger logger = LoggingUtils.getLogger();

        private final ElementLocator locator;
//        private WebDriver webDriver;
        private Field field;

        private static Set<String> ignoredMethods = new HashSet<>() {
            {
                add("toString");
                add("hashCode");
            }
        };

//        public ElementHandler(Field field, ElementLocator locator, WebDriver webDriver) {
//            this.locator = locator;
//            this.webDriver = webDriver;
//            this.field = field;
//            logger.debug("created handler for [{}]", field);
//        }

        public ElementHandler(Field field, ElementLocator locator) {
            this.locator = locator;
            this.field = field;
            logger.debug("created handler for [{}]", field);
        }

        private WebElement locateElement() {
            return locator.findElement();
        }

        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

            if (ignoredMethods.contains(method.getName())) {
                return methodProxy.invokeSuper(o, objects);
            }

            logger.debug("[{}] intercepted method [{}] on object [{}]", field, method, o);
            if (o instanceof PageElement) {
                if (!method.getName().equals("setRootElement") && !method.getName().equals("setWebDriver")) {
                    PageElement pageElement = (PageElement) o;

                    WebElement element = locateElement();

                    pageElement.setRootElement(element);
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
                    logger.info("found first displayed. invoking method");
                    return method.invoke(displayedElement, objects);
                } else {
                    logger.info("unable to detect first displayed");
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return "ElementHandler{" +
                    "field=" + field +
                    '}';
        }
    }
}
