package com.cbt.tests;

import com.cbt.utilities.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebDriverTests1 {
    public static WebDriver driver;

    @BeforeMethod
    public static void setUp(){
        driver = BrowserFactory.getDriver("chrome");
    }

    @Test
    public static void testCase1(){
        // Go to “https://practicecybertekschool.herokuapp.com”
        driver.get("https://practice-cybertekschool.herokuapp.com/");

        //Click on “Registration Form”
        driver.findElement(By.xpath("(//li[@class='list-group-item']//a[@href='/registration_form'])")).click();

        //Enter “wrong_dob” into date of birth input box.
        driver.findElement(By.xpath("//input[@name='birthday']")).sendKeys("wrong_dob");

        //Verify that warning message is displayed: “The date of birth is not valid”
        String result = driver.findElement(By.xpath("(//div[@class='col-sm-5']//small)[22]")).getText();
        Assert.assertEquals(result,"The date of birth is not valid");
    }
    @Test
    public static void testCase2(){
        // Go to “https://practicecybertekschool.herokuapp.com”
        driver.get("https://practice-cybertekschool.herokuapp.com/");

        //Click on “Registration Form”
        driver.findElement(By.xpath("(//li[@class='list-group-item']//a[@href='/registration_form'])")).click();

        //Verify that following options for programming languages are displayed: c++, java,JavaScript
        Assert.assertTrue(driver.findElement(By.id("inlineCheckbox1")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("inlineCheckbox2")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("inlineCheckbox3")).isDisplayed());
    }
    @Test
    public static void testCase3(){
        // Go to “https://practicecybertekschool.herokuapp.com”
        driver.get("https://practice-cybertekschool.herokuapp.com/");

        //Click on “Registration Form”
        driver.findElement(By.xpath("(//li[@class='list-group-item']//a[@href='/registration_form'])")).click();

        //Enter only one alphabetic character into first name input box.
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("a");

        //Verify that warning message is displayed: “first name must be more than 2 and less than 64 characters long”
        Assert.assertTrue(driver.findElement(By.xpath("(//small[@class='help-block'])[2]")).isDisplayed());
    }
    @Test
    public static void testCase4(){
        // Go to “https://practicecybertekschool.herokuapp.com”
        driver.get("https://practice-cybertekschool.herokuapp.com/");

        //Click on “Registration Form”
        driver.findElement(By.xpath("(//li[@class='list-group-item']//a[@href='/registration_form'])")).click();

        //Enter only one alphabetic character into last name input box.
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("a");

        //Verify that warning message is displayed: “The last name must be more than 2 and less than 64 characters long”
        Assert.assertTrue(driver.findElement(By.xpath("(//small[@class='help-block'])[6]")).isDisplayed());
    }
    @Test
    public static void testCase5(){
        // Go to “https://practicecybertekschool.herokuapp.com”
        driver.get("https://practice-cybertekschool.herokuapp.com/");

        //Click on “Registration Form”
        driver.findElement(By.xpath("(//li[@class='list-group-item']//a[@href='/registration_form'])")).click();

        //Enter any valid first name.
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Festina");
        //Enter any valid last name.
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Maxhera");
        //Enter any valid user name.
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("FestinaM");
        //Enter valid email.
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("festine@cybertekschool.com");
        //Enter any valid password.
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("FestinaM123");
        // Enter any valid phone number.
        driver.findElement(By.xpath("//input[@name='phone']")).sendKeys("111-222-3333");
        //Select gender.
        driver.findElement(By.xpath("//input[@value='female']")).click();
        //Enter any valid date of birth.
        driver.findElement(By.xpath("//input[@name='birthday']")).sendKeys("04/19/1997");
        //Select any department.
        Select drpDepartment = new Select(driver.findElement(By.name("department")));
        drpDepartment.selectByValue("DE");
        // Enter any job title.
        Select drpJobTitle = new Select(driver.findElement(By.name("job_title")));
        drpJobTitle.selectByVisibleText("Designer");
        //Select java as a programming language.
        driver.findElement(By.id("inlineCheckbox1")).click();

        //Click Sign up.
        driver.findElement(By.id("wooden_spoon")).click();

        //Verify that following success message is displayed: “You've successfully completed registration!”
        String confirmation = driver.findElement(By.xpath("//div[@class='alert alert-success']//p")).getText();
        Assert.assertEquals(confirmation,"You've successfully completed registration!");
    }
    @AfterMethod
    public static void tearDown(){
        driver.close();
    }
}
