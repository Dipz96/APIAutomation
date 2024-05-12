package FlipkartTests;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class FlipkartLoginAutomation {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\dipay\\GitRepo\\TestAutomationProjects\\UIAutomation\\chromedriver-win64\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver=new ChromeDriver(options);
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		//Code to open and close multiple tabs with different url
//		 ((JavascriptExecutor) driver).executeScript("window.open('https://www.geeksforgeeks.org/how-to-open-a-new-tab-using-selenium-webdriver-in-java/')");
//		 ((JavascriptExecutor) driver).executeScript("window.open('https://www.linkedin.com/notifications/?filter=all')");
//		 Set<String> windows=driver.getWindowHandles();
//		 for(String w: windows)
//		 {
//			 driver.switchTo().window(w);
//			 driver.close();
//		 }
		WebElement loginBtn=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[span='Login']")));
		Actions action= new Actions(driver);
		action.moveToElement(loginBtn).build().perform();
		
		WebElement signUpBtn=driver.findElement(By.xpath("//a//span[text()='Sign Up']"));
		signUpBtn.click();
		
		
		WebElement phoneTxtBox=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//span[text()='Enter Mobile number']//parent::label//parent::div//input")));
		phoneTxtBox.sendKeys("7001626618");
		
		
		WebElement continueBtn=driver.findElement(By.xpath("//button//span[text()='CONTINUE']//parent::button"));
		continueBtn.click();
		
		List<WebElement> registeredCheck=driver.findElements(By.xpath("//div[text()='You are already registered. Please log in.']"));
		Assert.assertTrue(registeredCheck.size()>0,"No error message for already registered user displayed");
		System.out.println("Error message displayed for already registered user displayed as expected");
		
		
		WebElement emailTxtBox=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//span[text()='Enter Email/Mobile number']//parent::label//parent::div//input")));
		js.executeScript("arguments[0].value = '';", emailTxtBox);
		emailTxtBox.sendKeys("babaisanyal4@gmail.com");
		
		
		WebElement reqOTPBtn=driver.findElement(By.xpath("//button[text()='Request OTP']"));
		reqOTPBtn.click();
		
////		
////		WebElement searchTxtBox=driver.findElement(By.xpath("//input[@title='Search for Products, Brands and More']"));
////		searchTxtBox.click();
////		
////		searchTxtBox.sendKeys("mobiles");
////		
////		WebElement searchBtn= driver.findElement(By.xpath("//button[@title='Search for Products, Brands and More']"));
////		searchBtn.click();
////		
////	    String mainWindow=driver.getWindowHandle();
////	    
////		WebElement ele=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Motorola g64 5G')]")));
////		ele.click();
////		
////		Set<String> windows=driver.getWindowHandles();
////		for(String window:windows)
////		{
////			if(!window.equals(mainWindow))
////				driver.switchTo().window(window);
////		}
////		
////		WebElement addToCartBtn=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add to cart']")));
////		addToCartBtn.click();
////		
////		WebElement placeOrderBtn=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button//span[text()='Place Order']")));
////		placeOrderBtn.click();
////		
////		List<WebElement> paymentOptions=driver.findElements(By.xpath("//span[text()='Payment Options']"));
//		
////		Assert.assertTrue(paymentOptions.size()>0,"Not redirected to payment page");
////		WebElement elecDrpdown=driver.findElement(By.xpath("//img[@alt='Electronics']"));
////		Actions action= new Actions(driver);
////		action.moveToElement(elecDrpdown).build().perform();
		
		
		driver.close();
	}

}
