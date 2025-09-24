package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Visit {

	private WebDriver driver;

	// Locators date of visit, time of visit, names, grouop name, number of pax,
	// purpose,sight seeing fields, submit button.
	private By dateOfVisitField = By.xpath("//*[@id=\"visit_date\"]");
	private By timeOfVisitField = By.id("visit_time");
	private By visitorName = By.xpath("//input[@name='names']");
	private By groupName = By.xpath("//input[@name='group_name']");
	private By paxNo = By.id("pax");
	private By purpose = By.xpath("//input[@name='purpose']");
	private By submitVisitButton = By.id("submitVisitBtn");

	// Constructor
	public Visit(WebDriver driver) {
		this.driver = driver;
	}
	
	public static void sleep(int ms) {
	    try {
	        Thread.sleep(ms);
	    } catch (InterruptedException e) {
	        throw new RuntimeException(e);
	    }
	}

	// Fill visit form with custom credentials
	public void submitVisitForm(String time, String name, String group, String pax, String purposeText, String date) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		// Time of Visit
		WebElement timeField = wait.until(ExpectedConditions.presenceOfElementLocated(timeOfVisitField));
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", timeField);
		timeField.sendKeys(time);
		sleep(3000); 
		// Visitor Name
		WebElement nameField = wait.until(ExpectedConditions.presenceOfElementLocated(visitorName));
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", nameField);
		nameField.sendKeys(name);
		sleep(3000); 
		// Group Name
		WebElement groupField = wait.until(ExpectedConditions.presenceOfElementLocated(groupName));
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", groupField);
		groupField.sendKeys(group);
		sleep(3000); 
		// Pax Number
		WebElement paxField = wait.until(ExpectedConditions.presenceOfElementLocated(paxNo));
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", paxField);
		paxField.sendKeys(pax);
		sleep(3000); 
		// Purpose
		WebElement purposeField = wait.until(ExpectedConditions.presenceOfElementLocated(purpose));
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", purposeField);
		purposeField.sendKeys(purposeText);
		sleep(3000); 

		// added JS Executor to input calendar data that doesnt allow user to type the
		// data
		// 1️⃣ Wait for the date input and click it to open the calendar
		WebElement dateInput = wait.until(ExpectedConditions.elementToBeClickable(dateOfVisitField));

		js.executeScript(
		    "arguments[0].value = arguments[1]; " +
		    "arguments[0].dispatchEvent(new Event('input')); " +
		    "arguments[0].dispatchEvent(new Event('change'));",
		    dateInput, "2025-09-25"
		);
		dateInput.sendKeys(Keys.TAB);  // closes date picker overlay
		sleep(3000); 
		System.out.println("📅 Date injected as MM-dd-yyyy: 09-20-2025");

		// Submit button logic
		WebElement submitBtn = wait.until(ExpectedConditions.presenceOfElementLocated(submitVisitButton));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", submitBtn);

		try {
		    wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
		    System.out.println("✅ Submit button clicked via Selenium");
		} catch (org.openqa.selenium.ElementClickInterceptedException e) {
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitBtn);
		    System.out.println("⚡ Submit button clicked via JavaScript fallback");
		}


		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
	}

}
