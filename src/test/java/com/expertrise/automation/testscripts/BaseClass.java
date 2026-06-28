package com.expertrise.automation.testscripts;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {

	// ThreadLocal driver for parallel execution
	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public Logger logger;
	public Properties p;

	@BeforeMethod(groups = {"browser","Actions","GridTest"})
	@Parameters({"os", "browser"})
	public void setup(String os, String br) throws IOException {
		// load properties
		FileReader file = new FileReader(".//src//test//resources//config.properties");
		p = new Properties();
		p.load(file);

		// log4j
		logger = LogManager.getLogger(this.getClass());

		WebDriver localDriver = null;

		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			// OS
			if (os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			} else if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			} else {
				System.out.println("No matching OS..");
				return;
			}

			// Browser
			switch (br.toLowerCase()) {
				case "chrome":
					capabilities.setBrowserName("chrome");
					break;
				case "edge":
					capabilities.setBrowserName("MicrosoftEdge");
					break;
				default:
					System.out.println("No matching browser..");
					return;
			}

			 localDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
			//localDriver = new RemoteWebDriver(new URL("http://172.20.128.1:4444/wd/hub"), capabilities);


		} else if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch (br.toLowerCase()) {
				case "chrome":
					localDriver = new ChromeDriver();
					break;
				case "edge":
					localDriver = new EdgeDriver();
					break;
				default:
					System.out.println("No matching browser..");
					return;
			}
		}

		// set into ThreadLocal
		driver.set(localDriver);

		System.out.println("Driver initialized for thread: " + Thread.currentThread().getId()
				+ " with URL: " + getDriver().getCurrentUrl());

		// common setup
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		getDriver().get(p.getProperty("Appurl"));
		getDriver().manage().window().maximize();
	}

	// Helper to get driver
	public static WebDriver getDriver() {
		return driver.get();
	}

	@AfterMethod (groups = {"browser","Actions","GridTest"})
	public void tearDown() {
		if (getDriver() != null) {
			getDriver().quit();
			driver.remove();
		}
	}
}
