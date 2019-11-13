package com.codelabs.examples.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;

public class CalculatorHomePage {

	private By percCalculatorLink = By.linkText("Percentage Calculator");
	
	private By mathCalculatorLink = By.linkText("Math Calculators");
	
	@Step("Step : Navigate to the percentage calculator page from home page")
	public PercentageCalculatorPage navigateToPercCalcPage(WebDriver driver) {
		
		driver.findElement(mathCalculatorLink).click();
		driver.findElement(percCalculatorLink).click();
		
		return new PercentageCalculatorPage();
	}
	
}
