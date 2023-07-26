package com.demo.datatest;

import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.demo.utility.DataTestUtility;



public class DataTest {
	static WebDriver driver;

	@BeforeTest
	public static void openUrl() throws Exception {
        
		System.setProperty("webdriver.chrome.driver","C:\\Selenium\\chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://nxtgenaiacademy.com/demo-site/");
	}

	@DataProvider
	public Iterator<Object[]> getTestData() throws Exception {
		ArrayList<Object[]> testData = DataTestUtility.getDataFromExcel();
		return testData.iterator();
	}

	@Test(dataProvider="getTestData")
	public static void Details(String firstname,String lastname,String address,String city,String country,
			String email,String dateofmonth,String dateofyear,String date,String convenient_timeHH,String convenient_timeMM,String mobilenumber,String enter_your_query,
			String verification,String gender) throws Exception {

		driver.findElement(By.cssSelector("input#vfb-5")).clear();
		driver.findElement(By.cssSelector("input#vfb-5")).sendKeys(firstname);
		driver.findElement(By.cssSelector("input#vfb-7")).clear();
		driver.findElement(By.cssSelector("input#vfb-7")).sendKeys(lastname);
		
		if(gender.equalsIgnoreCase("Male")) {
			driver.findElement(By.xpath("//*[@value=\"Male\"]")).click();
		}else if(gender.equalsIgnoreCase("female")) {
			driver.findElement(By.xpath("//*[@value=\"Female\"]")).click();
		}
		else
		{
			driver.findElement(By.xpath("//*[@value=\"Other\"]")).click();
		}
		driver.findElement(By.cssSelector("input#vfb-13-address")).clear();
		driver.findElement(By.cssSelector("input#vfb-13-address")).sendKeys(address);
		driver.findElement(By.cssSelector("input#vfb-13-zip")).clear();
		driver.findElement(By.cssSelector("input#vfb-13-zip")).sendKeys(city);
		
		//Dropdown
		WebElement drpDown = driver.findElement(By.xpath("(//*[@class=\"select2-selection select2-selection--single\"])[1]"));
		drpDown.click();
		WebElement countryOption=driver.findElement(By.cssSelector("input.select2-search__field"));
		countryOption.sendKeys(country);
		Thread.sleep(2000);
		drpDown.sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(2000);
		drpDown.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		
		driver.findElement(By.cssSelector("input#vfb-14")).clear();
		driver.findElement(By.cssSelector("input#vfb-14")).sendKeys(email);
		
		
		//Check box
//		List<WebElement> checkboxes = driver.findElements(By.xpath("//*[@id=\"item-vfb-20\"]"));
//
//		for(WebElement chkbox : checkboxes) {
//			String checkboxvalue = chkbox.getAttribute("value");
//			if(checkboxvalue.equals(hobby)||checkboxvalue.equals(hobby1)||checkboxvalue.equals(hobby2)) {
//				chkbox.click();
//			}
//		}
		
//		JavascriptExecutor jsc = (JavascriptExecutor) driver;
//		jsc.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Actions act = new Actions(driver);
		WebElement hourSearch=driver.findElement(By.cssSelector("span#select2-vfb-16-hour-container"));
		act.moveToElement(hourSearch).click().build().perform();
		
		WebElement hour=driver.findElement(By.cssSelector("input.select2-search__field"));
		hour.sendKeys(convenient_timeHH);
		hour.sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
		hour.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		
		
		WebElement minSearch=driver.findElement(By.cssSelector("span#select2-vfb-16-min-container"));
		act.moveToElement(minSearch).click().build().perform();
		WebElement minutes=driver.findElement(By.cssSelector("input.select2-search__field"));
		minutes.sendKeys(convenient_timeMM);
		Thread.sleep(1000);
		minutes.sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
		minutes.sendKeys(Keys.ENTER);
		
		driver.findElement(By.cssSelector("input#vfb-19")).clear();
		driver.findElement(By.cssSelector("input#vfb-19")).sendKeys(mobilenumber);
		driver.findElement(By.cssSelector("textarea.vfb-textarea.vfb-medium")).clear();
		driver.findElement(By.cssSelector("textarea.vfb-textarea.vfb-medium")).sendKeys(enter_your_query);

		
		//Date picker fetching date from excel
		driver.findElement(By.cssSelector("input#vfb-18")).click();
		while(true)
		{
			String dateXpath = driver.findElement(By.xpath("//*[@class=\"ui-datepicker-month\"]")).getText();
			String yearXpath = driver.findElement(By.xpath("//*[@class=\"ui-datepicker-year\"]")).getText();

			if(dateXpath.equals(dateofmonth) && yearXpath.equals(dateofyear))
			{
				driver.findElement(By.xpath("//*[@class=\"ui-datepicker-calendar\"]//tbody//tr//td//*[contains(text(),"+date+")]")).click();
			}
			else
			{
				driver.findElement(By.xpath("//*[@class=\"ui-icon ui-icon-circle-triangle-e\"]")).click();
			}
			break;
		}
		driver.findElement(By.cssSelector("input#vfb-3")).clear();
		driver.findElement(By.cssSelector("input#vfb-3")).sendKeys(verification);
		

	}
	@AfterTest
	public void logout()
	{
		driver.close();
	}
}


