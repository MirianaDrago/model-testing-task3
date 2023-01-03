package loginSystem;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {
    public static boolean valid_credentials;
    public static boolean inAlertsPage;
    public static boolean loggedIn;
    public static String userID = "bb59b5de-29c0-44a1-9788-d3f03719a886";
    static WebDriver driver;

    public static void goodLogin() {
        valid_credentials = true;
        loggedIn = true;
        inAlertsPage = true;
        System.out.println("Good login observed");
    }

    public static void badLogin() {
        valid_credentials = false;
        loggedIn = false;
        System.out.println("Bad login observed");
    }
    public static boolean isLoggedIn() {
        driver.findElement(By.xpath("//a[@href='/Alerts/Login']")).click();
        WebElement inputBox = driver.findElement(By.id("UserId"));
        inputBox.sendKeys(userID);
        inputBox.submit();
        if (driver.getCurrentUrl().equals("https://www.marketalertum.com/Alerts/List")) {
            goodLogin();
            return true;
        } else {
            badLogin();
            return false;
        }
    }
    public static void visitAlerts() {
        driver.get("https://www.marketalertum.com/Alerts/List");
        inAlertsPage = true;
    }
    public boolean isInAlertsPage() {
        if(driver.getCurrentUrl().equals("https://www.marketalertum.com/Alerts/List")) {
            inAlertsPage = true;
            return true;
        } else {
            inAlertsPage = false;
            return false;
        }
    }
    public static boolean isLoggedOut() {
        if (!loggedIn) {
            System.out.println("User logged out");
            return true;
        }
        return false;
    }
    public static void Logout() {
        driver.findElement(By.xpath("//a[@href='/Home/Logout']")).click();
        loggedIn = false;
    }
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","/Users/mirianadrago/webdrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.marketalertum.com");
        driver.manage().window().maximize();
        isLoggedIn();
        visitAlerts();
        Logout();
        isLoggedOut();
    }
}