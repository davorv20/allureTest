package com.codelabs.examples.selenium.tests;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.codelabs.examples.selenium.pages.CalculatorHomePage;
import com.codelabs.examples.selenium.pages.PercentageCalculatorPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class TC_PercentageCalculatorTest {
	private WebDriver driver;
	private String baseUrl;

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		
		File chromedriverExecutable = new File("drivers" + File.separator + "chromedriver");
		
		System.setProperty("webdriver.chrome.driver", chromedriverExecutable.getAbsolutePath());
		driver = new ChromeDriver();
		baseUrl = "https://www.calculator.net/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Severity(SeverityLevel.BLOCKER)
	@Description("Test to check the functionality of the percentage calculator")
	@Test(description = "TC-1000 ::: Calculate Percentage And Verify")
	public void checkPercentageCalculation() throws Exception {

		driver.get(baseUrl);

		CalculatorHomePage home = new CalculatorHomePage();
		PercentageCalculatorPage calPage = home.navigateToPercCalcPage(driver);
		calPage.calculatePercentage(driver, "10", "500");
		calPage.verifyPercentage(driver, "50");

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
	}

}
