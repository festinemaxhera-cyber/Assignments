package com.cbt.tests;

import com.cbt.utilities.BrowserFactory;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

public class TitleVerification3 {
    public static void main(String[] args) {
        List<String> urls = Arrays.asList("https://luluandgeorgia.com",
                "https://wayfair.com", "https://www.westelm.com", "https://walmart.com");

        for (String url: urls) {
            WebDriver driver = BrowserFactory.getDriver("chrome");
            driver.get(url);

            //Get title and remove spaces
            String title = driver.getTitle().replace(" ","");
            //Split title at non-word characters and take first split part
            String shortTitle= title.split("\\W")[0];
            //Get current URL
            String currentUrl = driver.getCurrentUrl();

            //Verify that URL of the website contains the title of the website.
            if(currentUrl.contains(shortTitle.toLowerCase())){
                System.out.println("Pass");
            }else {
                System.out.println("Fail");
            }
            driver.close();
        }
    }
}
