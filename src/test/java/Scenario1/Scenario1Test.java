package Scenario1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Scenario1Test {
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
    public void testRegistrationFunctionality() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement signUpButton = webDriver.findElement(By.xpath("/html/body/div/header/div[1]/div/div/div/div/a[4]"));
        signUpButton.click();
        Thread.sleep(1000);

        WebElement nameAndSurnameInputField = webDriver.findElement(By.id("user-name"));
        nameAndSurnameInputField.sendKeys("Mirna Ljiljic");

        WebElement submitSignUpButton = webDriver.findElement(By.xpath("/html/body/div[1]/main/div/div/div/div/div/div[2]/form/div[3]/button"));
        js.executeScript("arguments[0].scrollIntoView(true)", submitSignUpButton);

        Thread.sleep(1000);

        WebElement emailInputField = webDriver.findElement(By.id("user-email"));
        emailInputField.sendKeys("salihrogo18@gmail.com");
        Thread.sleep(1000);

        WebElement phoneInputField = webDriver.findElement(By.id("user-phone"));
        phoneInputField.sendKeys("063634799");
        Thread.sleep(1000);

        WebElement zipCodeInputField = webDriver.findElement(By.id("user-zip-code"));
        zipCodeInputField.sendKeys("71000");
        Thread.sleep(1000);

        WebElement cityInputField = webDriver.findElement(By.id("user-city"));
        cityInputField.sendKeys("Sarajevo");
        Thread.sleep(1000);

        WebElement addressInputField = webDriver.findElement(By.id("user-address"));
        addressInputField.sendKeys("Tajna adresa");
        Thread.sleep(1000);

        WebElement passwordInputField = webDriver.findElement(By.id("user-password"));
        passwordInputField.sendKeys("Mirnanemirna1");
        Thread.sleep(1000);

        WebElement repeatPasswordInputField = webDriver.findElement(By.id("user-password-confirmation"));
        repeatPasswordInputField.sendKeys("Mirnanemirna1");
        Thread.sleep(1000);


        submitSignUpButton.click();
        Thread.sleep(2000);

        assertEquals("https://www.univerzalno.com/", webDriver.getCurrentUrl());
    }


    @AfterAll
    public static void tearDown() {
        // Close the browser
        if (webDriver != null) {
            webDriver.quit();
        }
    }


}
