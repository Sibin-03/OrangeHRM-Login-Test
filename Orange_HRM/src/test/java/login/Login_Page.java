package login;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import setup.Browser_Actions;

public class Login_Page {

	WebDriver driver;

	private final By usernameField = By.name("username");
	private final By passwordField = By.name("password");
	private final By submitButton = By.xpath("//*[@type='submit']");
	

	@BeforeMethod
	void open_app() {

		Browser_Actions.open_Browser();
		driver = Browser_Actions.getDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

	}

	@Test(priority = 1, description = "Verify user can log in with valid credentials")
	void login_valid_data() {

		driver.findElement(usernameField).sendKeys("Admin");
		driver.findElement(passwordField).sendKeys("admin123");
		driver.findElement(submitButton).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[.='Dashboard'])[4]")));
		WebElement Dashboard = driver.findElement(By.xpath("(//*[.='Dashboard'])[4]"));
		Assert.assertTrue(Dashboard.isDisplayed(), "User Login successfully");
	}

	@Test(priority = 2, description = "Verify user can't log in with invalid username")
	void login_Invalid_username() {

		driver.findElement(usernameField).sendKeys("Adminn");
		driver.findElement(passwordField).sendKeys("admin123");
		driver.findElement(submitButton).click();

		WebElement warning = driver.findElement(By.xpath("//div[@role='alert']"));
		Assert.assertEquals(warning.getText(), "Invalid credentials", "Error message mismatch");
	}

	@Test(priority = 3, description = "Verify user can't log in with invalid password")
	void login_Invalid_password() throws InterruptedException {

		driver.findElement(usernameField).sendKeys("Admin");
		driver.findElement(passwordField).sendKeys("admin1233");
		driver.findElement(submitButton).click();

		WebElement warning = driver.findElement(By.xpath("//div[@role='alert']"));
		Assert.assertEquals(warning.getText(), "Invalid credentials", "Error message mismatch");
	}

	@AfterMethod
	void close_app() {
		Browser_Actions.close_Browser();
	}

}
