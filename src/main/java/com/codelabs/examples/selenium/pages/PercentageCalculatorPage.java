package com.codelabs.examples.selenium.pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;


public class PercentageCalculatorPage {

	private By percentageTextField = By.id("cpar1");

	private By valueTextField = By.id("cpar2");

	private By calculateBtn = By.xpath(
			"(.//*[normalize-space(text()) and normalize-space(.)='Percentage Calculator'])[3]/following::input[5]");

	private By percentageResultLabel = By.xpath(
			"//*[@id='content']/p[2]/font[@color='green']/b");
	
	
	@Step("Step : Calculating {1}% of the value {2}")
	public void calculatePercentage(WebDriver driver, String percentage, String value) {

		driver.findElement(percentageTextField).sendKeys(percentage);
		driver.findElement(valueTextField).sendKeys(value);
		
		driver.findElement(calculateBtn).click();
		
	}
	
	@Step("Step : Verifying the percentage result is equal to {1}")
	public void verifyPercentage(WebDriver driver, String expectedValue) {
		
		assertEquals(driver.findElement(percentageResultLabel).getText(), expectedValue);
	}

}
