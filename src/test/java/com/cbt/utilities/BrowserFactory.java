package com.cbt.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {

    public static WebDriver getDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Festine.maxhera\\Desktop\\chromedriver.exe");
            return new ChromeDriver();
        } else if(browser.equals("edge")){
            System.setProperty("webdriver.edge.driver","C:\\Users\\Festine.maxhera\\Desktop\\msedgedriver.exe");
            return new EdgeDriver();
        }
        return null;
    }
}


