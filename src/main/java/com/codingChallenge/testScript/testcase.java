package com.codingChallenge.testScript;

import com.codingChallenge.Driver;
import com.codingChallenge.apphelper.General;
import jdk.nashorn.internal.ir.CatchNode;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

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
           System.out.println(wind_Speed);
           String temp=General.getAPIData(General.getPropertiesValue("city"),General.getPropertiesValue("appID"),"main","temp");
            System.out.println(temp);
        }
        catch (Exception e)
        {
System.out.println("Error in code "+e);
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
            if((tempInAPI-tempInUI)<1 & (tempInAPI-tempInUI)>-1)
            { System.out.println(General.getPropertiesValue("city")+" Current temp is :"+tempInUI+"Which is similar to api temp : "+tempInAPI);
            }
            else
            {System.out.println(General.getPropertiesValue("city")+" Current temp is :"+tempInUI+"Which is not similar to api temp : "+tempInAPI);

            }

        }
        catch(Exception e)
        {
            System.out.println("Error in code "+e);
            Assert.fail("Phase1",e);
        }
    }
}
