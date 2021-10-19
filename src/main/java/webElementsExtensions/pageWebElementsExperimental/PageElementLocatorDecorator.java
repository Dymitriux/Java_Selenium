package webElementsExtensions.pageWebElementsExperimental;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import java.lang.reflect.Field;

public class PageElementLocatorDecorator implements FieldDecorator {

    final DefaultFieldDecorator defaultFieldDecorator;
    final SearchContext searchContext;

    public PageElementLocatorDecorator(SearchContext searchContext) {
        this.searchContext = searchContext;
        this.defaultFieldDecorator = new DefaultFieldDecorator(new DefaultElementLocatorFactory(searchContext));
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        if (PageElementExample2.class.isAssignableFrom(field.getType()) && field.isAnnotationPresent(FindBy.class)) {
            return getEnhancedObject(field.getType(), getElementHandler(field));
        } else {
            return defaultFieldDecorator.decorate(loader, field);
        }
    }

    public Object getEnhancedObject(Class clazz, MethodInterceptor methodInterceptor) {
        Enhancer e = new Enhancer();
        e.setSuperclass(clazz);
        e.setCallback(methodInterceptor);
        return e.create();
    }

    private PageElementLocatorHandler getElementHandler(Field field) {
        return new PageElementLocatorHandler(getLocator(field));
    }

//    private PageElementLocator.ElementHandler getElementHandler(Field field) {
//        return new PageElementLocator.ElementHandler(field, getLocator(field));
//    }

    private ElementLocator getLocator(Field field) {
        return new DefaultElementLocatorFactory(searchContext).createLocator(field);
    }
}
