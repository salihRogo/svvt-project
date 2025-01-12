package Scenario9;

import org.junit.jupiter.api.*;
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

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Scenario9Test {
    private static WebDriver webDriver;
    private static String baseUrl;

    private static JavascriptExecutor js;


    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/salihrogo/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        baseUrl = "https://www.univerzalno.com/";
        js = (JavascriptExecutor) webDriver;
    }

    @Test
    @Order(1)
    public void testSocialMediaFacebook() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement facebookButton = webDriver.findElement(
                By.xpath("/html/body/div/header/div[1]/div/div/div/div[1]/a[1]")
        );
        facebookButton.click();
        String originalWindow = webDriver.getWindowHandle();
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(
                driver -> driver.getWindowHandles().size() > 1
        );
        Set<String> allWindows = webDriver.getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }
        Thread.sleep(4000);

        assertEquals("https://www.facebook.com/univerzalno", webDriver.getCurrentUrl());

        webDriver.close();

        webDriver.switchTo().window(originalWindow);

        Thread.sleep(3000);
    }

    @Test
    @Order(2)
    public void testSocialMediaInstagram() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement facebookButton = webDriver.findElement(
                By.xpath("/html/body/div/header/div[1]/div/div/div/div[1]/a[2]")
        );
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

        assertEquals("https://www.instagram.com/univerzalno_com/#", webDriver.getCurrentUrl());

        webDriver.close();

        webDriver.switchTo().window(originalWindow);

        Thread.sleep(2000);
    }

    @AfterAll
    public static void tearDown() {
        // Close the browser
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
