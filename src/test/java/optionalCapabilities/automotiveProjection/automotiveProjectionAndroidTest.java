package optionalCapabilities.automotiveProjection;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.jupiter.api.*;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

public class automotiveProjectionAndroidTest {

    static {
        nu.pattern.OpenCV.loadLocally(); // Load OpenCV native library
    }

    AndroidDriver<AndroidElement> driver = null;
    DesiredCapabilities dc = new DesiredCapabilities();
    final String CLOUD_URL = "<CLOUD_URL>" + "/wd/hub";
    final String ACCESS_KEY = "<ACCESS_KEY>";
    final String APPIUM_VERSION = "<APPIUM_VERSION>";
    final String DHU_SCREEN_SIZE = "<DHU_SCREEN_SIZE>"; // "800x480" | "1280x720" |"1920x1080"

    @BeforeEach
    public void setUp() throws MalformedURLException {
        dc.setCapability("accessKey", ACCESS_KEY);
        dc.setCapability("appiumVersion", APPIUM_VERSION);
        dc.setCapability("deviceQuery", "@os='android'");
        dc.setCapability("appPackage", "com.google.android.apps.maps");
        dc.setCapability("appActivity", "com.google.android.maps.MapsActivity");
        dc.setCapability("digitalai:automotiveProjection", DHU_SCREEN_SIZE);
        dc.setCapability("appiumVersion", "2.12.1");
        dc.setCapability("autoGrantPermissions", true); //for location permission
        driver = new AndroidDriver<>(new URL(CLOUD_URL), dc);
    }

    @Test
    public void quickStartAndroidNativeDemo() throws InterruptedException {
        driver.executeScript("seetest:client.setLocationPlaybackFile", "cloud:locationPoints", 1000, "gps");

        Mat searchBarImage = Imgcodecs.imread("searchBar.png");
        findImageAndTapUsingOpencv(getDHUScreenshotAsMat(), searchBarImage);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@resource-id='com.google.android.projection.gearhead:id/open_search_view_edit_text']")).sendKeys("tel aviv");

        driver.executeScript("digitalai:automotive.tap", 500, 30);
        Thread.sleep(5000);
        Mat locationIconImage = Imgcodecs.imread("locationIcon.png");
        findImageAndTapUsingOpencv(getDHUScreenshotAsMat(), locationIconImage);
        Thread.sleep(5000);
        Mat startButtonImage = Imgcodecs.imread("startButton.png");
        findImageAndTapUsingOpencv(getDHUScreenshotAsMat(), startButtonImage);
        Thread.sleep(15000);
        driver.executeScript("digitalai:automotive.tap", 500, 120);
    }

    private Mat getDHUScreenshotAsMat() {
        String base64String = (String) driver.executeScript("digitalai:automotive.getScreenshot");
        byte[] decodedBytes = Base64.getDecoder().decode(base64String);
        // Write the byte array to the output PNG file
        try (FileOutputStream fos = new FileOutputStream("screenshot.png")) {
            fos.write(decodedBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Imgcodecs.imread("screenshot.png");
    }

    private void findImageAndTapUsingOpencv(Mat inputImage, Mat templateImage) {
        if (inputImage.empty() || templateImage.empty()) {
            System.out.println("Error loading images!");
            return;
        }

        Mat result = new Mat();
        Imgproc.matchTemplate(inputImage, templateImage, result, Imgproc.TM_CCOEFF_NORMED); //find the template in the input image
        MatOfPoint points = new MatOfPoint();

        Core.findNonZero(result, points);
        boolean foundImage = false;
        for (Point p : points.toList()) {
            // Check if the result at this location is above the threshold
            if (result.get((int) p.y, (int) p.x)[0] >= 0.8) {
                System.out.println("Found image at " + p);
                driver.executeScript("digitalai:automotive.tap", p.x, p.y);
                foundImage = true; // opencv can find multiple matches of same element, but we only want to tap once
            }
        }
        if (!foundImage) {
            System.out.println("No image found.");
        }
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Report URL: " + driver.getCapabilities().getCapability("reportUrl"));
        driver.quit();
    }
}