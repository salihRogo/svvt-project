package Scenario12;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Scenario12Test {
    private static WebDriver webDriver;
    private static String baseUrl;
    private static JavascriptExecutor js;
    private static WebDriverWait wait;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/salihrogo/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        baseUrl = "https://www.univerzalno.com/";
        js = (JavascriptExecutor) webDriver;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    @Test
    @Order(1)
    public void sessionHandlingWithCookiesDeletionTest() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(1000);

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

        WebElement submitButton = webDriver.findElement(
                By.xpath("/html/body/div/main/div/div/div[2]/div/div/div[2]/form/div[3]/button")
        );
        submitButton.click();
        Thread.sleep(2000);

        assertEquals("https://www.univerzalno.com/", webDriver.getCurrentUrl());

        webDriver.manage().deleteAllCookies();

        Thread.sleep(2000);

        System.out.println("Session cookies after logout and cookies deletion:");
        for (Cookie cookie : webDriver.manage().getCookies()) {
            System.out.println(cookie.getName() + ": " + cookie.getValue());
        }
        System.out.println("Session cookies should be empty after cookies deletion.");

        webDriver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("/html/body/div[1]/header/div[1]/div/div/div/div/a[3]"))
        );

        Thread.sleep(2000);
        assertTrue(webDriver.findElement(
                By.xpath("/html/body/div[1]/header/div[1]/div/div/div/div/a[3]")).isDisplayed()
        );
    }

    @Test
    @Order(2)
    public void manualLogoutAndCheckSessionCookies() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(1000);

        WebElement loginButton = webDriver.findElement(
                By.xpath("/html/body/div[1]/header/div[1]/div/div/div/div/a[3]")
        );
        loginButton.click();
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0,400)", "");

        WebElement email = webDriver.findElement(By.id("user-name"));
        email.sendKeys("salihrogo111@gmail.com");
        Thread.sleep(2000);

        WebElement password = webDriver.findElement(By.id("user-password"));
        password.sendKeys("Mirnanemirna1");
        Thread.sleep(2000);

        WebElement submitButton = webDriver.findElement(
                By.xpath("/html/body/div/main/div/div/div[2]/div/div/div[2]/form/div[3]/button")
        );
        submitButton.click();
        Thread.sleep(2000);

        System.out.println("Session cookies after login:");
        for (Cookie cookie : webDriver.manage().getCookies()) {
            System.out.println(cookie.getName() + ": " + cookie.getValue());
        }

        WebElement profileButton = webDriver.findElement(
                By.xpath("/html/body/div/header/div[1]/div/div/div/div[2]/div")
        );
        profileButton.click();
        Thread.sleep(2000);

        WebElement logoutButton = webDriver.findElement(
                By.xpath("/html/body/div/main/div/div/div[1]/a[2]")
        );
        logoutButton.click();
        Thread.sleep(4000);

        // Check for the absence of session cookies
        boolean sessionCookieExists = webDriver.manage().getCookies().stream()
                .anyMatch(cookie -> cookie.getName().equals("laravel_session"));

        assertFalse(sessionCookieExists, "Session cookie should be absent after logout");

        System.out.println("Session cookies after logout:");
        for (Cookie cookie : webDriver.manage().getCookies()) {
            System.out.println(cookie.getName() + ": " + cookie.getValue());
        }
    }

    @AfterAll
    public static void tearDown() {
        // Close the browser
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
