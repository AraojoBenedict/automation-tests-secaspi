package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Config;
import java.time.Duration;


public class Login {
    private WebDriver driver;

    // Locators
    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By loginButton = By.className("login-btn");
    private By logoutButton = By.xpath("//button[text()='Log Out']");

    // Constructor
    public Login(WebDriver driver) {
        this.driver = driver;
    }

    // Login with custom credentials
    public void login(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
    }

    // Login with default credentials from Config
    public void loginWithDefaultUser() {
        login(Config.get("EMAIL"), Config.get("PASSWORD"));
    }

    public String getLogoutText() {
        return driver.findElement(logoutButton).getText();
    }
}
