package com.yumcourt.model;

public class Kart {
    private long id;
    private double finalPrice;
    private Customer customer;
    private Restaurant restaurant;

    public Kart(long id, double finalPrice, Customer customer, Restaurant restaurant) {
        this.id = id;
        this.finalPrice = finalPrice;
        this.customer = customer;
        this.restaurant = restaurant;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Kart{" +
                "id=" + id +
                ", finalPrice=" + finalPrice +
                ", customer=" + customer +
                ", restaurant=" + restaurant +
                '}';
    }
}
