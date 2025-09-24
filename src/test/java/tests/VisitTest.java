package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.Login;
import pages.Visit;

public class VisitTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.secaspi.org/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//html/body/header/nav/ul/li[6]/a")).click();

		Login loginPage = new Login(driver);
		loginPage.loginWithDefaultUser();
		 driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
		 
		// navigation to visit page
		 WebElement visitUsButton = driver.findElement(By.xpath("//button[contains(text(),'Visit Us')]"));

		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].scrollIntoView(true);", visitUsButton);
		 js.executeScript("arguments[0].click();", visitUsButton);



		Visit visitPage = new Visit(driver);
		visitPage.submitVisitForm(
	            "10:00 AM",
	            "Juan Dela Cruz",
	            "Test Group",
	            "5",
	            "Educational Trip",
	            "09-25-2025"
	        );
		// Wait for the modal to appear
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement successMessage = wait2.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//p[contains(text(),'Your visit form has been successfully submitted')]")));

		// Assert the text
		String actualText = successMessage.getText();
		String expectedText = "Your visit form has been successfully submitted. Thank you.";

		if (actualText.equals(expectedText)) {
			System.out.println("✅ Visit request submitted successfully!");
		} else {
			System.out.println("❌ Unexpected message: " + actualText);
		}
	}

}
