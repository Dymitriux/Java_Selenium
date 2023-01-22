package elements.findByExtension;

import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactoryFinder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@PageFactoryFinder(ExtendedFindByBuilder.class)
public @interface ExtendedFindBy {
    How how() default How.UNSET;

    String using() default "";

    String id() default "";

    String name() default "";

    String className() default "";

    String css() default "";

    String tagName() default "";

    String linkText() default "";

    String partialLinkText() default "";

    String xpath() default "";

    /* Below additional fields can be added for usage in ExtendedFindBy annotation like for example friendlyName. */
    String friendlyName() default "";
}
