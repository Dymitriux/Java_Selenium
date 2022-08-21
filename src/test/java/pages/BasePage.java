package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.LoggingUtils;
import utils.WebDriverUtils;
import elements.webElementsExtensions.PageElementLocatorDecorator;

public class BasePage {

    public Logger logger = LoggingUtils.getLogger(this.getClass());

    public BasePage() {
        WebDriver driver = WebDriverUtils.getWebDriver();
        PageFactory.initElements(new PageElementLocatorDecorator(driver), this);
    }
}
