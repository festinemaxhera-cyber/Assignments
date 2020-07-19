package com.cbt.tests;

import com.cbt.utilities.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Olympics_2016_Part2 {
    public static WebDriver driver;

    @BeforeMethod
    public static void setUp() {
        driver = BrowserFactory.getDriver("chrome");
        driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");
    }

    @Test(description = "The first from the bottom")
    public void tc2_firstFromBottomTest(){
        Assert.assertEquals(getCountryWithLeastGold(), "Italy (ITA)");
        Assert.assertEquals(getCountryWithLeastSilver(), "South Korea (KOR)");
        Assert.assertEquals(getCountryWithLeastBronze(), "Italy (ITA)");
    }
    private String getCountryWithLeastGold(){
        driver.findElement(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//thead//tr//th[3]")).click();
        String country = driver.findElement(By.xpath("(//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//tbody//tr//th)")).getText().trim();
        return country;
    }
    private String getCountryWithLeastSilver(){
        driver.findElement(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//thead//tr//th[4]")).click();
        String country = driver.findElement(By.xpath("(//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//tbody//tr//th)")).getText().trim();
        return country;
    }
    private String getCountryWithLeastBronze(){
        driver.findElement(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//thead//tr//th[5]")).click();
        String country = driver.findElement(By.xpath("(//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//tbody//tr//th)")).getText().trim();
        return country;
    }

    @Test(description = "Country by medal")
    public void tc3_countryByMedal(){
        List<String> expectedList = new ArrayList<>();
        expectedList.add("South Korea (KOR)");
        expectedList.add("Japan (JPN)");
        expectedList.add("Germany (GER)");
        expectedList.add("Australia (AUS)");
        expectedList.add("Italy (ITA)");
        expectedList.add("Russia (RUS)");
        expectedList.add("China (CHN)");
        expectedList.add("France (FRA)");
        expectedList.add("Great Britain (GBR)");
        expectedList.add("United States (USA)");

        List<String> actualList = getCountryListWithSilverMedal();
        Assert.assertEquals(actualList,expectedList);
    }
    private List<String> getCountryListWithSilverMedal(){
        driver.findElement(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//thead//tr//th[4]")).click();
        List<WebElement> tableNOCData = driver.findElements(By.xpath("(//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//tbody//tr//th)[position() <= 10]"));

        List<String> countryList = new ArrayList<>();

        for (int i = 0; i < tableNOCData.size(); i++) {
            String countryName = tableNOCData.get(i).getText().trim();
            countryList.add(countryName);
        }
        return countryList;
    }

    @Test(description = "Get index")
    public void tc4_getIndex(){
        Integer[] expectedIndex = new Integer[] {5,2};
        Integer[] actualIndex = getCountryIndex("Germany (GER)");
        Assert.assertEquals(actualIndex,expectedIndex);
    }
    private Integer[] getCountryIndex(String countryName){
        List<WebElement> tableNOCData = driver.findElements(By.xpath("(//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//tbody//tr//th)[position() <= 10]"));

        for (int i = 0; i < tableNOCData.size(); i++) {
            String country = tableNOCData.get(i).getText().trim();
            if (countryName.equals(country)){
                return new Integer[]{i+1,2};
            }
        }
        return null;
    }

    @Test(description = "Get sum")
    public void tc5_getSum(){
        List<String> expectedContries = new ArrayList<>();
        expectedContries.add("Italy (ITA)");
        expectedContries.add("Australia (AUS)");
        List<String> actualContries = getSumOfCountries();
        Assert.assertEquals(actualContries,expectedContries);
    }
    private List<String> getSumOfCountries(){
        List<WebElement> tableNOCData = driver.findElements(By.xpath("(//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//tbody//tr//th)[position() <= 10]"));
        List<WebElement> tableBronzeData = driver.findElements(By.xpath("(//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//tbody//tr//td[4])[position() <= 10]"));

        for (int i = 0; i < tableNOCData.size() ; i++) {
            String firstCounrty = tableNOCData.get(i).getText().trim();
            for (int j = 0; j < tableBronzeData.size() ; j++) {
                String secondCounrty = tableNOCData.get(j).getText().trim();
                if (!firstCounrty.equals(secondCounrty)){
                    Integer firstCountryMedals = Integer.parseInt(tableBronzeData.get(i).getText());
                    Integer secondCountryMedals = Integer.parseInt(tableBronzeData.get(j).getText());
                    if (firstCountryMedals + secondCountryMedals == 18){
                        List<String> countryList = new ArrayList<>();
                        countryList.add(firstCounrty);
                        countryList.add(secondCounrty);
                        return countryList;
                    }
                }
            }
        }
        return null;
    }

    @AfterMethod
    public static void tearDown() {
        driver.close();
    }
}
