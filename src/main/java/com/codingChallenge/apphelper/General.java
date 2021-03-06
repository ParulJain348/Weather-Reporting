package com.codingChallenge.apphelper;

import com.codingChallenge.PageFactory.Elements;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class General  {
Elements elements;
static Properties properties;

    public void getWeather(WebDriver driver) throws InterruptedException {
        NormalWait(3);
        elements=PageFactory.initElements(driver,Elements.class);
        Assert.assertTrue(elements.ndtvlogo.isDisplayed(),"Header is not displayed");
        Reporter.log("Header is displayed");
        elements.subMenul.click();
        Assert.assertTrue(elements.weatherMenu.isDisplayed(),"Weather menu is not displayed");
        Reporter.log("Weather menu is displayed");
        elements.weatherMenu.click();
        implicitWaitSeconds(5,driver);
        Assert.assertTrue(elements.weatherPage.isDisplayed(),"Weather Page is not displayed.");
        Reporter.log("Weather Page is displayed.");

    }

    public void enterCityPin(WebDriver driver,String city) throws AWTException, InterruptedException {
        elements=PageFactory.initElements(driver,Elements.class);
       Assert.assertTrue(elements.pinYourCityText.isDisplayed(),"Pin Your City Text is not displayed.");
       Reporter.log("Pin Your City Text is displayed.");
       elements.searchBox.sendKeys(city);
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
       NormalWait(3);
       Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'"+city+"')]")).isDisplayed(),"Search city is not found");
       Reporter.log("Search city is found.");
       driver.findElement(By.xpath("//input[@id='"+city+"']")).click();
    }

    public void verifySearchCityInMap(WebDriver driver,String city)
    {
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='"+city+"']")).isDisplayed(),"Searched city is not displayed in Map");
        Reporter.log("Searched city is displayed in Map");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@title='"+city+"']/div/span[@class='tempRedText']")).isDisplayed());
        Reporter.log("Temperature in celsius is displayed");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@title='"+city+"']/div/span[@class='tempWhiteText']")).isDisplayed());
        Reporter.log("Temperature in Fahrenheit is displayed");

    }

    public void verifyCityWeatherDetailsPopup(WebDriver driver,String city,String state)
    {
        elements=PageFactory.initElements(driver,Elements.class);
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='"+city+"']")).isDisplayed(),"Searched city is not displayed in Map");
        Reporter.log("Searched city is not displayed in Map");
        driver.findElement(By.xpath("//div[text()='"+city+"']")).click();
       // Assert.assertTrue(driver.findElement(By.xpath("//span[text()='"+city+", "+state+"']")).isDisplayed());
       Assert.assertTrue(elements.condition.isDisplayed());
       Reporter.log("Weather condition is displayed");
       Assert.assertTrue(elements.wind.isDisplayed());
        Reporter.log("Weather wind is displayed");
       Assert.assertTrue(elements.humidity.isDisplayed());
        Reporter.log("Weather humidity is displayed");
       Assert.assertTrue(elements.tempInDregrees.isDisplayed());
        Reporter.log("Weather temperature in Degrees is displayed");
       Assert.assertTrue(elements.tempInFahrenheit.isDisplayed());
        Reporter.log("Weather temperature in Fahrenheit is displayed");

    }

    public String getTempInCelesicuc(WebDriver driver)
    {
        elements=PageFactory.initElements(driver,Elements.class);

       String tempInDegree= elements.tempInDregrees.getText();
       String[] tempArrya=tempInDegree.split(" ");
       return tempArrya[3];

    }

    public static double convertKelvinIntoCelsius(double kelvin)
    {
        double celsius=kelvin-273.5;

        return celsius;
    }

    public void NormalWait(int time) throws InterruptedException {Thread.sleep(time); }

    public void implicitWaitSeconds(int time,WebDriver driver) { driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS); }

  public static String getPropertiesValue(String key) throws IOException {
      FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/java/com/codingChallenge/testData/TestData.properties"));
      properties = new Properties();
      properties.load(file);
      return properties.getProperty(key);

  }

  public static String getAPIData(String city,String appId,String map,String key)
  {
      RestAssured.baseURI = "http://api.openweathermap.org/data/2.5?";
      RequestSpecification httpRequest = RestAssured.given();
      Response response = httpRequest.request(Method.GET, "/weather?q="+city+"&appid="+appId+"");
      String responseBody = response.getBody().asString();
      JsonPath jsonPathEvaluator = response.jsonPath();
      System.out.println("Response Body is =>  " + responseBody);
      String value= jsonPathEvaluator.getMap(map).get(key).toString();

     return value;
  }

    public static String getAPIData(String city,String appId,String key)
    {
        RestAssured.baseURI = "http://api.openweathermap.org/data/2.5?";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/weather?q="+city+"&appid="+appId+"");
        String responseBody = response.getBody().asString();
        JsonPath jsonPathEvaluator = response.jsonPath();
        System.out.println("Response Body is =>  " + responseBody);
        String value= jsonPathEvaluator.get(key);

        return value;
    }


    public static void compareTempInCelsius(Double tempInAPI,Double tempInUI) throws IOException {
        if((tempInAPI-tempInUI)<1 & (tempInAPI-tempInUI)>-1)
        { Reporter.log(getPropertiesValue("city")+" Current temp is :"+tempInUI+" Which is similar to api temp : "+tempInAPI);
        }
        else
        {Reporter.log(getPropertiesValue("city")+" Current temp is :"+tempInUI+" Which is not similar to api temp : "+tempInAPI);

        }
    }
}
