package Scenario3;

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

public class ScenarioTest3 {
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
    public void testSearchFunctionality() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement searchInputField = webDriver.findElement(By.name("q"));
        searchInputField.sendKeys("iphone 13");
        Thread.sleep(2000);

        WebElement searchButton = webDriver.findElement(By.xpath("//*[@id=\"header-search\"]/button"));
        searchButton.click();
        Thread.sleep(2000);
        js.executeScript("window.scrollBy(0,200)", "");
        Thread.sleep(2000);

        assertEquals("https://www.univerzalno.com/bs/search?any_collection_id=&q=iphone+13", webDriver.getCurrentUrl());

        WebElement iphone13 = webDriver.findElement(By.xpath("//*[@id=\"search-filters-form\"]/section/div/div/div/div[2]/div[1]/div/div[3]/h2/a"));
        assertEquals("Apple iPhone 13 128GB", iphone13.getText());
    }

    @Test
    public void testSearchFunctionalityFailed() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement searchInputField = webDriver.findElement(By.name("q"));
        searchInputField.sendKeys("ihpone 13");
        Thread.sleep(2000);

        WebElement searchButton = webDriver.findElement(By.xpath("//*[@id=\"header-search\"]/button"));
        searchButton.click();
        Thread.sleep(2000);

        WebElement noResultMessage = webDriver.findElement(By.xpath("//*[@id=\"search-filters-form\"]/section/div/div/div/div[1]/div"));
        assertEquals("Nismo pronašli rezultate za vašu pretragu", noResultMessage.getText());
    }

    @AfterAll
    public static void tearDown() {
        // Close the browser
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
