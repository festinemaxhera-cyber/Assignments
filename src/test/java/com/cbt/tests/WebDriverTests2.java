package com.cbt.tests;

import com.cbt.utilities.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebDriverTests2 {
    public static WebDriver driver;

    @BeforeMethod
    public static void setUp() {
        driver = BrowserFactory.getDriver("chrome");
    }

    @Test
    public static void testCase6(){

//        Step 1. Go to "https://www.tempmailaddress.com/"
        driver.get("https://www.tempmailaddress.com");

//        Step 2. Copy and save email as a string.
        String tempEmail = driver.findElement(By.id("email")).getText();

//        Step 3. Then go to “https://practicecybertekschool.herokuapp.com”
        driver.get("https://practice-cybertekschool.herokuapp.com/");

//        Step 4. And click on “Sign Up For Mailing List".
        driver.findElement(By.xpath("(//li[@class='list-group-item']//a[@href='/sign_up'])")).click();

//        Step 5. Enter any valid name.
        driver.findElement(By.xpath("//input[@name='full_name']")).sendKeys("Festina");

//        Step 6. Enter email from the Step 2.
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys(tempEmail);

//        Step 7. Click Sign Up
        driver.findElement(By.tagName("button")).click();

//        Step 8. Verify that following message is displayed: “Thank you for signing up. Click the button below to
//        return to the home page.”
        Assert.assertTrue(driver.findElement(By.tagName("h3")).isDisplayed());

//        Step 9. Navigate back to the “https://www.tempmailaddress.com/”
        driver.navigate().to("https://www.tempmailaddress.com");

//        Step 10. Verify that you’ve received an email from “do-not-reply@practice.cybertekschool.com”
        WebElement spanEmail = driver.findElement(By.xpath("//span[text()='do-not-reply@practice.cybertekschool.com']"));
        Assert.assertNotNull(spanEmail);

//        Step 11. Click on that email to open it.
        WebElement emailRow = driver.findElement(By.xpath("//tbody/tr[1]"));
        Assert.assertNotNull(emailRow);
        emailRow.click();
        
//        Step 12. Verify that email is from: “do-notreply@practice.cybertekschool.com”
//        Step 13. Verify that subject is: “Thanks for subscribing to practice.cybertekschool.com!”


//        driver.findElement(By.xpath("//tbody[@id='schranka']//tr[@class='hidden-xs hidden-sm klikaciRadek']//td[1]")).click();
//        String emailconfig = driver.findElement(By.id("odesilatel")).getText();
//        Assert.assertEquals(emailconfig,"do-not-reply@practice.cybertekschool.com");
//        String subject = driver.findElement(By.id("predmet")).getText();
//        Assert.assertEquals(subject,"Thanks for subscribing to practice.cybertekschool.com!");
//        String email = driver.findElement(By.xpath("//td[@class='from']")).getText();
//        String configEmail = " do-not-reply@practice.cybertekschool.com";
//        Assert.assertEquals(email,configEmail);
//        String subject = driver.findElement(By.xpath("//tr[@class='hidden-xs hidden-sm klikaciRadek']//td[2]")).getText();
//        Assert.assertEquals(subject,"Thanks for subscribing to practice.cybertekschool.com!");
    }
    @Test
    public static void testCase7(){
        //Go to "https://practicecybertekschool.herokuapp.com"
        driver.get("https://practice-cybertekschool.herokuapp.com/");

        //And click on “File Upload”.
        driver.findElement(By.xpath("(//li[@class='list-group-item']//a[@href='/upload'])")).click();

        //Upload any file with .txt extension from your computer.
        WebElement uploadFile = driver.findElement(By.xpath("//input[@id='file-upload']"));
        uploadFile.sendKeys("C:\\Users\\Festine.maxhera\\Desktop\\TestUpload.txt");

        // Click “Upload” button.
        driver.findElement(By.xpath("//input[@id='file-submit']")).click();

        //Verify that subject is: “File Uploaded!”
        String fileMessage = driver.findElement(By.tagName("h3")).getText();
        Assert.assertEquals(fileMessage,"File Uploaded!");

        //Verify that uploaded file name is displayed.
        Assert.assertTrue(driver.findElement(By.id("uploaded-files")).isDisplayed());
        String fileText = driver.findElement(By.id("uploaded-files")).getText();
        Assert.assertEquals(fileText, "TestUpload.txt");
    }
    @Test
    public static void testCase8() {
        //Go to "https://practicecybertekschool.herokuapp.com"
        driver.get("https://practice-cybertekschool.herokuapp.com/");

        //And click on “Autocomplete”.
        driver.findElement(By.xpath("(//li[@class='list-group-item']//a[@href='/autocomplete'])")).click();

        //Enter “United States of America” into country input box.
        driver.findElement(By.xpath("//input[@id='myCountry']")).sendKeys("United States of America");
        driver.findElement(By.xpath("//input[@value='Submit']")).click();

        //Verify that following message is displayed: “You selected: United States of America”
        Assert.assertTrue(driver.findElement(By.id("result")).isDisplayed());
    }

    @AfterMethod
    public static void tearDown() {
        driver.close();
    }
}

