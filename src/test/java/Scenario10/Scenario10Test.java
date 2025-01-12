package Scenario10;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.v85.input.Input;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Scenario10Test {
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
    public void testDeletionFromCart() throws InterruptedException {
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

        WebElement submitButton = webDriver.findElement(By.xpath("/html/body/div/main/div/div/div[2]/div/div/div[2]/form/div[3]/button"));
        submitButton.click();
        Thread.sleep(2000);

        WebElement cartButtonFromHome = webDriver.findElement(By.xpath("/html/body/div/header/div[2]/div[1]/div[3]/div/div[3]"));
        cartButtonFromHome.click();
        Thread.sleep(1500);

        WebElement cartCart = webDriver.findElement(By.xpath("/html/body/div/aside/div[2]/div/ul/li[1]/a"));
        cartCart.click();
        Thread.sleep(2000);

        WebElement table = webDriver.findElement(By.xpath("/html/body/div/main/div/div/div[1]/table"));
        List<WebElement> rows = table.findElements(By.tagName("tbody"));

        assertEquals(3, rows.size());

        WebElement price = webDriver.findElement(By.xpath("/html/body/div/main/div/div/div[1]/table/tfoot/tr[3]/td[2]/strong"));

        assertEquals(617.00, Double.parseDouble(price.getText()));

        Thread.sleep(1000);

        WebElement deleteOneItemFromCart = webDriver.findElement(By.xpath("/html/body/div/main/div/div/div[1]/table/tbody[1]/tr/td[6]/a"));
        deleteOneItemFromCart.click();
        Thread.sleep(1000);

        WebElement deletionText = webDriver.findElement(By.xpath("//*[@id=\"flash-overlay-modal\"]/div/div/div[2]/p"));
        assertEquals("Artikal uklojen iz korpe", deletionText.getText());

        Thread.sleep(2000);

        WebElement closeModal = webDriver.findElement(By.xpath("//*[@id=\"flash-overlay-modal\"]/div/div/div[3]/button"));
        closeModal.click();
        Thread.sleep(1000);

        WebElement tableAfterDeletion = webDriver.findElement(By.xpath("/html/body/div/main/div/div/div[1]/table"));
        List<WebElement> rowsAfterDeletion = tableAfterDeletion.findElements(By.tagName("tbody"));

        assertEquals(2, rowsAfterDeletion.size());

        WebElement priceAfterDeletion = webDriver.findElement(By.xpath("/html/body/div/main/div/div/div[1]/table/tfoot/tr[3]/td[2]/strong"));

        assertEquals(349, Double.parseDouble(priceAfterDeletion.getText()));

        Thread.sleep(2000);
    }

    @Test
    public void updateQuantity() throws InterruptedException {
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

        WebElement submitButton = webDriver.findElement(By.xpath("/html/body/div/main/div/div/div[2]/div/div/div[2]/form/div[3]/button"));
        submitButton.click();
        Thread.sleep(2000);

        WebElement cartButtonFromHome = webDriver.findElement(By.xpath("/html/body/div/header/div[2]/div[1]/div[3]/div/div[3]"));
        cartButtonFromHome.click();
        Thread.sleep(1500);

        WebElement cartCart = webDriver.findElement(By.xpath("/html/body/div/aside/div[2]/div/ul/li[1]/a"));
        cartCart.click();
        Thread.sleep(2000);

        WebElement quantityInputOfFirstItemInCart = webDriver.findElement(By.xpath("/html/body/div/main/div/div/div[1]/table/tbody/tr/td[3]/div/form/div/input"));

        String quantityBeforeUpdate = quantityInputOfFirstItemInCart.getAttribute("value");

        quantityInputOfFirstItemInCart.sendKeys(Keys.ARROW_UP);

        WebElement popupModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"flash-overlay-modal\"]/div/div")));

        assertTrue(popupModal.isDisplayed());

        WebElement closeModal = webDriver.findElement(By.xpath("//*[@id=\"flash-overlay-modal\"]/div/div/div[3]/button"));
        Thread.sleep(2000);
        closeModal.click();

        WebElement quantityInputOfFirstItemInCart2 = webDriver.findElement(By.xpath("/html/body/div/main/div/div/div[1]/table/tbody/tr/td[3]/div/form/div/input"));
        String quantityAfterUpdate = quantityInputOfFirstItemInCart2.getAttribute("value");

        assertNotEquals(quantityBeforeUpdate, quantityAfterUpdate);

        Thread.sleep(3000);

    }



    @AfterAll
    public static void tearDown() {
        // Close the browser
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
