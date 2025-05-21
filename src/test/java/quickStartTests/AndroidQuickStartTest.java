package quickStartTests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;


class AndroidQuickStartTest {

    AndroidDriver<AndroidElement> driver = null;
    DesiredCapabilities dc = new DesiredCapabilities();
    final String CLOUD_URL = System.getenv("CT_URL") + "/wd/hub";
    final String ACCESS_KEY = System.getenv("CT_ACCESS_KEY");
    final String APPIUM_VERSION = System.getenv("APPIUM_VERSION");

    @BeforeEach
    public void setUp() throws MalformedURLException {
        dc.setCapability("accessKey", ACCESS_KEY);
        if (APPIUM_VERSION != null && APPIUM_VERSION.length() > 0 ) {
            dc.setCapability("appiumVersion", APPIUM_VERSION);
        }
        dc.setCapability("deviceQuery", "@os='android' and contains(@name, 'Galaxy S2')");
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,  "UiAutomator2");
        dc.setCapability("testName", "Run Quickstart test on Android device");
        dc.setCapability(MobileCapabilityType.APP, "cloud:com.experitest.ExperiBank/.LoginActivity");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.experitest.ExperiBank");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".LoginActivity");
        driver = new AndroidDriver<>(new URL(CLOUD_URL), dc);
    }

    @Test
    void runQuickStartAndroidNative() {
        driver.rotate(ScreenOrientation.PORTRAIT);
        driver.findElement(By.id("com.experitest.ExperiBank:id/usernameTextField")).sendKeys("company");
        driver.findElement(By.id("com.experitest.ExperiBank:id/passwordTextField")).sendKeys("company");
        driver.findElement(By.id("com.experitest.ExperiBank:id/loginButton")).click();
        driver.findElement(By.id("com.experitest.ExperiBank:id/makePaymentButton")).click();
        driver.findElement(By.id("com.experitest.ExperiBank:id/phoneTextField")).sendKeys("0501234567");
        driver.findElement(By.id("com.experitest.ExperiBank:id/nameTextField")).sendKeys("John Snow");
        driver.findElement(By.id("com.experitest.ExperiBank:id/amountTextField")).sendKeys("50");
        driver.findElement(By.id("com.experitest.ExperiBank:id/countryTextField")).sendKeys("'Switzerland'");
        driver.findElement(By.id("com.experitest.ExperiBank:id/sendPaymentButton")).click();
        driver.findElement(By.id("android:id/button1")).click();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
