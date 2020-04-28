package com.emicalculator;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EmiCalculator {

	WebDriver driver;
	
	@BeforeMethod
	public void browsersetup() {
		System.setProperty("webdriver.chrome.driver","D:\\library\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.get("https://emicalculator.net/");
	}
	
	@Test
	public void EMITest() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"personal-loan\"]/a")).click();
		Thread.sleep(5000);
		Actions action = new Actions (driver);
		action.dragAndDropBy(driver.findElement((By.xpath("//*[@id=\"loanamountslider\"]/span"))), 64, 0).build().perform();;
		action.dragAndDropBy(driver.findElement((By.xpath("//*[@id=\"loaninterestslider\"]/span"))), -158, 0).build().perform();;
		action.dragAndDropBy(driver.findElement((By.xpath("//*[@id=\"loantermslider\"]/span"))), -128, 0).build().perform();;
		Thread.sleep(3000);
		String emielement = driver.findElement(By.xpath("//*[@id=\"emiamount\"]/p/span")).getText();
		Assert.assertEquals(emielement, "23,654");
		Thread.sleep(5000);
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
}
