package Scenario4;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Scenario4Test {
    private static WebDriver webDriver;
    private static String baseUrl;
    private static JavascriptExecutor js;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/salihrogo/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);

        // Maximize the browser window
        webDriver.manage().window().maximize();
        baseUrl = "https://www.univerzalno.com/";
        js = (JavascriptExecutor) webDriver;
    }

    @Test
    public void testNavigation() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(2000);

        String[] links = {
                "mobiteli",
                "oprema-za-mobitele-i-tablete",
                "kucanski-aparati",
                "laptop-pc-i-oprema",
                "tv-i-oprema",
                "multimedija",
                "auto-oprema",
                "foto-i-kamere",
                "kucne-i-kancelarijske-potrepstine",
                "sport-putovanje-i-slobodno-vrijeme"
        };

        int i = 1;

        for(String link : links){
            String finalUrl = baseUrl + "bs/" + link;
            webDriver.findElement(By.xpath("/html/body/div/header/div[3]/div/div[2]/div/div[2]/nav/ul/li[" + i + "]/a")).click();
            i++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            assertEquals(finalUrl, webDriver.getCurrentUrl());
        }
        Thread.sleep(2000);
    }

    @Test
    public void testNavigationFailed() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(2000);

        String[] links = {
                "pogresanLink",
                "oprema-za-mobitele-i-tablete",
                "kucanski-aparati",
                "laptop-pc-i-oprema",
                "tv-i-oprema",
                "multimedija",
                "auto-oprema",
                "foto-i-kamere",
                "kucne-i-kancelarijske-potrepstine",
                "sport-putovanje-i-slobodno-vrijeme"
        };

        int i = 1;

        for(String link : links){
            String finalUrl = baseUrl + "bs/" + link;
            webDriver.findElement(By.xpath("/html/body/div/header/div[3]/div/div[2]/div/div[2]/nav/ul/li[" + i + "]/a")).click();
            i++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            assertEquals(finalUrl, webDriver.getCurrentUrl(), "Test failed because of incorrect link.");
        }
        Thread.sleep(2000);
    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
