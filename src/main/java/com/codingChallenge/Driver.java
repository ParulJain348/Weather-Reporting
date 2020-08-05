package com.codingChallenge;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;

public class Driver {

public static WebDriver LaunchBrowser(String browser){
    WebDriver driver=null;
    String opSys = System.getProperty("os.name").toLowerCase();
    if(opSys.contains("windows"))
    {
        if(browser.toLowerCase().contains("chrome"))
        {
            System.setProperty("webdriver.chrome.driver","./chromedriver.exe");
            driver=new ChromeDriver();
            Reporter.log("Launch chrome browser");
        }
        else if(browser.toLowerCase().contains("firefox"))
        {
            System.setProperty("webdriver.gecko.driver","./geckodriver.exe");
            driver=new FirefoxDriver();
            Reporter.log("Launch firefox browser");
        }
        else if(browser.toLowerCase().contains("ie"))
        {
            System.setProperty("webdriver.ie.driver","./IEDriverServer.exe");
            driver=new InternetExplorerDriver();
            Reporter.log("Launch ie browser");
        }
        else
        {
            Reporter.log("browser Not found");
        }
        driver.manage().window().maximize();
    }
    else if(opSys.contains("linux"))
    {
        if(browser.toLowerCase().contains("chrome")) {
            System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            driver = new ChromeDriver(options);
            Reporter.log("Launch linux chrome browser");
        }
        driver.manage().window().maximize();
    }
    else
    {
       Reporter.log("OS not found");
    }
    return driver;
}
}
