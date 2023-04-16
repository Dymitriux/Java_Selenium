package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static String takeScreenshot(String name, WebDriver driver) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH.mm.ss");
        File screenshotsDirectory = new File("screenshots/");
        if (!screenshotsDirectory.exists()){
            screenshotsDirectory.mkdirs();
        }
        String path = "screenshots/" + name + "_" + formatter.format(LocalDateTime.now()) + ".png";
        try {
            FileHandler.copy(screenshot, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}
