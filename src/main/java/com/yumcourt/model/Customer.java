package com.yumcourt.model;

import org.json.JSONObject;

public class Customer {
    private long id;
    private String name;
    private String email;
    private String password;
    private Contact contact;

    public Customer(long id, String name, String email, String password, Contact contact) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
    }
    // Getters and Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Contact getContact() { return contact; }
    public void setContact(Contact contact) { this.contact = contact; }

    public String toJson() {
        JSONObject json = new JSONObject();
        json.put("id", this.id);
        json.put("name", this.name);
        json.put("email", this.email);
        json.put("contact", new JSONObject(this.contact)); // Ensure Contact has a proper toString() or implement its toJson() method
        return json.toString();
    }
}
