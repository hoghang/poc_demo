package poc_demo;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CheckLogin {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress = "diem.hakieu@duluxgroup.com.au";
	String passWord = "L!ncoln5entry";
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://pre-comm.lincolnsentry.com.au/");
		waitForSecond(3);
		
	}
	
	@Test
	public void TC_01_LoginSuccess() {
		driver.findElement(By.xpath("//span[contains(text(),'Sign In')]")).click();
		driver.findElement(By.id("j_username")).sendKeys(emailAddress);
		driver.findElement(By.id("j_password")).sendKeys(passWord);
		driver.findElement(By.id("btn-login")).click();
		
		waitForSecond(3);
		// Verify that a popup is displayed or not
		assertTrue(driver.findElement(By.xpath("//div[@class=\"account-content\"]")).isDisplayed());
		
	}

	private void waitForSecond(int i) {
		try {
		    Thread.sleep(i*1000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
