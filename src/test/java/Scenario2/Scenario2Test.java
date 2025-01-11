package Scenario2;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;

public class Scenario2Test {
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
    public void testLoginFunctionality() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement loginButton = webDriver.findElement(By.xpath("/html/body/div[1]/header/div[1]/div/div/div/div/a[3]"));
        loginButton.click();
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0,400)", "");

        WebElement email = webDriver.findElement(By.id("user-name"));
        email.sendKeys("salihrogo111@gmail.com");
        Thread.sleep(2000);

        WebElement password = webDriver.findElement(By.id("user-password"));
        password.sendKeys("Mirnanemirna1");
        Thread.sleep(2000);

        WebElement submitButton = webDriver.findElement(By.xpath("/html/body/div/main/div/div/div[2]/div/div/div[2]/form/div[3]/button"));
        submitButton.click();
        Thread.sleep(2000);

        assertEquals("https://www.univerzalno.com/", webDriver.getCurrentUrl());
        System.out.println("Successfully logged in.");
    }

    @Test
    public void testWithIncorrectEmailLoginFunctionality() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement loginButton = webDriver.findElement(By.xpath("/html/body/div[1]/header/div[1]/div/div/div/div/a[3]"));
        loginButton.click();
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0,400)", "");

        WebElement email = webDriver.findElement(By.id("user-name"));
        email.sendKeys("salihrogo1111@gmail.com");
        Thread.sleep(2000);

        WebElement password = webDriver.findElement(By.id("user-password"));
        password.sendKeys("Mirnanemirna1");
        Thread.sleep(2000);

        WebElement submitButton = webDriver.findElement(By.xpath("/html/body/div/main/div/div/div[2]/div/div/div[2]/form/div[3]/button"));
        submitButton.click();
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0,-200)", "");

        Thread.sleep(2000);

        WebElement failedLoginText = webDriver.findElement(By.xpath("/html/body/div/main/div/div/div[1]/div/div"));

        assertEquals("Neispravni pristupni podaci.", failedLoginText.getText());
        System.out.println("Failed to log in due to incorrect email.");
    }

    @Test
    public void testWithIncorrectPasswordLoginFunctionality() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement loginButton = webDriver.findElement(By.xpath("/html/body/div[1]/header/div[1]/div/div/div/div/a[3]"));
        loginButton.click();
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0,400)", "");

        WebElement email = webDriver.findElement(By.id("user-name"));
        email.sendKeys("salihrogo111@gmail.com");
        Thread.sleep(2000);

        WebElement password = webDriver.findElement(By.id("user-password"));
        password.sendKeys("abc");
        Thread.sleep(2000);

        WebElement submitButton = webDriver.findElement(By.xpath("/html/body/div/main/div/div/div[2]/div/div/div[2]/form/div[3]/button"));
        submitButton.click();
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0,-200)", "");

        Thread.sleep(2000);

        WebElement failedLoginText = webDriver.findElement(By.xpath("/html/body/div/main/div/div/div[1]/div/div"));

        assertEquals("Neispravni pristupni podaci.", failedLoginText.getText());
        System.out.println("Failed to log in due to incorrect password.");
    }

    @Test
    public void testForgotPasswordFunctionality() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement loginButton = webDriver.findElement(By.xpath("/html/body/div[1]/header/div[1]/div/div/div/div/a[3]"));
        loginButton.click();

        js.executeScript("window.scrollBy(0,400)", "");
        Thread.sleep(2000);

        WebElement forgotPasswordButton = webDriver.findElement(By.xpath("/html/body/div/main/div/div/div[2]/div/div/div[2]/form/p/a"));

        forgotPasswordButton.click();
        Thread.sleep(2000);

        WebElement email = webDriver.findElement(By.id("user-name"));
        email.sendKeys("salihrogo111@gmail.com");
        Thread.sleep(2000);

        WebElement submitResetButton = webDriver.findElement(By.xpath("/html/body/div/main/div/form/div[2]/div[2]/button"));
        submitResetButton.click();
        Thread.sleep(2000);

        assertEquals("https://www.univerzalno.com/bs/password/reset", webDriver.getCurrentUrl());
    }

    @AfterAll
    public static void tearDown() {
        // Close the browser
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
