package setup;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Browser_Actions {

	static WebDriver driver;

	public static void open_Browser() {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	public static WebDriver getDriver() { // Return the driver to which class implement this function
		return driver;
	}

	public static void close_Browser() {

		System.out.println("Thank you!");
		driver.quit();
	}

}
