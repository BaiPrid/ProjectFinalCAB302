package com.example.finalassignmentcab302.Tables;


public class Order {

    private int orderId;
    private int userId;
    private int organisationId;
    private String orderDateTime;
    private Float amount;
    private String billingAddress;


    public Order(int orderId, int userId, int organisationId, String orderDateTime, Float amount, String billingAddress){
        this.orderId = orderId;
        this.userId = userId;
        this.organisationId = organisationId;
        this.orderDateTime = orderDateTime;
        this.amount = amount;
        this.billingAddress = billingAddress;
    }

    public Order(int userId, int organisationId, String orderDateTime, Float amount, String billingAddress){
        this.userId = userId;
        this.organisationId = organisationId;
        this.orderDateTime = orderDateTime;
        this.amount = amount;
        this.billingAddress = billingAddress;
    }

    public int getOrderId(){
        return orderId;
    }
    public int getOrganisationId(){
        return organisationId;
    }
    public int getUserId(){
        return userId;
    }
    public String getOrderDateTime(){
        return orderDateTime;
    }
    public Float getAmount(){
        return amount;
    }
    public String getBillingAddress(){
        return billingAddress;
    }



}
