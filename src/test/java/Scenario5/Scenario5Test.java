package Scenario5;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Scenario5Test {
    private static WebDriver webDriver;
    private static String baseUrl;
    private static JavascriptExecutor js;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ljilj\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);

        // Maximize the browser window
        webDriver.manage().window().maximize();
        baseUrl = "https://www.univerzalno.com/";
        js = (JavascriptExecutor) webDriver;
    }

    @Test
    public void testAddToCart() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement searchInput = webDriver.findElement(By.name("q"));
        searchInput.sendKeys("miris");
        Thread.sleep(2000);

        WebElement searchButton = webDriver.findElement(By.xpath("//*[@id=\"header-search\"]/button"));
        searchButton.click();
        Thread.sleep(1000);

        Select arrange = new Select(webDriver.findElement(By.name("order_by")));
        arrange.selectByValue("cheapest");
        Thread.sleep(1000);

        WebElement miris = webDriver.findElement(By.xpath("//*[@id=\"search-filters-form\"]/section/div/div/div/div[2]/div[1]/div"));
        assertTrue(miris.isDisplayed());

        js.executeScript("window.scrollBy(0,400)", "");
        Thread.sleep(2000);

        WebElement addToCartButton = webDriver.findElement(By.xpath("//*[@id=\"search-filters-form\"]/section/div/div/div/div[2]/div[1]/div/div[3]/div[3]/a"));
        addToCartButton.click();
        Thread.sleep(2000);

        assertEquals("https://www.univerzalno.com/bs/miris-jelkica-lampd-elite-class-individualno-pakovanje-251-coquette?show_cart_modal=1", webDriver.getCurrentUrl());

        WebElement modalText = webDriver.findElement(By.xpath("//*[@id=\"add-to-cart-modal\"]/div/div/div/p[1]"));
        assertEquals("Proizvod je dodan u korpu", modalText.getText());
        Thread.sleep(1000);

        WebElement closeButton = webDriver.findElement(By.xpath("//*[@id=\"add-to-cart-modal\"]/div/div/button"));
        closeButton.click();
        Thread.sleep(1000);

        WebElement cart = webDriver.findElement(By.xpath("/html/body/div/header/div[2]/div[1]/div[3]/div/div[3]/div"));
        cart.click();
        Thread.sleep(1000);

        WebElement itemInCart = webDriver.findElement(By.xpath("/html/body/div/aside/div[2]/div/div[1]/div[2]/h4"));
        assertEquals("Miris jelkica L&amp;D Elite class individualno pakovanje 25/1 coquette", itemInCart.getText());
        Thread.sleep(2000);
    }

    @Test
    public void addToCartAndGoToCartPageTest() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement searchInput = webDriver.findElement(By.name("q"));
        searchInput.sendKeys("privjesak");
        Thread.sleep(2000);

        WebElement searchButton = webDriver.findElement(By.xpath("//*[@id=\"header-search\"]/button"));
        searchButton.click();
        Thread.sleep(1000);

        WebElement privjesak = webDriver.findElement(By.xpath("//*[@id=\"search-filters-form\"]/section/div/div/div/div[2]/div[1]/div/div[3]/h2/a"));
        js.executeScript("window.scrollBy(0,300)", "");
        Thread.sleep(2000);
        privjesak.click();
        Thread.sleep(1000);

        js.executeScript("window.scrollBy(0,400)", "");
        Thread.sleep(2000);

        WebElement increaseQuantityButton = webDriver.findElement(By.xpath("//*[@id=\"cart-form\"]/div/div[1]/span[2]/button"));
        increaseQuantityButton.click();
        increaseQuantityButton.click();
        Thread.sleep(1000);

        WebElement addToCart = webDriver.findElement(By.xpath("//*[@id=\"cart-form\"]/div/div[2]/button"));
        addToCart.click();
        Thread.sleep(1000);

        WebElement modalText = webDriver.findElement(By.xpath("//*[@id=\"add-to-cart-modal\"]/div/div/div/p[1]"));
        assertEquals("Proizvod je dodan u korpu", modalText.getText());
        Thread.sleep(1000);

        WebElement closeButton = webDriver.findElement(By.xpath("//*[@id=\"add-to-cart-modal\"]/div/div/button"));
        closeButton.click();
        Thread.sleep(1000);

        WebElement cart = webDriver.findElement(By.xpath("/html/body/div/header/div[2]/div[1]/div[3]/div/div[3]/div"));
        cart.click();
        Thread.sleep(1000);

        WebElement openFullCartButton = webDriver.findElement(By.xpath("/html/body/div/aside/div[2]/div/ul/li[1]/a"));
        openFullCartButton.click();
        Thread.sleep(1000);

        WebElement item = webDriver.findElement(By.xpath("/html/body/div/main/div/div/div[1]/table/tbody[1]/tr"));
        assertTrue(item.isDisplayed());
        Thread.sleep(2000);
    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
