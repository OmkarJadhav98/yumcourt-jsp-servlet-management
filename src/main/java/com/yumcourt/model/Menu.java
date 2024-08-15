package com.yumcourt.model;

public class Menu {
    private long id;
    private String name;
    private String description;
    private double price;
    private Restaurant restaurant;
    private boolean availability;

    public Menu(long id, String name, String description, double price, Restaurant restaurant, boolean availability) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.restaurant = restaurant;
        this.availability = availability;
    }

    // Getters and Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public Restaurant getRestaurant() { return restaurant; }
    public void setRestaurant(Restaurant restaurant) { this.restaurant = restaurant; }
    public boolean isAvailable() { return availability; }
    public void setAvailability(boolean availability) { this.availability = availability; }

    public String toJson() {
        return String.format("{\"id\":%d,\"name\":\"%s\",\"description\":\"%s\",\"price\":%f,\"restaurantId\":%d,\"availability\":%b}",
                id, name, description, price, restaurant.getId(), availability);
    }
}
