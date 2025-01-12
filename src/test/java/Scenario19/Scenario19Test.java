package Scenario19;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Scenario19Test {
    private static WebDriver webDriver;
    private static String baseUrl;
    private static JavascriptExecutor js;
    private static WebDriverWait wait;


    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ljilj\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        baseUrl = "https://www.univerzalno.com/";
        js = (JavascriptExecutor) webDriver;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    @Test
    public void scrollToStart() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0,3000)", "");
        Thread.sleep(3000);

        WebElement cookiesButton = webDriver.findElement(By.xpath("/html/body/div/div[2]/div/button"));
        cookiesButton.click();
        Thread.sleep(4000);

        WebElement scrollToStartButton = webDriver.findElement(By.xpath("/html/body/div/div[1]"));
        scrollToStartButton.click();
        Thread.sleep(3000);

        int currentScrollY = Integer.parseInt(js.executeScript("return window.scrollY").toString());

        assertEquals(0, currentScrollY);
    }

    @AfterAll
    public static void tearDown() {
        // Close the browser
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
