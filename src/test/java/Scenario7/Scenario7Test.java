package Scenario7;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Scenario7Test {
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
    public void addToComparison() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement nextPage = webDriver.findElement(
                By.xpath("/html/body/div/header/div[3]/div/div[2]/div/div[2]/nav/ul/li[2]/a")
        );
        nextPage.click();
        Thread.sleep(2000);

        WebElement element1 = webDriver.findElement(
                By.xpath("//*[@id=\"accordian\"]/ul/li/ul/li[1]/a[1]")
        );
        js.executeScript("arguments[0].click()", element1);
        Thread.sleep(1000);

        WebElement element2 = webDriver.findElement(
                By.xpath("//*[@id=\"accordian\"]/ul/li/ul/li[1]/ul/li[1]/a")
        );
        js.executeScript("arguments[0].click()", element2);
        Thread.sleep(2000);

        WebElement watch1 = webDriver.findElement(
                By.xpath("//*[@id=\"filters-form\"]/section/div/div/div[1]/div[3]/div[3]")
        );
        js.executeScript("window.scrollBy(0,200)", "");
        Thread.sleep(1000);
        Actions actions = new Actions(webDriver);
        actions.moveToElement(watch1).perform();
        WebElement addFirst = webDriver.findElement(
                By.xpath("//*[@id=\"filters-form\"]/section/div/div/div[1]/div[3]/div[3]/div/div[2]/ul/li[1]/a")
        );
        addFirst.click();
        Thread.sleep(1000);
        WebElement item1AddedText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("/html/body/div[2]/span[3]")
                )
        );
        assertEquals("Artikal dodan u poređenje", item1AddedText.getText());

        Thread.sleep(1000);

        WebElement watch2 = webDriver.findElement(
                By.xpath("//*[@id=\"filters-form\"]/section/div/div/div[1]/div[3]/div[4]")
        );
        js.executeScript("window.scrollBy(0,200)", "");
        Thread.sleep(1000);
        actions.moveToElement(watch2).perform();
        WebElement addSecond = webDriver.findElement(
                By.xpath("//*[@id=\"filters-form\"]/section/div/div/div[1]/div[3]/div[4]/div/div[2]/ul/li[1]/a")
        );
        addSecond.click();
        Thread.sleep(1000);
        WebElement item2AddedText = webDriver.findElement(By.xpath("/html/body/div[2]/span[3]"));
        assertEquals("Artikal dodan u poređenje", item2AddedText.getText());
        Thread.sleep(1000);

        WebElement compare = webDriver.findElement(
                By.xpath("/html/body/div/header/div[2]/div[1]/div[3]/div/div[1]")
        );
        Thread.sleep(1000);
        compare.click();

        Thread.sleep(1000);

        WebElement device1 = webDriver.findElement(
                By.xpath("/html/body/div/main/div/div/div/table/thead/tr/th[2]/a/div[2]")
        );
        WebElement device2 = webDriver.findElement(
                By.xpath("/html/body/div/main/div/div/div/table/thead/tr/th[3]/a/div[2]")
        );

        assertEquals("Samsung Galaxy Watch5 (Watch 5) BT 44mm (SM-R910)", device1.getText());
        assertEquals("APPLE WATCH SE (2 GENERACIJA) (GPS) 40MM", device2.getText());

        Thread.sleep(2000);
    }

    @Test
    @Order(2)
    public void deleteItemFromComparison() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement nextPage = webDriver.findElement(
                By.xpath("/html/body/div/header/div[3]/div/div[2]/div/div[2]/nav/ul/li[2]/a")
        );
        nextPage.click();
        Thread.sleep(2000);

        WebElement element1 = webDriver.findElement(
                By.xpath("//*[@id=\"accordian\"]/ul/li/ul/li[1]/a[1]")
        );
        js.executeScript("arguments[0].click()", element1);
        Thread.sleep(1000);

        WebElement element2 = webDriver.findElement(
                By.xpath("//*[@id=\"accordian\"]/ul/li/ul/li[1]/ul/li[1]/a")
        );
        js.executeScript("arguments[0].click()", element2);
        Thread.sleep(2000);

        WebElement watch1 = webDriver.findElement(
                By.xpath("//*[@id=\"filters-form\"]/section/div/div/div[1]/div[3]/div[3]")
        );
        js.executeScript("window.scrollBy(0,200)", "");
        Thread.sleep(1000);
        Actions actions = new Actions(webDriver);
        actions.moveToElement(watch1).perform();
        WebElement addFirst = webDriver.findElement(
                By.xpath("//*[@id=\"filters-form\"]/section/div/div/div[1]/div[3]/div[3]/div/div[2]/ul/li[1]/a")
        );
        addFirst.click();
        Thread.sleep(1000);
        WebElement item1AddedText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("/html/body/div[2]/span[3]")
                )
        );

        Thread.sleep(1000);

        WebElement watch2 = webDriver.findElement(
                By.xpath("//*[@id=\"filters-form\"]/section/div/div/div[1]/div[3]/div[4]")
        );
        js.executeScript("window.scrollBy(0,200)", "");
        Thread.sleep(1000);
        actions.moveToElement(watch2).perform();
        WebElement addSecond = webDriver.findElement(
                By.xpath("//*[@id=\"filters-form\"]/section/div/div/div[1]/div[3]/div[4]/div/div[2]/ul/li[1]/a")
        );
        addSecond.click();
        Thread.sleep(1000);
        WebElement item2AddedText = webDriver.findElement(By.xpath("/html/body/div[2]/span[3]"));
        Thread.sleep(1000);

        WebElement compare = webDriver.findElement(
                By.xpath("/html/body/div/header/div[2]/div[1]/div[3]/div/div[1]")
        );
        compare.click();
        Thread.sleep(1000);

        WebElement table = webDriver.findElement(By.xpath("/html/body/div/main/div/div/div/table"));
        WebElement headerRow = table.findElement(By.tagName("tr"));
        List<WebElement> columns = headerRow.findElements(By.tagName("th"));

        assertEquals(2, columns.size() - 1);    // - 1 because of the column containing comparison details
        System.out.println("Before deletion, there are 2 items in comparison");

        WebElement deleteItem = webDriver.findElement(
                By.xpath("/html/body/div/main/div/div/div/table/tbody/tr[4]/td[1]/a")
        );
        deleteItem.click();
        Thread.sleep(2000);

        WebElement tableAgain = webDriver.findElement(By.xpath("/html/body/div/main/div/div/div/table"));
        Thread.sleep(1000);
        WebElement headerRowAgain = tableAgain.findElement(By.tagName("tr"));
        List<WebElement> columnsAgain = headerRowAgain.findElements(By.tagName("th"));

        assertEquals(1, columnsAgain.size() - 1);
        System.out.println("After deletion, there is 1 item in comparison");
    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
