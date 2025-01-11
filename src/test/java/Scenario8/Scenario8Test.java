package Scenario8;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Scenario8Test {
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
    public void contactTest() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0,4800)", "");
        Thread.sleep(4000);

        WebElement contactButton = webDriver.findElement(By.xpath("/html/body/div/footer/div[2]/div[1]/div/div[2]/div/ul/li[5]"));
        contactButton.click();
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0,500)", "");
        Thread.sleep(2000);

        WebElement name = webDriver.findElement(By.id("contact-name"));
        name.sendKeys("Salih Rogo");
        Thread.sleep(1000);

        WebElement email = webDriver.findElement(By.id("contact-email"));
        email.sendKeys("salihrogo111@gmail.com");
        Thread.sleep(1000);

        WebElement phone = webDriver.findElement(By.id("contact-phone"));
        phone.sendKeys("0601234567");
        Thread.sleep(1000);

        WebElement city = webDriver.findElement(By.id("contact-city"));
        city.sendKeys("Sarajevo");
        Thread.sleep(1000);

        WebElement message = webDriver.findElement(By.id("message"));
        message.sendKeys("Test");
        Thread.sleep(1000);

        js.executeScript("window.scrollBy(0,200)", "");
        Thread.sleep(2000);

        WebElement checkbox = webDriver.findElement(By.id("checkbox"));
        checkbox.click();
        Thread.sleep(1000);

        WebElement submitButton = webDriver.findElement(By.xpath("/html/body/div[1]/main/section[3]/div/div/div[2]/div/form/div/div[7]/div/div/button"));
        submitButton.click();
        Thread.sleep(1000);

        WebElement modal = webDriver.findElement(By.xpath("//*[@id=\"flash-overlay-modal\"]/div/div"));
        assertTrue(modal.isDisplayed());
    }

    @Test
    public void contactTestWithOnlyName() throws InterruptedException{
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0,4800)", "");
        Thread.sleep(4000);

        WebElement contactButton = webDriver.findElement(By.xpath("/html/body/div/footer/div[2]/div[1]/div/div[2]/div/ul/li[5]"));
        contactButton.click();
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0,500)", "");
        Thread.sleep(2000);

        WebElement name = webDriver.findElement(By.id("contact-name"));
        name.sendKeys("Salih Rogo");
        Thread.sleep(1000);

        js.executeScript("window.scrollBy(0,200)", "");
        Thread.sleep(2000);

        WebElement submitButton = webDriver.findElement(By.xpath("/html/body/div[1]/main/section[3]/div/div/div[2]/div/form/div/div[7]/div/div/button"));
        submitButton.click();
        Thread.sleep(1000);

        WebElement modal = webDriver.findElement(By.xpath("//*[@id=\"flash-overlay-modal\"]/div/div"));
        assertTrue(modal.isDisplayed());
    }

    @AfterAll
    public static void tearDown() {
        // Close the browser
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
