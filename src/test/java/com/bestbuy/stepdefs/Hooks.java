package com.bestbuy.stepdefs;

import com.bestbuy.utils.Driver;
import com.bestbuy.utils.PropertyReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Hooks {

    public static WebDriver driver;

    @Before("not @ChromeProfile")
    public void setUp(){
        driver = Driver.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Long.parseLong(PropertyReader.getProperty("implicitWait")), TimeUnit.SECONDS);
    }

    @Before("@ChromeProfile")
    public void setUpForChromeProfile(){
        driver = Driver.getDriverChromeProfile();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Long.parseLong(PropertyReader.getProperty("implicitWait")), TimeUnit.SECONDS);
    }

    @After()
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()){
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png","screenshot");
        }
        Driver.quitDriver();
    }



}
