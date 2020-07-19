package com.webOrders.pages;

import com.webOrders.models.Order;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class ViewAllOrderPage {

    private WebDriver driver;

    public ViewAllOrderPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    @FindBy(id = "ctl00_MainContent_btnCheckAll")
    public WebElement checkAllButton;

    @FindBy(id = "ctl00_MainContent_btnUncheckAll")
    public WebElement unCheckAllButton;

    @FindBy(id = "ctl00_MainContent_btnDelete")
    public WebElement deleteSelectedButton;

    @FindBy(xpath = "//tr[position() > 1]")
    public List<WebElement> tableRows;

    @FindBy(xpath = "//th")
    public List<WebElement> tableColumns;

    public String getTargetColumn(Order order, String targetColumn){
        Integer columnIndex = 0;
        for (int i = 0; i < tableColumns.size(); i++) {
            if (tableColumns.get(i).getText().equals(targetColumn)){
                columnIndex = i;
                break;
            }
        }
        for (int i = 0; i < tableRows.size(); i++) {
            List<WebElement> rowCells = driver.findElements(By.xpath("(//tr[position() > 1])[1]//td"));
            if (rowCells.get(1).getText().equals(order.getCustomerName())){
                return rowCells.get(columnIndex).getText().trim();
            }
        }
        return "";
    }
    public void verifyOrder(Order order){
        String product = getTargetColumn(order, "Product");
        String quantity = getTargetColumn(order, "#");
        String street = getTargetColumn(order, "Street");
        String city = getTargetColumn(order, "City");
        String state = getTargetColumn(order, "State");
        String zip = getTargetColumn(order, "Zip");
        String card = getTargetColumn(order, "Card");
        String cardNumber = getTargetColumn(order, "Card Number");
        String expireDate = getTargetColumn(order, "Exp");

        Assert.assertEquals(product,order.getProduct());
        Assert.assertEquals(quantity,order.getQuantity().toString());
        Assert.assertEquals(street,order.getStreet());
        Assert.assertEquals(city,order.getCity());
        Assert.assertEquals(state,order.getState());
        Assert.assertEquals(zip,order.getZip().toString());
        Assert.assertEquals(card,order.getCard());
        Assert.assertEquals(cardNumber,order.getCardNr().toString());
        Assert.assertEquals(expireDate,order.getExpireDate());
    }
}
