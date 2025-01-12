package Scenario17;

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
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Scenario17Test {
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
    public void checkoutTestWithoutAnAccount() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement searchItems = webDriver.findElement(By.xpath("//*[@id=\"header-search\"]/input"));
        WebElement searchButton = webDriver.findElement(By.xpath("//*[@id=\"header-search\"]/button"));
        searchItems.sendKeys("miris");
        Thread.sleep(1000);
        searchButton.click();
        Thread.sleep(2000);

        Select orderBy = new Select(webDriver.findElement(By.name("order_by")));
        orderBy.selectByValue("cheapest");
        Thread.sleep(1500);

        WebElement firstItem = webDriver.findElement(
                By.xpath("//*[@id=\"search-filters-form\"]/section/div/div/div/div[2]/div[1]/div/div[3]/div[3]/a")
        );
        js.executeScript("window.scrollBy(0,200)", "");
        Thread.sleep(2000);
        firstItem.click();

        Thread.sleep(1500);

        WebElement closeModal = webDriver.findElement(
                By.xpath("//*[@id=\"add-to-cart-modal\"]/div/div/div/div[4]/a[2]")
        );
        closeModal.click();

        Thread.sleep(1500);

        WebElement searchItems2 = webDriver.findElement(By.xpath("//*[@id=\"header-search\"]/input"));
        WebElement searchButton2 = webDriver.findElement(By.xpath("//*[@id=\"header-search\"]/button"));

        searchItems2.sendKeys("kablovi");

        Thread.sleep(1000);

        searchButton2.click();

        Thread.sleep(2000);

        Select orderBy2 = new Select(webDriver.findElement(By.name("order_by")));

        orderBy2.selectByValue("cheapest");

        Thread.sleep(1500);

        WebElement secondItem = webDriver.findElement(
                By.xpath("//*[@id=\"search-filters-form\"]/section/div/div/div/div[2]/div[1]/div/div[3]/div[3]/a")
        );
        js.executeScript("window.scrollBy(0,200)", "");
        Thread.sleep(2000);
        secondItem.click();

        Thread.sleep(1500);

        WebElement closeModal2AndProceedToCheckout = webDriver.findElement(
                By.xpath("//*[@id=\"add-to-cart-modal\"]/div/div/div/div[4]/a[1]")
        );
        closeModal2AndProceedToCheckout.click();

        Thread.sleep(1500);

        WebElement inputName = webDriver.findElement(By.id("name"));
        WebElement inputEmail = webDriver.findElement(By.id("email"));
        WebElement inputPhone = webDriver.findElement(By.id("phone"));
        WebElement inputZipCode = webDriver.findElement(By.id("zip_code"));
        WebElement inputCity = webDriver.findElement(By.id("city"));
        WebElement inputAddress = webDriver.findElement(By.id("address"));

        inputName.sendKeys("Test");
        Thread.sleep(1000);
        inputEmail.sendKeys("testForUni@gmail.com");
        Thread.sleep(1000);
        inputPhone.sendKeys("061111121");
        Thread.sleep(1000);

        js.executeScript("window.scrollBy(0,100)", "");
        Thread.sleep(1000);

        inputZipCode.sendKeys("71000");
        Thread.sleep(1000);
        inputCity.sendKeys("Sarajevo");
        Thread.sleep(1000);

        js.executeScript("window.scrollBy(0,100)", "");
        Thread.sleep(1000);

        inputAddress.sendKeys("Zmaja od Bosne bb");
        Thread.sleep(1000);

        js.executeScript("window.scrollBy(0,400)", "");
        Thread.sleep(1000);

        WebElement additionalNote = webDriver.findElement(By.id("orderNote"));
        additionalNote.sendKeys(
                "Ova kupnja je napravljena u svrhu testiranja vase stranice. " +
                "Testiranje se vrsi za univerzitet, te je nemojte uzimati u razmatranje. " +
                "Hvala na razumijevanju i izvinjavamo se."
        );
        Thread.sleep(1000);

        WebElement checkBox = webDriver.findElement(By.id("termsOfPurchaseTermsOfUse"));
        checkBox.click();
        Thread.sleep(1000);

        js.executeScript("window.scrollBy(0,400)", "");
        Thread.sleep(2000);

        WebElement cookiesButton = webDriver.findElement(By.xpath("/html/body/div/div[2]/div/button"));
        cookiesButton.click();
        Thread.sleep(1000);

        WebElement orderButton = webDriver.findElement(
                By.xpath("//*[@id=\"order-form\"]/div[2]/div/div[6]/button")
        );
        orderButton.click();

        Thread.sleep(3000);

        WebElement orderSuccessMessage = webDriver.findElement(
                By.xpath("//*[@id=\"exampleModalLabel\"]")
        );
        assertEquals("Narudžba je primljena!", orderSuccessMessage.getText());

        Thread.sleep(2000);

        WebElement closeSuccessMessage = webDriver.findElement(
                By.xpath("//*[@id=\"flash-overlay-modal\"]/div/div/div[3]/button")
        );
        closeSuccessMessage.click();

        String currentUrl = webDriver.getCurrentUrl();
        assertEquals(baseUrl + "bs/thankYou", currentUrl);
    }

    @AfterAll
    public static void tearDown() {
        // Close the browser
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
