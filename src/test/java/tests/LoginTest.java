package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import pages.Login;

public class LoginTest {
    public static void main(String[] args) {
        WebDriver driver = new EdgeDriver();
        driver.get("https://www.secaspi.org/");
        driver.manage().window().maximize(); 
        driver.findElement(By.xpath("//html/body/header/nav/ul/li[6]/a")).click();
        
        Login loginPage = new Login(driver);
        loginPage.loginWithDefaultUser();

        String actualText = loginPage.getLogoutText();
        if (actualText.equals("Log Out")) {
            System.out.println("✅ Login successful");
        } else {
            System.out.println("❌ Login failed");
        }

        driver.quit();
    }
}
