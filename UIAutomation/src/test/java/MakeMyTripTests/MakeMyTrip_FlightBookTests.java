package MakeMyTripTests;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MakeMyTrip_FlightBookTests {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\dipay\\GitRepo\\TestAutomationProjects\\UIAutomation\\chromedriver-win64\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver=new ChromeDriver(options);
		driver.get("https://www.makemytrip.com/");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		
		WebElement initialPopup=driver.findElement(By.xpath("//div[@class='imageSlideContainer']//span[@class='commonModal__close']"));
		initialPopup.click();
		
		
		WebElement fromCity=driver.findElement(By.id("fromCity"));
		fromCity.sendKeys("Mumbai");
		
		
		WebElement fromcitydrpdown=driver.findElement(By.xpath("//div[@class='autoSuggestPlugin hsw_autocomplePopup']//ul//li[1]"));
		fromcitydrpdown.click();
		
		
		WebElement toCity=wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("toCity"))));
		toCity.click();
		
		WebElement toCitySearch=driver.findElement(By.xpath("//div[contains(@class,'autoSuggestPlugin hsw_autocomplePopup')]//input[@placeholder='To']"));
		toCitySearch.sendKeys("Pune");
		
		Thread.sleep(2000);
		WebElement tocitydrpdown=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'autoSuggestPlugin hsw_autocomplePopup')]//ul//li[1]")));
		tocitydrpdown.click();
		
		Date dt=new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM"); 
		String currentDateTime = dateFormat.format(dt);
		
		HashMap<String,String> map=new HashMap<>();
		map.put("1", "January");
		map.put("2", "February");
		map.put("3", "March");
		map.put("4", "April");
		map.put("5", "May");
		map.put("6", "June");
		map.put("7", "July");
		map.put("8", "August");
		map.put("9", "September");
		map.put("10", "October");
		map.put("11", "November");
		map.put("12", "December");
		
		int month=Integer.parseInt(currentDateTime.split("-")[1])+1;
		int day=Integer.parseInt(currentDateTime.split("-")[0])+4;
		WebElement fromMonth=driver.findElement(By.xpath("//div[@class='DayPicker-Month']//div[contains(text(),'"+map.get(month+"")+"')]"));
		WebElement fromDate=fromMonth.findElement(By.xpath("//div[@class='DayPicker-Day']//p[text()='"+day+"']"));
		fromDate.click();
		
		WebElement returnArea=driver.findElement(By.xpath("//div[@data-cy='returnArea']"));
		returnArea.click();
		
		month=Integer.parseInt(currentDateTime.split("-")[1])+3;
		day=Integer.parseInt(currentDateTime.split("-")[0])+1;
		List<WebElement> toMonth=driver.findElements(By.xpath("//div[@class='DayPicker-Month']//div[contains(text(),'"+map.get(month+"")+"')]"));
		WebElement btn=null;
		while(toMonth.size()==0)
		{
			btn=driver.findElement(By.xpath("//span[@aria-label='Next Month']"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", btn);
			toMonth=driver.findElements(By.xpath("//div[@class='DayPicker-Month']//div[contains(text(),'"+map.get(month+"")+"')]"));
		}
		String toDateLocator="//div[@class='DayPicker-Month']//div[contains(text(),'"+map.get(month+"")+"')]//ancestor::div[@class='DayPicker-Month']//div[@class='DayPicker-Day']//p[text()='"+day+"']";
		WebElement toDate=driver.findElement(By.xpath(toDateLocator));
		toDate.click();
		
		driver.quit();
		
	}
}
