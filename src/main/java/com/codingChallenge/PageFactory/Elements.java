package com.codingChallenge.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Elements {

@FindBy(className = "ndtvlogo")
public WebElement ndtvlogo;

@FindBy(id="h_sub_menu")
    public WebElement subMenul;

@FindBy(xpath = "//*[text()='WEATHER']")
    public WebElement weatherMenu;

@FindBy(xpath = "//*[text()='Current weather conditions in your city.']")
    public WebElement weatherPage;

@FindBy(xpath = "//*[text()='Pin your City']")
    public WebElement pinYourCityText;

@FindBy(id="searchBox")
    public WebElement searchBox;

    @FindBy(xpath = "//b[contains(text(),'Condition')]")
    public WebElement condition;

@FindBy(xpath = "//b[contains(text(),'Wind')]")
    public WebElement wind;

@FindBy(xpath = "//b[contains(text(),'Humidity')]")
    public WebElement humidity;

@FindBy(xpath = "//b[contains(text(),'Temp in Degrees')]")
    public WebElement tempInDregrees;

@FindBy(xpath = "//b[contains(text(),'Temp in Fahrenheit')]")
    public WebElement tempInFahrenheit;



}
