package com.webOrders.tests;

import com.cbt.utilities.BrowserFactory;
import com.webOrders.models.Order;
import com.webOrders.pages.LoginPage;
import com.webOrders.pages.OrderPage;
import com.webOrders.pages.ViewAllOrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrderTest {
    public static WebDriver driver;

    @BeforeMethod
    public static void setUp() {
        driver = BrowserFactory.getDriver("chrome");
    }

    @Test
    public void orderTest() throws InterruptedException {
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");
        LoginPage login = new LoginPage(driver);
        login.login("Tester","test");

        driver.findElement(By.linkText("Order")).click();

        Order order = new Order();
        order.setProduct("ScreenSaver");
        order.setQuantity(4);
        order.setCustomerName("John");
        order.setStreet("15, Ringer Street");
        order.setCity("Las Vegas, NV");
        order.setState("US");
        order.setZip(12345);
        order.setCard("Visa");
        order.setCardNr(1233345656788L);
        order.setExpireDate("02/05");

        OrderPage orderPage = new OrderPage(driver);
        orderPage.makeOrder(order);

        driver.findElement(By.linkText("View all orders")).click();

        ViewAllOrderPage viewAllOrderPage = new ViewAllOrderPage(driver);
        viewAllOrderPage.verifyOrder(order);
    }
    @AfterMethod
    public static void tearDown() {
        driver.close();
    }
}
