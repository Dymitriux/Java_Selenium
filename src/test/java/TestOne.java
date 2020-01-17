import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestOne {

    WebDriver driver;

    @BeforeEach
    public void beforeEach() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();

//        Dimension dim = new Dimension(640,480);
//        driver.manage().window().setSize(dim);

//        driver.manage().window().setSize(new Dimension(640,480));

        driver.navigate().to("https://www.seleniumeasy.com/test/");

    }

    @AfterEach
    public void afterEach(){
//        driver.quit();
    }

    @Test
    public void testOne(){
        driver.findElement(By.id("btn_basic_example")).click();
        driver.findElement(By.xpath("//*[@id=\"basic\"]/div/a[1]")).isDisplayed();
        driver.findElement(By.xpath("//*[@id=\"basic\"]/div/a[1]")).click();
    }
}
