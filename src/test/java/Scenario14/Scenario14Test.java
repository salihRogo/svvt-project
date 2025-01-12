package Scenario14;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Scenario14Test {
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
    public void editPasswordTest() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(2000);


        WebElement loginButton = webDriver.findElement(
                By.xpath("/html/body/div[1]/header/div[1]/div/div/div/div/a[3]")
        );
        loginButton.click();
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0,400)", "");
        Thread.sleep(1000);

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

        WebElement profileButton = webDriver.findElement(
                By.xpath("/html/body/div/header/div[1]/div/div/div/div[2]/div/a")
        );
        profileButton.click();
        Thread.sleep(2000);

        WebElement settingsButton = webDriver.findElement(
                By.xpath("/html/body/div/main/div/div/div[1]/a[1]")
        );
        settingsButton.click();
        Thread.sleep(2000);

        assertEquals("https://www.univerzalno.com/bs/showSettingsForm", webDriver.getCurrentUrl());

        WebElement passwordInputField = webDriver.findElement(By.id("user-password"));
        passwordInputField.sendKeys("Mirnanemirna1");
        Thread.sleep(1000);

        WebElement passwordInputField2 = webDriver.findElement(By.id("user-password-confirmation"));
        passwordInputField2.sendKeys("Mirnanemirna1");
        Thread.sleep(1000);

        js.executeScript("window.scrollBy(0,200)", "");
        Thread.sleep(1000);

        WebElement saveChangesButton = webDriver.findElement(
                By.xpath("/html/body/div/main/div/div/form/div[2]/button")
        );
        saveChangesButton.click();
        Thread.sleep(1000);

        WebElement conformationModal = webDriver.findElement(By.xpath("/html/body/div[2]/span[3]"));
        assertEquals("Postavke spašene", conformationModal.getText());
        Thread.sleep(1000);
        webDriver.manage().deleteAllCookies();
    }

    @Test
    @Order(2)
    public void changePasswordMismatchTest() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(2000);


        WebElement loginButton = webDriver.findElement(
                By.xpath("/html/body/div[1]/header/div[1]/div/div/div/div/a[3]")
        );
        loginButton.click();
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0,400)", "");
        Thread.sleep(1000);

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

        WebElement profileButton = webDriver.findElement(
                By.xpath("/html/body/div/header/div[1]/div/div/div/div[2]/div/a")
        );
        profileButton.click();
        Thread.sleep(2000);

        WebElement settingsButton = webDriver.findElement(
                By.xpath("/html/body/div/main/div/div/div[1]/a[1]")
        );
        settingsButton.click();
        Thread.sleep(2000);

        assertEquals("https://www.univerzalno.com/bs/showSettingsForm", webDriver.getCurrentUrl());

        WebElement passwordInputField = webDriver.findElement(By.id("user-password"));
        passwordInputField.sendKeys("1234");
        Thread.sleep(1000);

        WebElement passwordInputField2 = webDriver.findElement(By.id("user-password-confirmation"));
        passwordInputField2.sendKeys("abcd");
        Thread.sleep(1000);

        js.executeScript("window.scrollBy(0,200)", "");
        Thread.sleep(1000);

        WebElement saveChangesButton = webDriver.findElement(
                By.xpath("/html/body/div/main/div/div/form/div[2]/button")
        );
        saveChangesButton.click();
        Thread.sleep(1000);

        WebElement incorrectPasswordModal = webDriver.findElement(
                By.xpath("/html/body/div/main/div/div/div")
        );
        assertEquals("Potvrda za polje \"Šifra\" nije ispravna.", incorrectPasswordModal.getText());
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
