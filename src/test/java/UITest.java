import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UITest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\WW\\Downloads\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);
        driver.get("http://localhost:8080/users");
        // Rest of your test code goes here


    try {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains("Users"));

        WebElement usersHeader = driver.findElement(By.tagName("h2"));
        if (usersHeader.getText().equals("Users")) {
        System.out.println("Users header is displayed.");
        } else {
        System.out.println("Users header is not displayed.");
        }

        WebElement createUserLink = driver.findElement(By.linkText("Create user"));
        if (createUserLink.isDisplayed()) {
        System.out.println("Create user link is displayed.");
        } else {
        System.out.println("Create user link is not displayed.");
        }
    } catch (Exception e) {
        e.printStackTrace();
        } finally {
        driver.quit();
        }
        }
        }