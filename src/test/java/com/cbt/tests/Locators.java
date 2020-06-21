package com.cbt.tests;

import com.cbt.utilities.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class Locators {

    public static WebDriver driver;

    @BeforeMethod
    public static void setUp(){
       driver = BrowserFactory.getDriver("chrome");
    }

    @Test
    public static void amazonTest(){
        //Open go to amazon
        driver.get("https://www.amazon.com/");

        //enter any search term
        String searchItem = "shoes";
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(searchItem);

        //click on search button
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        //verify page title contains the search term
        String title = driver.getTitle();
        Assert.assertTrue(title.contains(searchItem));
    }

    @Test
    public static void wikipediaTest(){
        //Go to wikipedia.org
        driver.get("https://www.wikipedia.org/");

        //enter search term selenium webdriver
        String search = "selenium webdriver";
        driver.findElement(By.id("searchInput")).sendKeys(search);

        //click on search button
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //click on search result Selenium (software)
        driver.findElement(By.xpath("//a[@title='Selenium (software)']")).click();

        // verify url ends with Selenium_(software)
        String title = driver.getTitle();
        Assert.assertTrue(title.endsWith("Selenium_(software)"));
    }

    @Test
    public static void googleResultTitle(){
        //Go to https://google.com
        driver.get("https://www.google.com/");

        List<String> searchStrs = Arrays.asList("Java", "cucumber bdd", "Selenium web browser automation");

        for (String search : searchStrs) {
            //Search for one of the strings the list searchStrs
            driver.findElement(By.xpath("//input[@name='q']")).sendKeys(search);
            driver.findElement(By.xpath("//input[@name='q']")).sendKeys(Keys.ENTER);

            //In the results pages, capture the url right above the first result
            String urlValue = driver.findElement(By.xpath("(//div[@id='search']//cite)[1]")).getText();

            //Click on the first result
            driver.findElement(By.xpath("(//div[@id='search']//a)[1]")).click();

            //Verify that url is equal to the value from step 4
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains(urlValue));

            //Navigate back
            driver.navigate().back();
            driver.navigate().back();
        }
    }

    @Test
    public static void ebaySearchTest(){
        //Go to https://ebay.com
        driver.get("https://www.ebay.com/");

        //Search for wooden spoon
        driver.findElement(By.xpath("(//div[@id='gh-ac-box2']//input)[1]")).sendKeys("wooden spoon");
        driver.findElement(By.xpath("(//div[@id='gh-ac-box2']//input)[1]")).sendKeys(Keys.ENTER);

        //Save the total number of results
        String firstResults = driver.findElement(By.xpath("(//h1[@class='srp-controls__count-heading']/span)[1]")).getText();

        //Click on link All under the categories on the left menu
        driver.findElement(By.xpath("(//ul[@class='x-refine__left__nav']//li/a)[1]")).click();

        //Save the total number of second results
        String secondResults = driver.findElement(By.xpath("(//h1[@class='srp-controls__count-heading']/span)[1]")).getText();

        //Verify that number of results is bigger than the number in step 4
        Integer firstRes = Integer.parseInt(firstResults.replace(",",""));
        Integer secondRes = Integer.parseInt(secondResults.replace(",",""));
        Assert.assertTrue(secondRes > firstRes);

        //Navigate back to previous research results page
        driver.navigate().back();

        //Verify that wooden spoon is still displayed in the search box
        String searchValue = driver.findElement(By.xpath("(//div[@id='gh-ac-box2']//input)[1]")).getAttribute("value");
        Assert.assertEquals(searchValue, "wooden spoon");

        //Navigate back to home page
        driver.navigate().back();

        // Verify that search box is blank
        String searchBoxValue = driver.findElement(By.xpath("(//div[@id='gh-ac-box2']//input)[1]")).getAttribute("value");
        Assert.assertEquals(searchBoxValue,"");
    }

    @Test
    public static void vyTrackTitleTest() throws InterruptedException {
        //Go to Vytrack login page
        driver.get("https://qa3.vytrack.com/");

        //Login as any user
        String userName="user1";
        String password="UserUser123";
        driver.findElement(By.id("prependedInput")).sendKeys(userName);
        driver.findElement(By.id("prependedInput2")).sendKeys(password);
        driver.findElement(By.id("_submit")).click();

        //Click on your name on top right
        String user = driver.findElement(By.xpath("//li[@id='user-menu']//a")).getText();
        driver.findElement(By.xpath("//li[@id='user-menu']//a")).click();

        //Click on My Configuration
        driver.findElement(By.linkText("My Configuration")).click();
        Thread.sleep(2000);

        //Verify that page title starts with the same name on top
        String title = driver.getTitle();
        Assert.assertTrue(title.startsWith(user));
    }

    @Test
    public static void vyTrackShortcutFunctionalityTest() throws InterruptedException {
        //Go to Vytrack login page
        driver.get("https://qa3.vytrack.com/");

        //Login as sales manager
        String userName="salesmanager101";
        String password="UserUser123";
        driver.findElement(By.id("prependedInput")).sendKeys(userName);
        driver.findElement(By.id("prependedInput2")).sendKeys(password);
        driver.findElement(By.id("_submit")).click();

        //Verify Dashboard page is open
        String title = driver.getTitle();
        Assert.assertEquals(title,"Dashboard");

        Thread.sleep(4000);
        //driver.findElement(By.xpath("//div[@class='nav top-search shortcuts']//a[@title='Shortcuts']"));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//div[@class='nav top-search shortcuts']//a[@title='Shortcuts']"))).click().build().perform();


        action.moveToElement(driver.findElement(By.xpath("//div[@class='extra-small']//a"))).click();

       // driver.findElement(By.linkText("Opportunities")).click(); (//table[@class='table table-bordered table-striped']//a)[21]
        //driver.findElement(By.xpath("(//div[@id='container']//a)[21]")).isDisplayed();

        //Thread.sleep(8000);
       // String opportunitiesTitle = driver.getTitle();
       // Assert.assertEquals(opportunitiesTitle,"Open opportunities");

        //driver.findElement(By.xpath("//div[@class='nav top-search shortcuts']//a[@href='javascript:void(0);']")).isDisplayed();

       // driver.findElement(By.xpath("//div[@class='extra-small']//a")).isDisplayed();


       // driver.findElement(By.linkText("Vehicle Services Logs")).click();

       // String shortcutsTitle = driver.getTitle();
        //Assert.assertEquals(shortcutsTitle,"Shortcut Actions List");
    }
    @AfterMethod
    public static void tearDown(){
        driver.close();
    }
}
