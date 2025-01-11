package Scenario9;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Scenario9Test {
    private static WebDriver webDriver;
    private static String baseUrl;

    private static JavascriptExecutor js;


    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ljilj\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        // e.g. path: "C:/Users/John/smth/selenium/chromedriver.exe"
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        baseUrl = "https://www.univerzalno.com/";
        js = (JavascriptExecutor) webDriver;
    }

    @Test
    public void testSocialMediaFacebook() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement facebookButton = webDriver.findElement(By.xpath("/html/body/div/header/div[1]/div/div/div/div[1]/a[1]"));
        facebookButton.click();
        String originalWindow = webDriver.getWindowHandle();
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(
                driver -> driver.getWindowHandles().size() > 1
        );
        Set<String> allWindows = webDriver.getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                webDriver.switchTo().window(windowHandle); // Switch to the new tab
                break;
            }
        }
        Thread.sleep(2000);

        assertEquals("https://www.facebook.com/univerzalno", webDriver.getCurrentUrl());
    }

    @Test
    public void testSocialMediaInstagram() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement facebookButton = webDriver.findElement(By.xpath("/html/body/div/header/div[1]/div/div/div/div[1]/a[2]"));
        facebookButton.click();
        String originalWindow = webDriver.getWindowHandle();
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(
                driver -> driver.getWindowHandles().size() > 1
        );
        Set<String> allWindows = webDriver.getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                webDriver.switchTo().window(windowHandle); // Switch to the new tab
                break;
            }
        }
        Thread.sleep(2000);

        assertEquals("https://www.instagram.com/univerzalno_com/", webDriver.getCurrentUrl());
    }

    @AfterAll
    public static void tearDown() {
        // Close the browser
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
