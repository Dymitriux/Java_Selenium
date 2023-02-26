package elements.webElementsExtensions;

import elements.findByExtension.ExtendedFindBy;
import exceptions.NewInstanceException;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PageElementLocatorDecorator implements FieldDecorator {

    private final DefaultFieldDecorator defaultFieldDecorator;
    private final SearchContext searchContext;
    private Class<? extends PageElement> listType;
    private String elementName;

    public PageElementLocatorDecorator(SearchContext searchContext) {
        this.searchContext = searchContext;
        this.defaultFieldDecorator = new DefaultFieldDecorator(new DefaultElementLocatorFactory(searchContext));
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Class<?> type = field.getType();
        setName(field);

        if (PageElement.class.isAssignableFrom(type) &&
                (field.isAnnotationPresent(FindBy.class) || field.isAnnotationPresent(ExtendedFindBy.class))) {
            return getProxyElement(type, new PageElementLocatorHandler(getLocator(field), elementName));

        } else if (isPageElementList(field)) {
            return getProxyElementList(type, new PageElementLocatorListHandler(getLocator(field), listType, elementName));

        } else {
            return defaultFieldDecorator.decorate(loader, field);
        }
    }

    private void setName(Field field) {
        if (field.isAnnotationPresent(ExtendedFindBy.class) && (!field.getAnnotation(ExtendedFindBy.class).friendlyName().isEmpty())) {
            elementName = field.getAnnotation(ExtendedFindBy.class).friendlyName();
        } else {
            elementName = field.getName();
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxyElement(Class<T> tClass, MethodHandler handler) {
        ProxyFactory factory = new ProxyFactory();
        factory.setSuperclass(tClass);
        Class<?> aClass = factory.createClass();

        ProxyObject instance = getProxyObject(aClass, tClass.getSimpleName());

        instance.setHandler(handler);
        return (T) instance;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getProxyElementList(Class<T> tClass, MethodHandler handler) {
        ProxyFactory factory = new ProxyFactory();
        factory.setInterfaces(ArrayList.class.getInterfaces());
        Class<?> aClass = factory.createClass();

        ProxyObject instance = getProxyObject(aClass, tClass.getSimpleName());

        instance.setHandler(handler);
        return (List<T>) instance;
    }

    private ProxyObject getProxyObject(Class<?> aClass, String simpleName) {
        ProxyObject instance;
        try {
            instance = (ProxyObject) aClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new NewInstanceException("Class " + simpleName
                    + " should have default constructor or constructor without parameters.");
        }
        return instance;
    }

    private ElementLocator getLocator(Field field) {
        return new DefaultElementLocatorFactory(searchContext).createLocator(field);
    }

    @SuppressWarnings("unchecked")
    private boolean isPageElementList(Field field) {
        if (!List.class.isAssignableFrom(field.getType())) {
            return false;
        }

        Type genericType = field.getGenericType();
        if (!(genericType instanceof ParameterizedType)) {
            return false;
        }

        Class<?> listType = (Class<?>) ((ParameterizedType) genericType).getActualTypeArguments()[0];
        if (!(PageElement.class.isAssignableFrom(listType))) {
            return false;
        }

        this.listType = (Class<? extends PageElement>) listType;
        return field.getAnnotation(FindBy.class) != null
                || field.getAnnotation(FindBys.class) != null
                || field.getAnnotation(FindAll.class) != null;
    }
}
