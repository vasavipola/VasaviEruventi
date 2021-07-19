package steps;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefeinition {
	WebDriver driver;
	Properties properties = new Properties();
	
	@After
	public void cleanup() {
		driver.quit();
	}
	@Before
	public void setup() throws IOException{
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
	}
	@Given("Open ANZ URL")
	public void open_ANZ_URL() throws FileNotFoundException, IOException {
		properties.load(new FileInputStream("selenium.properties"));
		driver.get(properties.getProperty("applicationURL"));
	}
	@When("enter the all required fields")
	public void enter_the_all_required_fields() {
	   driver.manage().window().maximize();
	   driver.findElement(By.xpath("//*[@aria-labelledby=\"q2q1\"]")).sendKeys("80000");
	   driver.findElement(By.xpath("//*[@aria-labelledby=\"q2q2\"]")).sendKeys("10000");
	   driver.findElement(By.xpath("//*[@id=\"expenses\"]")).sendKeys("500");
	   driver.findElement(By.xpath("//*[@id=\"otherloans\"]")).sendKeys("100");
	   driver.findElement(By.xpath("//*[@id=\"credit\"]")).sendKeys("1000");
	}

	@And("click on the button")
	public void click_on_the_button() throws InterruptedException {
	  driver.findElement(By.xpath("//*[@id=\"btnBorrowCalculater\"]")).click();
	  Thread.sleep(4000);
	}

	@Then("Verify expected amount")
	public void verify_expected_amount() {
	    String amount = driver.findElement(By.xpath("//*[@id=\"borrowResultTextAmount\"]")).getText();
	    Assert.assertEquals("$479,000",amount);
	}

	@And("click on start over button")
	public void click_on_start_over_button() throws InterruptedException {
		 driver.findElement(By.xpath("//*[@class=\"start-over\"]")).click();
		 Thread.sleep(2000);
	}

	@And("Verify all the fields are get cleared")
	public void verify_all_the_fields_are_get_cleared() {
		 Assert.assertEquals(driver.findElement(By.xpath("//*[@aria-labelledby=\"q2q1\"]")).getText(),"");
		 Assert.assertEquals(driver.findElement(By.xpath("//*[@aria-labelledby=\"q2q2\"]")).getText(),"");
		 Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"expenses\"]")).getText(),"");
		 Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"otherloans\"]")).getText(),"");
		 Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"credit\"]")).getText(),"");
	}

	@When("enter one dollar in leaving expenses field")
	public void enter_one_dollar_in_leaving_expenses_field() {
		driver.findElement(By.xpath("//*[@id=\"expenses\"]")).sendKeys("1");
	}

	@Then("Verify the expected message")
	public void verify_the_expected_message() {
		 Assert.assertEquals(driver.findElement(By.xpath("//*[@class=\"borrow__error__text\"]")).getText().trim(),"Based on the details you've entered, we're unable to give you an estimate of your borrowing power with this calculator. For questions, call us on 1800 035 500.");
	}



	
			

}
