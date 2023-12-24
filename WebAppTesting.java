package com.defineslabs.websitetesting;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Set;

public class WebAppTesting {

	private Actions action;

	// Constructor initializes the Actions object with the provided WebDriver instance
	public WebAppTesting(WebDriver driver) {
		action = new Actions(driver);
	}

	// Presses the Enter key using the Actions class
	public void pressEnter() {
		action.sendKeys(Keys.ENTER).build().perform();
	}

	// Scrolls down the web page using JavascriptExecutor
	public void scrollDown(WebDriver driver) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.scrollBy(0,2000)");
	}

	// Performs mouse hover over the specified WebElement
	public void performMouseHover(WebElement element) {
		action.moveToElement(element).build().perform();
	}

	// main method
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();

		WebAppTesting objSearch = new WebAppTesting(driver);

		driver.manage().window().maximize();
		driver.get("https://www.bhaane.com");

		// subscription pop-up closer
		driver.findElement(By.xpath("//body/div[7]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]")).click();

		// Click on the signup button
		driver.findElement(By.xpath("/html[1]/body[1]/div[7]/div[1]/header[1]/div[1]/div[1]/div[4]/ul[1]/li[2]")).click();
		driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/form[1]/div[4]/div[1]")).click();

		// Send the user details in the sign-up page.
		driver.findElement(By.name("first_name")).sendKeys("Poorvik");
		driver.findElement(By.name("last_name")).sendKeys("Gowda K");
		driver.findElement(By.xpath("//body/div[@id='modal-register']/div[1]/form[1]/div[1]/div[3]/div[1]/input[1]")).sendKeys("poorvik123@gmail.com");
		driver.findElement(By.name("mobile")).sendKeys("9353825717");
		driver.findElement(By.xpath("//body/div[@id='modal-register']/div[1]/form[1]/div[1]/div[5]/div[1]/input[1]")).sendKeys("Poorvik@12");

		// Click on the sign-up submit button
		driver.findElement(By.xpath("//body/div[@id='modal-register']/div[1]/form[1]/div[2]/button[1]")).click();

		// Click on the login button
		driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/form[1]/div[2]/div[1]")).click();
		Thread.sleep(1000);

		// Send the user details in the login page.
		driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/form[1]/div[1]/input[1]")).sendKeys("poorvik123@gmail.com");
		driver.findElement(By.xpath("//body/div[@id='modal-login']/div[1]/form[1]/div[2]/input[1]")).sendKeys("Poorvik@12");
		Thread.sleep(1000);

		// Click on the login submit button
		driver.findElement(By.xpath("/html/body/div[2]/div/form/div[4]/button")).click();

		
		//// search button select and pass the values.
		//WebElement enterkey = driver.findElement(By.name("q"));
		//enterkey.sendKeys("sweater");
		//objSearch.pressEnter();
		

		objSearch.scrollDown(driver);
		// Sleep time for the page
		Thread.sleep(2000);

		// Clicking the particular item by using xpath section
		WebElement item = driver.findElement(By.xpath("/html/body/div[7]/div[2]/div[2]/div/div[6]/a/div/img"));
		item.click();

		// User account clicking user account option
		driver.findElement(By.xpath("//*[@id=\"top-header\"]/div/div/div[4]/ul/li[2]")).click();
		Thread.sleep(1000);

		// Handle the child window
		String parentWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();

		for (String window : windows) {
			if (!window.equals(parentWindow)) {
				driver.switchTo().window(window);
				// Close the child window
				driver.close();
				break;
			}
		}

		// Switch back to the parent window
		Thread.sleep(1000);

		driver.switchTo().window(parentWindow);
		Thread.sleep(500);

		// Logout text present inside the anchor tag.
		driver.findElement(By.xpath("//a[contains(text(),'log out')]")).click();

		// Close the main window
		//        driver.close();
	}
}
