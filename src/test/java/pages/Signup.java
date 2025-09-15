package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
//import java.time.Duration;

public class Signup {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new EdgeDriver();

		// Open SauceDemo
		driver.get("https://www.secaspi.org/");
		driver.manage().window().maximize();

		//navigation to the sign up page
		driver.findElement(By.xpath("//html/body/section[1]/div/a[2]")).click();

		// Input name field
		driver.findElement(By.id("last-name")).sendKeys("Kubo");
		driver.findElement(By.id("first-name")).sendKeys("Tite");

		// Input calendar field
		WebElement dateInput = driver.findElement(By.id("birthdate"));

		// Send a date in yyyy-MM-dd format
		dateInput.sendKeys("09-14-2006");

		// Selecting Female as gender
		WebElement genderDropdown = driver.findElement(By.id("gender"));

		Select select = new Select(genderDropdown);
		select.selectByVisibleText("Female");

		// Input fb link field
		driver.findElement(By.id("facebook-profile")).sendKeys("https://facebook.com/tite-kubo"); 
		
		// Input contact number field
		driver.findElement(By.id("contact-number")).sendKeys("091812346518"); 
		
		// Input email field
		driver.findElement(By.id("email")).sendKeys("helloimandreaaa@gmail.com"); // modify this if you want to see if the email verification was sent 
		
		// Input password field
		driver.findElement(By.name("password")).sendKeys("MyStrongPassword123!");
		driver.findElement(By.name("confirm-password")).sendKeys("MyStrongPassword123!");
		
		// Submitting registration details
		driver.findElement(By.id("sign-up-btn")).click();

	}

}
