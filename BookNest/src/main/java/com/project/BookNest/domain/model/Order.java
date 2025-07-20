package com.project.BookNest.domain.model;

public class Order {

    private static int orderID=0;
    private String name;
    private String number;
    private String address;

    public Order(String name, String number, String address) {
        this.name = name;
        this.number = number;
        this.address = address;
        orderID++;
    }
    public Order(){

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNumber() {
        return number;
    }

    public static int getOrderID() {
        return orderID;
    }

    public static void setOrderID(int orderID) {
        Order.orderID = orderID;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void makeOrder(){

    }
}
