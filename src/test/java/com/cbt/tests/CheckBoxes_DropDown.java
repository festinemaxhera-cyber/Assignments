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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

public class CheckBoxes_DropDown {
    public static WebDriver driver;

    @BeforeMethod
    public static void setUp() {
        driver = BrowserFactory.getDriver("chrome");
    }

    @Test
    public static void days() throws InterruptedException {
        driver.get("http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCheckBox");
        Thread.sleep(2000);
        List<WebElement> checkboxDaysList = driver.findElements(By.cssSelector("td input"));
        List<WebElement> labelDaysList = driver.findElements(By.cssSelector("td label"));

        Random random = new Random();
        int fridayCounter = 0;
        while(fridayCounter != 3) {
            int checkboxIndex = random.nextInt(checkboxDaysList.size()-1);
            if(checkboxDaysList.get(checkboxIndex).isEnabled()){
                if(labelDaysList.get(checkboxIndex).getText().equals("Friday")) {
                    fridayCounter++;
                }
                checkboxDaysList.get(checkboxIndex).click();
                System.out.println(labelDaysList.get(checkboxIndex).getText());
                checkboxDaysList.get(checkboxIndex).click();
            }
        }
    }
    @Test
    public static void todaysDate(){
        driver.get("http://practice.cybertekschool.com/dropdown");

        WebElement year = driver.findElement(By.id("year"));
        Select years = new Select(year);

        WebElement month = driver.findElement(By.id("month"));
        Select months = new Select(month);

        WebElement day = driver.findElement(By.id("day"));
        Select days = new Select(day);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MMMM/dd");
        LocalDateTime time = LocalDateTime.now();

        String date = years.getFirstSelectedOption().getText()+ "/" + months.getFirstSelectedOption().getText()+ "/" + days.getFirstSelectedOption().getText();

        Assert.assertEquals(dateTimeFormatter.format(time), date);
    }
    @Test
    public static void departmentsSort(){
        driver.get("https://www.amazon.com/");
        WebElement departmentElement = driver.findElement(By.xpath("//select"));
        Select departmentSelect = new Select(departmentElement);

        String defaultDepartmentValue = departmentSelect.getFirstSelectedOption().getText();
        Assert.assertEquals(defaultDepartmentValue, "All Departments");

        List<WebElement> options = departmentSelect.getOptions();
        Boolean sorted = true;
        String previousDepartment = "";
        for (int i = 1; i < options.size() - 1; i++) {
            String department = options.get(i).getText();

            if (department.compareTo(previousDepartment) < 0) {
                sorted = false;
                break;
            }
            previousDepartment = department;
        }
        Assert.assertFalse(sorted);
    }

    @AfterMethod
    public static void tearDown() {
        driver.close();
    }
}
