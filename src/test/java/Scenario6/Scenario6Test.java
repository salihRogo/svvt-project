package Scenario6;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Scenario6Test {
    private static WebDriver webDriver;
    private static String baseUrl;
    private static JavascriptExecutor js;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/salihrogo/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
        baseUrl = "https://www.univerzalno.com/";
        js = (JavascriptExecutor) webDriver;
    }

    @Test
    @Order(1)
    public void testAddToWishList() throws InterruptedException {
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

        WebElement submitButton = webDriver.findElement(
                By.xpath("/html/body/div/main/div/div/div[2]/div/div/div[2]/form/div[3]/button")
        );
        submitButton.click();
        Thread.sleep(2000);

        WebElement searchInput = webDriver.findElement(By.name("q"));
        searchInput.sendKeys("samsung galaxy a50");
        Thread.sleep(2000);

        WebElement searchButton = webDriver.findElement(By.xpath("//*[@id=\"header-search\"]/button"));
        searchButton.click();
        Thread.sleep(1000);

        js.executeScript("window.scrollBy(0,200)", "");
        Thread.sleep(2000);

        WebElement card = webDriver.findElement(
                By.xpath("//*[@id=\"search-filters-form\"]/section/div/div/div/div[2]/div[1]/div")
        );

        Actions actions = new Actions(webDriver);
        actions.moveToElement(card).perform();

        WebElement phone = webDriver.findElement(
                By.xpath("//*[@id=\"search-filters-form\"]/section/div/div/div/div[2]/div[1]/div/div[2]/ul/li[2]/a")
        );
        phone.click();
        Thread.sleep(1000);

        WebElement addedToCart = webDriver.findElement(By.xpath("/html/body/div[2]/span[3]"));
        assertEquals("Artikal dodan na wish listu", addedToCart.getText().trim());

        webDriver.manage().deleteAllCookies();

    }

    @Test
    @Order(2)
    public void testAddToWishListAlreadyExists() throws InterruptedException{
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

        WebElement searchInput = webDriver.findElement(By.name("q"));
        searchInput.sendKeys("samsung galaxy a55");
        Thread.sleep(2000);

        WebElement searchButton = webDriver.findElement(By.xpath("//*[@id=\"header-search\"]/button"));
        searchButton.click();
        Thread.sleep(1000);

        js.executeScript("window.scrollBy(0,200)", "");
        Thread.sleep(2000);

        WebElement card = webDriver.findElement(By.xpath("//*[@id=\"search-filters-form\"]/section/div/div/div/div[2]/div[1]/div"));

        Actions actions = new Actions(webDriver);
        actions.moveToElement(card).perform();

        WebElement phone = webDriver.findElement(By.xpath("//*[@id=\"search-filters-form\"]/section/div/div/div/div[2]/div[1]/div/div[2]/ul/li[2]/a"));
        phone.click();
        Thread.sleep(7000);
        js.executeScript("window.scrollBy(0,200)", "");
        Thread.sleep(1000);
        WebElement card2 = webDriver.findElement(By.xpath("//*[@id=\"search-filters-form\"]/section/div/div/div/div[2]/div[1]/div"));
        actions.moveToElement(card2).perform();
        WebElement phone2 = webDriver.findElement(By.xpath("//*[@id=\"search-filters-form\"]/section/div/div/div/div[2]/div[1]/div/div[2]/ul/li[2]/a"));
        phone2.click();
        Thread.sleep(1000);

        WebElement addedToCart = webDriver.findElement(By.xpath("/html/body/div[2]/span[3]"));
        assertEquals("Artikal već na wish listi", addedToCart.getText().trim());
    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
