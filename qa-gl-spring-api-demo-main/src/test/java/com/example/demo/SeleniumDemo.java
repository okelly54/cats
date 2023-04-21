package com.example.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumDemo {

	private WebDriver driver;

	@BeforeEach
	void init() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		// creates new window
		this.driver = new ChromeDriver(options);
		// maximises it
		this.driver.manage().window().maximize();
	}

	@Test
	void turtleTest() {
		// redirects browser to this address
		this.driver.get("https://www.bbc.co.uk/search");

		// find element on website, in this case the search bar, by doing inspect
		// element - right click on the code - copy - copy selector
		WebElement search = this.driver.findElement(By.cssSelector("#search-input"));
		search.sendKeys("turtles");
		search.sendKeys(Keys.ENTER);

		WebElement result = this.driver.findElement(By.cssSelector(
				"#main-content > div.ssrcss-1v7bxtk-StyledContainer.enjd40x0 > div > div > ul > li:nth-child(3) > div > div > div.ssrcss-tq7xfh-PromoContent.e1f5wbog8 > div.ssrcss-1f3bvyz-Stack.e1y4nx260 > a"));

		Assertions.assertEquals("The Turtle Dove Pilgrimage", result.getText());
	}

	@Test
	void penguinTest() throws InterruptedException {
		this.driver.get("https://www.bing.com/search");
		Thread.sleep(3000);
		WebElement search = this.driver.findElement(By.cssSelector("#sb_form_q"));
		search.sendKeys("penguin");
		search.sendKeys(Keys.ENTER);

		WebElement result = this.driver.findElement(By.cssSelector("#b_results > li.b_algo.b_vtl_deeplinks > h2 > a"));

		Assertions.assertEquals("Penguin Books", result.getText());

	}

	@AfterEach
	void tearDown() {
		this.driver.close();
	}

}