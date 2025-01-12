package Scenario18;

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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Scenario18Test {
    private static WebDriver webDriver;
    private static String baseUrl;
    private static JavascriptExecutor js;
    private static WebDriverWait wait;


    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/salihrogo/chromedriver");
        // e.g. path: "C:/Users/John/smth/selenium/chromedriver.exe"
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        baseUrl = "https://www.univerzalno.com/";
        js = (JavascriptExecutor) webDriver;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    @Test
    public void newsletterTest() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0,1300)", "");
        Thread.sleep(1000);

        WebElement newsletterInputField = webDriver.findElement(
                By.xpath("/html/body/div/main/div[5]/div/div/form/input")
        );
        newsletterInputField.sendKeys("salihrogo111@gmail.com");
        Thread.sleep(1000);

        WebElement submitButton = webDriver.findElement(
                By.xpath("/html/body/div/main/div[5]/div/div/form/button")
        );
        String buttonType = submitButton.getAttribute("type");
        System.out.println(
                "The newsletter form is submitted even though it does not look like it on the websites UI. " +
                "The reason for that is that the button is of type submit."
        );
        assertEquals("submit", buttonType);
        Thread.sleep(1000);
    }

    @AfterAll
    public static void tearDown() {
        // Close the browser
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
