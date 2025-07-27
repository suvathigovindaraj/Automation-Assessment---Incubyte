package StepDefinitions;

import java.time.Duration;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class MagentoSteps {
	
	 WebDriver driver;
	    WebDriverWait wait;
	    String email;
	    String password = "Apple@123";

	    @Given("I open the Magento website")
	    public void openMagento() {
	        
	        driver = new ChromeDriver();
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        driver.manage().window().maximize();
	        driver.get("https://magento.softwaretestingboard.com/");
	    }

	    @When("I register a new user")
	    public void registerUser() {
	        driver.findElement(By.linkText("Create an Account")).click();

	        String firstName = "Apple";
	        String lastName = "Red";
	        email = "testuser" + UUID.randomUUID().toString().substring(0, 5) + "@mail.com";

	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstname"))).sendKeys(firstName);
	        driver.findElement(By.id("lastname")).sendKeys(lastName);
	        driver.findElement(By.id("email_address")).sendKeys(email);
	        driver.findElement(By.id("password")).sendKeys(password);
	        driver.findElement(By.id("password-confirmation")).sendKeys(password);
	        driver.findElement(By.cssSelector("button[title='Create an Account']")).click();

	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message-success")));
	    }

	    @Then("I logout")
	    public void logout() {
	        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".customer-name")));
	        dropdown.click();

	        WebElement signOut = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign Out")));
	        signOut.click();

	        wait.until(ExpectedConditions.titleContains("Home Page"));
	    }

	    @And("I login with the same credentials")
	    public void loginWithSameCredentials() {
	        driver.findElement(By.linkText("Sign In")).click();

	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys(email);
	        driver.findElement(By.id("pass")).sendKeys(password);
	        driver.findElement(By.id("send2")).click();
	    }

	    @Then("I should see the account dashboard")
	    public void verifyDashboard() {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement dashboardHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.panel.header")));

	        Assert.assertTrue(dashboardHeader.isDisplayed());
	        System.out.println("User is successfully navigated to the account dashboard.");
	        driver.close();
	    }
	

}
