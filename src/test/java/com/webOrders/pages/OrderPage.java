package com.webOrders.pages;

import com.webOrders.models.Order;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class OrderPage {

    public OrderPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "ctl00_MainContent_fmwOrder_ddlProduct")
    public WebElement productSelect;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtQuantity")
    public WebElement quantityInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtUnitPrice")
    public WebElement pricePerUnitInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtDiscount")
    public WebElement discountInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtTotal")
    public WebElement totalInput;

    @FindBy(xpath = "//input[@value='Calculate']")
    public WebElement calculateButton;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtName")
    public WebElement customerNameInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox2")
    public WebElement streetInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox3")
    public WebElement cityInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox4")
    public WebElement stateInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox5")
    public WebElement zipInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_cardList_0")
    public WebElement cardVisaRadio;

    @FindBy(id = "ctl00_MainContent_fmwOrder_cardList_1")
    public WebElement cardMasterRadio;

    @FindBy(id = "ctl00_MainContent_fmwOrder_cardList_2")
    public WebElement cardAmericanRadio;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox6")
    public WebElement cardNrInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox1")
    public WebElement expireDateInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_InsertButton")
    public WebElement processButton;

    @FindBy(xpath = "//input[@value='Reset']")
    public WebElement resetButton;

    public void makeOrder(Order order){
        Select product = new Select(productSelect);
        product.selectByVisibleText(order.getProduct());
        quantityInput.sendKeys(order.getQuantity().toString());
        customerNameInput.sendKeys(order.getCustomerName());
        streetInput.sendKeys(order.getStreet());
        stateInput.sendKeys(order.getState());
        cityInput.sendKeys(order.getCity());
        zipInput.sendKeys(order.getZip().toString());
        switch (order.getCard()){
            case "Visa":
                cardVisaRadio.click();
                break;
            case "Master":
                cardMasterRadio.click();
                break;
            case "American":
                cardAmericanRadio.click();
                break;
        }
        cardNrInput.sendKeys(order.getCardNr().toString());
        expireDateInput.sendKeys(order.getExpireDate());
        processButton.click();
    }
}
