package com.codelabs.examples.selenium.tests;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class TC_WikipediaSearchTest {
	private WebDriver driver;
	private String baseUrl;

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {

		File chromedriverExecutable = new File("drivers" + File.separator + "chromedriver");

		System.setProperty("webdriver.chrome.driver", chromedriverExecutable.getAbsolutePath());
		driver = new ChromeDriver();
		baseUrl = "https://www.wikipedia.org/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Severity(SeverityLevel.CRITICAL)
	@Description("Test to search wikipedia site for a term and validate the search result heading")
	@Test(description = "TC-2000 ::: Search Wikipedia For Search Term")
	public void searchWikipedia() throws Exception {

		driver.get(baseUrl);
		String searchText = "Selenium";
		driver.findElement(By.id("searchInput")).clear();
		driver.findElement(By.id("searchInput")).sendKeys(searchText);
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='" + searchText + "'])[6]/following::i[1]"))
				.click();

		try {
			// Injecting a failure here to show case a failure
			assertEquals(driver.findElement(By.id("firstHeading")).getText(), "HP ALM");

		} catch (AssertionError err) {
			takeScreenshot();
			getPageSource();
			attachUserGuide();
			throw err;
		}

	}
	
	@Attachment (value = "Page Source Dump")
	public String getPageSource() {
		// Take page source dump and return
		return driver.getPageSource();
	}

	@Attachment(value = "Web Page Screenshot", type = "image/png")
	public byte[] takeScreenshot() {
		// Take a screenshot as byte array and return
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
	
	
	public void attachUserGuide() throws IOException {

		Path content = Paths.get("Allure Userguide.pdf");
		try (InputStream is = Files.newInputStream(content)) {
		    Allure.addAttachment("Troubleshooting Guide ", is);
		}
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
	}

}
