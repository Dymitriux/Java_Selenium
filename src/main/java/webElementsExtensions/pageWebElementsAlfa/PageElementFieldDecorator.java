package webElementsExtensions.pageWebElementsAlfa;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import java.lang.reflect.Field;

public class PageElementFieldDecorator implements FieldDecorator {

    final DefaultFieldDecorator defaultFieldDecorator;
    final SearchContext searchContext;
//    private final WebDriver webDriver;

    public PageElementFieldDecorator(SearchContext searchContext, WebDriver webDriver) {
        this.searchContext = searchContext;
//        this.webDriver = webDriver;
        defaultFieldDecorator = new DefaultFieldDecorator(new DefaultElementLocatorFactory(searchContext));
    }

    public Object getEnhancedObject(Class clazz, MethodInterceptor methodInterceptor) {
        Enhancer e = new Enhancer();
        e.setSuperclass(clazz);
        e.setCallback(methodInterceptor);
        return e.create();
    }

    public Object decorate(ClassLoader loader, Field field) {
        if (PageElementAlfa.class.isAssignableFrom(field.getType()) && field.isAnnotationPresent(FindBy.class)) {
            return getEnhancedObject(field.getType(), getElementHandler(field));
        } else {
            return defaultFieldDecorator.decorate(loader, field);
        }
    }

//    private PageElementLocator.ElementHandler getElementHandler(Field field) {
//        return new PageElementLocator.ElementHandler(field, getLocator(field), webDriver);
//    }

    private PageElementLocator.ElementHandler getElementHandler(Field field) {
        return new PageElementLocator.ElementHandler(field, getLocator(field));
    }

    private ElementLocator getLocator(Field field) {
        return new DefaultElementLocatorFactory(searchContext).createLocator(field);
    }
}
