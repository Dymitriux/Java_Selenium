package elements.findByExtension;

import org.openqa.selenium.By;
import org.openqa.selenium.support.AbstractFindByBuilder;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class ExtendedFindByBuilder extends AbstractFindByBuilder {

    @Override
    public By buildIt(Object annotation, Field field) {
        ExtendedFindBy extendedFindBy = (ExtendedFindBy) annotation;
        this.assertValidFindBy(extendedFindBy);
        By ans = this.buildByFromShortFindBy(extendedFindBy);
        if (ans == null) {
            ans = this.buildByFromLongFindBy(extendedFindBy);
        }

        return ans;
    }

    protected By buildByFromLongFindBy(ExtendedFindBy extendedFindBy) {
        return extendedFindBy.how().buildBy(extendedFindBy.using());
    }

    protected By buildByFromShortFindBy(ExtendedFindBy extendedFindBy) {
        if (!"".equals(extendedFindBy.className())) {
            return By.className(extendedFindBy.className());
        } else if (!"".equals(extendedFindBy.css())) {
            return By.cssSelector(extendedFindBy.css());
        } else if (!"".equals(extendedFindBy.id())) {
            return By.id(extendedFindBy.id());
        } else if (!"".equals(extendedFindBy.linkText())) {
            return By.linkText(extendedFindBy.linkText());
        } else if (!"".equals(extendedFindBy.name())) {
            return By.name(extendedFindBy.name());
        } else if (!"".equals(extendedFindBy.partialLinkText())) {
            return By.partialLinkText(extendedFindBy.partialLinkText());
        } else if (!"".equals(extendedFindBy.tagName())) {
            return By.tagName(extendedFindBy.tagName());
        } else {
            return !"".equals(extendedFindBy.xpath()) ? By.xpath(extendedFindBy.xpath()) : null;
        }
    }

    protected void assertValidFindBy(ExtendedFindBy extendedFindBy) {
        if (extendedFindBy.how() != null && extendedFindBy.using() == null) {
            throw new IllegalArgumentException("If you set the 'how' property, you must also set 'using'");
        } else {
            Set<String> finders = new HashSet<>();
            if (!"".equals(extendedFindBy.using())) {
                finders.add("how: " + extendedFindBy.using());
            }

            if (!"".equals(extendedFindBy.className())) {
                finders.add("class name:" + extendedFindBy.className());
            }

            if (!"".equals(extendedFindBy.css())) {
                finders.add("css:" + extendedFindBy.css());
            }

            if (!"".equals(extendedFindBy.id())) {
                finders.add("id: " + extendedFindBy.id());
            }

            if (!"".equals(extendedFindBy.linkText())) {
                finders.add("link text: " + extendedFindBy.linkText());
            }

            if (!"".equals(extendedFindBy.name())) {
                finders.add("name: " + extendedFindBy.name());
            }

            if (!"".equals(extendedFindBy.partialLinkText())) {
                finders.add("partial link text: " + extendedFindBy.partialLinkText());
            }

            if (!"".equals(extendedFindBy.tagName())) {
                finders.add("tag name: " + extendedFindBy.tagName());
            }

            if (!"".equals(extendedFindBy.xpath())) {
                finders.add("xpath: " + extendedFindBy.xpath());
            }

            if (finders.size() > 1) {
                throw new IllegalArgumentException(String.format("You must specify at most one location strategy. Number found: %d (%s)", finders.size(), finders.toString()));
            }
        }
    }
}
