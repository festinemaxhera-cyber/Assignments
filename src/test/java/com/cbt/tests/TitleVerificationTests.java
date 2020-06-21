package com.cbt.tests;

import com.cbt.utilities.BrowserFactory;
import com.cbt.utilities.StringUtility;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class TitleVerificationTests {
    public static void main(String[] args) {
        List<String> urls = Arrays.asList("http://practice.cybertekschool.com/",
                "http://practice.cybertekschool.com/dropdown",
                "http://practice.cybertekschool.com/login");

        //Open chrome browser
        WebDriver driver = BrowserFactory.getDriver("chrome");

        //Visit all the websites from step 3 and verify that they all have the same title.
        driver.get(urls.get(0));
        String firstTitle = driver.getTitle();

        driver.get(urls.get(1));
        String secondTitle = driver.getTitle();

        driver.get(urls.get(2));
        String thirdTitle = driver.getTitle();

        //Verify that all URLs of all pages start with http://practice.cybertekschool.com.
        StringUtility.verifyEquals(firstTitle,secondTitle);
        StringUtility.verifyEquals(secondTitle,thirdTitle);
        StringUtility.verifyEquals(firstTitle,thirdTitle);

        String expectedUrl = "http://practice.cybertekschool.com";
        for (String url: urls) {
            driver.get(url);
            String currentUrl = driver.getCurrentUrl();

            if (currentUrl.startsWith(expectedUrl)){
                System.out.println("Expected url is equal to actual url");
            }else {
                System.out.println("Fail");
                System.out.println("Actual Url = " + currentUrl);
                System.out.println("Expected Url = " + expectedUrl);
            }
        }

        driver.close();
    }
}
