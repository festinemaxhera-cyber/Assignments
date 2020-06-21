package com.cbt.tests;

import com.cbt.utilities.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NavigationTests {
    public static void main(String[] args) {
       testGoogle();
       testEdge();
    }
    @Test
    public static void testGoogle(){
        //Go to	website	https://google.com
        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("https://google.com/");
        //Save	the	title	in	a	string	variable
        String googleTitle = driver.getTitle();

        //Go to	https://etsy.com
        driver.get("https://etsy.com/");
        //Save	the	title in a string variable
        String etsyTitle = driver.getTitle();

        //Navigate	back to	previous page
        driver.navigate().back();
        //Verify that title is	same is	in	step 3
        Assert.assertEquals(googleTitle,driver.getTitle());
        System.out.println("googleTitle = " + googleTitle);

        //Navigate	forward	to	previous	page
        driver.navigate().forward();
        //Verify that title	is	same is	in	step 5
        Assert.assertEquals(etsyTitle, driver.getTitle());
        System.out.println("etsyTitle = " + etsyTitle);

        driver.close();
    }
    @Test
    public static void testEdge(){
        //Go to	website	https://google.com
        WebDriver driver = BrowserFactory.getDriver("edge");
        driver.get("https://google.com/");
        //Save	the	title	in	a	string	variable
        String googleTitle = driver.getTitle();

        //Go to	https://etsy.com
        driver.get("https://etsy.com/");
        //Save	the	title in a string variable
        String etsyTitle = driver.getTitle();

        //Navigate	back to	previous page
        driver.navigate().back();
        //Verify that title is	same is	in	step 3
        Assert.assertEquals(googleTitle,driver.getTitle());
        System.out.println("googleTitle = " + googleTitle);

        //Navigate	forward	to	previous	page
        driver.navigate().forward();
        //Verify that title	is	same is	in	step 5
        Assert.assertEquals(etsyTitle, driver.getTitle());
        System.out.println("etsyTitle = " + etsyTitle);

        driver.close();
    }
}
