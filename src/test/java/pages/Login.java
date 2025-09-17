package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import utils.Config;
import java.time.Duration;


public class Login {

	public static void main(String[] args) {

		// D ko alam ginagawa neto nalimutan ko na pero parang ini-initialize nya yung
		// web driver from maven type shi
		WebDriver driver = new EdgeDriver();

		// Open Secaspi
		driver.get("https://www.secaspi.org/");
		driver.manage().window().maximize();

		// navigation to the sign up page
		driver.findElement(By.xpath("//html/body/header/nav/ul/li[6]/a")).click();

		// Input email field
		driver.findElement(By.id("email")).sendKeys(Config.get("EMAIL"));

		// Input password field
		driver.findElement(By.id("password")).sendKeys(Config.get("PASSWORD"));

		// Clicking login
		driver.findElement(By.className("login-btn")).click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
		// Get text of the button using concise XPath
		String actualText = driver.findElement(By.xpath("//button[text()='Log Out']")).getText();

		// Check text manually (no TestNG/JUnit)
		if (actualText.equals("Log Out")) {
		    System.out.println("✅ Text matches! Found: " + actualText);
		} else {
		    System.out.println("❌ Text does not match! Found: " + actualText);
		}

	}

}
