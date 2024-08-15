package com.yumcourt.model;

import java.time.LocalDateTime;

public class Order {
    private long id;
    private Customer customer;
    private Menu menu;
    private DeliveryExecutive deliveryExecutive;
    private LocalDateTime timestamp;

    // Constructor
    public Order(long id, Customer customer, Menu menu, DeliveryExecutive deliveryExecutive, LocalDateTime timestamp) {
        this.id = id;
        this.customer = customer;
        this.menu = menu;
        this.deliveryExecutive = deliveryExecutive;
        this.timestamp = timestamp;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public DeliveryExecutive getDeliveryExecutive() {
        return deliveryExecutive;
    }

    public void setDeliveryExecutive(DeliveryExecutive deliveryExecutive) {
        this.deliveryExecutive = deliveryExecutive;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    // Convert Order to JSON
    public String toJson() {
        // Use a library like Jackson or Gson for actual JSON conversion
        return String.format("{\"id\":%d,\"customer\":{\"id\":%d},\"menu\":{\"id\":%d},\"deliveryExecutive\":{\"id\":%d},\"timestamp\":\"%s\"}",
                id, customer.getId(), menu.getId(), deliveryExecutive.getId(), timestamp.toString());
    }
}
