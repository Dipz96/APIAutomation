package FlipkartTests;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class FlipkartProductTest {
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\dipay\\GitRepo\\TestAutomationProjects\\UIAutomation\\chromedriver-win64\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver=new ChromeDriver(options);
		driver.navigate().to("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor js = (JavascriptExecutor)driver;
				
		
		WebElement searchTxtBox=driver.findElement(By.xpath("//input[@title='Search for Products, Brands and More']"));
		searchTxtBox.click();
		
		searchTxtBox.sendKeys("mobiles");
		
		WebElement searchBtn= driver.findElement(By.xpath("//button[@title='Search for Products, Brands and More']"));
		searchBtn.click();
		
	    String mainWindow=driver.getWindowHandle();
	    
		WebElement ele=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Motorola g64 5G')]")));
		ele.click();
		
		Set<String> windows=driver.getWindowHandles();
		for(String window:windows)
		{
			if(!window.equals(mainWindow))
				driver.switchTo().window(window);
		}
		
		WebElement addToCartBtn=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add to cart']")));
		addToCartBtn.click();
		
		WebElement placeOrderBtn=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button//span[text()='Place Order']")));
		placeOrderBtn.click();
		
		List<WebElement> paymentOptions=driver.findElements(By.xpath("//span[text()='Payment Options']"));
		Assert.assertTrue(paymentOptions.size()>0,"Not redirected to payment page");
		
		
//		WebElement elecDrpdown=driver.findElement(By.xpath("//img[@alt='Electronics']"));
//		Actions action= new Actions(driver);
//		action.moveToElement(elecDrpdown).build().perform();
		
		
		driver.quit();
	}
}
