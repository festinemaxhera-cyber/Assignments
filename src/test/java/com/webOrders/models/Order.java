package com.webOrders.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
    private String product;
    private Integer quantity;
    private Double pricePerUnit;
    private Double discount;
    private String customerName;
    private String street;
    private String city;
    private String state;
    private Integer zip;
    private String card;
    private Long cardNr;
    private String expireDate;
    private Double total;
    private String date;


    public Order() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date today = new Date();
        date = formatter.format(today);
    }

    public Order(String product, Integer quantity, Double pricePerUnit, Double discount, String customerName, String street, String city, String state, Integer zip, String card, Long cardNr, String expireDate, Double total) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        date = formatter.format(today);

        this.product = product;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.discount = discount;
        this.customerName = customerName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.card = card;
        this.cardNr = cardNr;
        this.expireDate = expireDate;
        this.total = total;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public Long getCardNr() {
        return cardNr;
    }

    public void setCardNr(Long cardNr) {
        this.cardNr = cardNr;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    public String getDate() {
        return date;
    }
}
