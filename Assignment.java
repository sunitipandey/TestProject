import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        //Launching chrome driver and url
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(" https://www.demoblaze.com/");

        //Clicking on login button
        driver.findElement(By.id("login2")).click();

        //Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        //Click login after entering credentials
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("loginusername")))).sendKeys("test51@example.com");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("loginpassword")))).sendKeys("1234");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Log in')]"))).click();

        //Clicking on first product and add to cart
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//a[contains(text(),'Samsung galaxy s6')]"))));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Samsung galaxy s6')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Add to cart')]"))).click();

        //Accepting alert
        wait.until (ExpectedConditions.alertIsPresent ());
        driver.switchTo().alert().accept();

        //Clicking on cart and placing order
        driver.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Place Order')]")).click();

        //Filling user details and click on purchase
        wait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).sendKeys("test");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("country"))).sendKeys("test");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("card"))).sendKeys("test");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Purchase')]"))).click();

        //Validating purchase is completed or not
        String purchaseCompleteText= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'Thank you for your purchase')]"))).getText();
        Assert.isTrue(purchaseCompleteText.equalsIgnoreCase("Thank you for your purchase!"),"Purchase is not completed");

        //Clicking on Ok and closing driver
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.sa-confirm-button-container"))).click();
        driver.quit();





        }
    }
