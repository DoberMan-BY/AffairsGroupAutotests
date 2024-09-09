package helpers;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotHelper {
    public static void addScreenShotToAllure(WebDriver driver) {
        String screenshotName = new SimpleDateFormat("yyyy_MM_dd-HH_mm_ss").format(new Date());
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(System.getProperty("user.dir") + screenshotName + ".png");
            FileHandler.copy(srcFile, destFile);
            FileInputStream fis = new FileInputStream(destFile);
            Allure.addAttachment(screenshotName, new ByteArrayInputStream(fis.readAllBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
