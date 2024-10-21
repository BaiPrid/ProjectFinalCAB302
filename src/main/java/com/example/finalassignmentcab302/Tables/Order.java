package com.example.finalassignmentcab302.Tables;

/**
 * A simple model class representing an Order with a orderID, userID, OrganisationID, Order datetime, amount and billingaddress.
 */
public class Order {

    private int orderId;
    private int userId;
    private int organisationId;
    private String orderDateTime;
    private Float amount;
    private String billingAddress;

    /**
     * Constructs a new Order with the specified orderID, userID, OrganisationID, Order datetime, amount and billingaddress.
     * @param orderId The orderID of the order
     * @param userId The userID of the user who made and order
     * @param organisationId The organisation ID of the organisation payment made to
     * @param orderDateTime The DateTime of the order
     * @param amount The amount of the payment
     * @param billingAddress The billingaddress of the user making the order
     */
    public Order(int orderId, int userId, int organisationId, String orderDateTime, Float amount, String billingAddress){
        this.orderId = orderId;
        this.userId = userId;
        this.organisationId = organisationId;
        this.orderDateTime = orderDateTime;
        this.amount = amount;
        this.billingAddress = billingAddress;
    }

    /**
     * Constructs a new Order with the specified  userID, OrganisationID, Order datetime, amount and billingaddress.
     * @param userId The userID of the user who made and order
     * @param organisationId The organisation ID of the organisation payment made to
     * @param orderDateTime The DateTime of the order
     * @param amount The amount of the payment
     * @param billingAddress The billingaddress of the user making the order
     */
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
