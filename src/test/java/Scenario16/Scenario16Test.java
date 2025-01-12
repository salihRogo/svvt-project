package Scenario16;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Scenario16Test {
    private static WebDriver webDriver;
    private static String baseUrl;
    private static JavascriptExecutor js;
    private static WebDriverWait wait;


    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ljilj\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        // e.g. path: "C:/Users/John/smth/selenium/chromedriver.exe"
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        baseUrl = "https://www.univerzalno.com/";
        js = (JavascriptExecutor) webDriver;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    @Test
    public void comparisonEquationTest() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(2000);

        Select categories = new Select(webDriver.findElement(By.id("category")));
        categories.selectByValue("74");
        Thread.sleep(1000);

        WebElement searchInput = webDriver.findElement(By.xpath("//*[@id=\"header-search\"]/input"));
        searchInput.sendKeys("iphone 13");
        Thread.sleep(1000);

        WebElement searchButton = webDriver.findElement(By.xpath("//*[@id=\"header-search\"]/button"));
        searchButton.click();
        Thread.sleep(1000);

        WebElement phone1 = webDriver.findElement(By.xpath("//*[@id=\"search-filters-form\"]/section/div/div/div/div[2]/div[1]"));
        js.executeScript("window.scrollBy(0,200)", "");
        Thread.sleep(1000);
        Actions actions = new Actions(webDriver);
        actions.moveToElement(phone1).perform();
        WebElement addFirst = webDriver.findElement(By.xpath("//*[@id=\"search-filters-form\"]/section/div/div/div/div[2]/div[1]/div/div[2]/ul/li[1]/a"));
        addFirst.click();
        Thread.sleep(1000);
        WebElement item1AddedText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/span[3]")));
        assertEquals("Artikal dodan u poređenje", item1AddedText.getText());

        WebElement phone2 = webDriver.findElement(By.xpath("//*[@id=\"search-filters-form\"]/section/div/div/div/div[2]/div[2]/div/div[2]"));
        js.executeScript("window.scrollBy(0,200)", "");
        Thread.sleep(1000);
        Actions action = new Actions(webDriver);
        actions.moveToElement(phone2).perform();
        WebElement addSecond = webDriver.findElement(By.xpath("//*[@id=\"search-filters-form\"]/section/div/div/div/div[2]/div[2]/div/div[2]/ul/li[1]/a"));
        addSecond.click();
        Thread.sleep(1000);
        WebElement item2AddedText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/span[3]")));
        assertEquals("Artikal dodan u poređenje", item2AddedText.getText());

        WebElement compare = webDriver.findElement(By.xpath("/html/body/div/header/div[2]/div[1]/div[3]/div/div[1]"));
        compare.click();
        Thread.sleep(1000);

        WebElement table = webDriver.findElement(By.xpath("/html/body/div/main/div/div/div/table"));
        WebElement headerRow = table.findElement(By.tagName("tr"));
        List<WebElement> columns = headerRow.findElements(By.tagName("th"));

        WebElement comparisonNumberOfItems = webDriver.findElement(By.xpath("html/body/div/header/div[2]/div[1]/div[3]/div/div[1]/a/span[1]"));
        assertEquals(columns.size() - 1, comparisonNumberOfItems.getText(), "Failed because the number of items added to comparison cart is not the same as displayed on the website.");    // - 1 because of the column containing comparison details
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
