package com.codingChallenge.testScript;

import com.codingChallenge.Driver;
import com.codingChallenge.apphelper.General;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class testcase extends Driver {
    WebDriver driver;

    @Test
    public void Phase1() {
        try {
            System.out.println("Launch URL");
            driver=Driver.LaunchBrowser(General.getPropertiesValue("browser"));
            driver.get(General.getPropertiesValue("url"));
            new General().getWeather(driver);
            new General().enterCityPin(driver,General.getPropertiesValue("city"));
            new General().verifySearchCityInMap(driver,General.getPropertiesValue("city"));
            new General().verifyCityWeatherDetailsPopup(driver,General.getPropertiesValue("city"),General.getPropertiesValue("state"));
            driver.quit();

        }
        catch (Exception e){
            Assert.fail("Phase1",e);
        }

    }

    @Test
    public void Phase2(){
        try{
           String  wind_Speed = General.getAPIData(General.getPropertiesValue("city"),General.getPropertiesValue("appID"),"wind","speed");
          Reporter.log(wind_Speed);
           String temp=General.getAPIData(General.getPropertiesValue("city"),General.getPropertiesValue("appID"),"main","temp");
            Reporter.log(temp);
        }
        catch (Exception e)
        {
Reporter.log("Error in code "+e);
            Assert.fail("Phase1",e);
        }
    }

    @Test
    public void Phase3(){
        try{
            driver=Driver.LaunchBrowser(General.getPropertiesValue("browser"));
            driver.get(General.getPropertiesValue("url"));
            new General().getWeather(driver);
            new General().enterCityPin(driver,General.getPropertiesValue("city"));
            new General().verifySearchCityInMap(driver,General.getPropertiesValue("city"));
            new General().verifyCityWeatherDetailsPopup(driver,General.getPropertiesValue("city"),General.getPropertiesValue("state"));
            Double tempInUI= Double.valueOf(new General().getTempInCelesicuc(driver));
            Double tempInAPI= Double.valueOf(General.getAPIData(General.getPropertiesValue("city"),General.getPropertiesValue("appID"),"main","temp"));
            tempInAPI=General.convertKelvinIntoCelsius(tempInAPI);
            General.compareTempInCelsius(tempInAPI,tempInUI);
        }
        catch(Exception e)
        {
            Reporter.log("Error in code "+e);
            Assert.fail("Phase1",e);
        }
    }
}
