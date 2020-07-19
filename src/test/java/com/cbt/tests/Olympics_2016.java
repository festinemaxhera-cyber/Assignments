package com.cbt.tests;

import com.cbt.utilities.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Olympics_2016 {
    public static WebDriver driver;

    @BeforeMethod
    public static void setUp() {
        driver = BrowserFactory.getDriver("chrome");
    }

    @Test
    public static void defaultSortTest(){
        driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");

        List<WebElement> tableRanksData = driver.findElements(By.xpath("(//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//tbody//tr//td[1])[position() <= 10]"));
        boolean sorted = true;
        for (int i = 0; i < tableRanksData.size(); i++){
            String rank = tableRanksData.get(i).getText();
            System.out.println("rank = " + rank);
            if(!rank.equals((i + 1) + "")){
                sorted = false;
            }
        }
        Assert.assertTrue(sorted);
        //Click on NOC
        driver.findElement(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//thead//tr//th[2]")).click();
        //verify that the table is now sorted by the country names.
        List<WebElement> tableNOCData = driver.findElements(By.xpath("(//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//tbody//tr//th)[position() <= 10]"));
        String previousCountryName = "";
        Boolean sortedNOC = true;
        for (WebElement noc : tableNOCData) {
            String countryName = noc.getText();
            System.out.println("countryName = " + countryName);
            if (countryName.compareTo(previousCountryName) < 0){
                sortedNOC = false;
            }
            previousCountryName = countryName;
        }
        Assert.assertTrue(sortedNOC);

        tableRanksData = driver.findElements(By.xpath("(//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//tbody//tr//td[1])[position() <= 10]"));
        sorted = true;
        for (int i = 0; i < tableRanksData.size(); i++){
            String rank = tableRanksData.get(i).getText();
            System.out.println("rank = " + rank);
            if(!rank.equals((i + 1) + "")){
                sorted = false;
            }
        }
        Assert.assertFalse(sorted);
    }

    @AfterMethod
    public static void tearDown() {
        driver.close();
    }
}
