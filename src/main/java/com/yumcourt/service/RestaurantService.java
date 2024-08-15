package com.yumcourt.service;

import com.yumcourt.model.Contact;
import com.yumcourt.model.Address;
import com.yumcourt.model.Menu;
import com.yumcourt.model.Restaurant;
import com.yumcourt.repository.ContactRepository;
import com.yumcourt.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RestaurantService extends ContactRepository {
    private static RestaurantRepository restaurantRepository;
    private final ContactRepository contactRepository;
    private final Scanner scanner;

    public RestaurantService(RestaurantRepository restaurantRepository, ContactRepository contactRepository) {
        RestaurantService.restaurantRepository = restaurantRepository;
        this.contactRepository = contactRepository;
        this.scanner = new Scanner(System.in);
    }

    public List<Restaurant> retrieveRestaurants() {
        return restaurantRepository.retrieveRestaurants();
    }

    public Restaurant getRestaurantById(long id) {
        return restaurantRepository.findById(id);
    }

    public void createRestaurant() {
        System.out.println("Enter Restaurant ID:");
        long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter Name:");
        String name = scanner.nextLine();

        System.out.println("Enter Contact ID:");
        long contactId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter Contact Phone Number:");
        long phone = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter Address Name:");
        String addressName = scanner.nextLine();

        System.out.println("Enter Flat Number:");
        long flatNo = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter Building Name:");
        String buildingName = scanner.nextLine();

        System.out.println("Enter Street:");
        String street = scanner.nextLine();

        System.out.println("Enter City:");
        String city = scanner.nextLine();

        System.out.println("Enter State:");
        String state = scanner.nextLine();

        System.out.println("Enter Pin Code:");
        long pinCode = scanner.nextLong();
        scanner.nextLine();

        Address address = new Address(0, addressName, flatNo, buildingName, street, city, pinCode,state );
        Contact contact = new Contact(contactId, phone, address);
        contactRepository.createContact(contact);

        Restaurant restaurant = new Restaurant(id, name, contact, new ArrayList<>());
        restaurantRepository.createRestaurant(restaurant);
        System.out.println("Restaurant created successfully.");
    }

    public void updateRestaurant(Restaurant restaurant) {
        System.out.println("Enter Contact ID:");
        long contactId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter Contact Phone Number:");
        long phone = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter Address Name:");
        String addressName = scanner.nextLine();

        System.out.println("Enter Flat Number:");
        long flatNo = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter Building Name:");
        String buildingName = scanner.nextLine();

        System.out.println("Enter Street:");
        String street = scanner.nextLine();

        System.out.println("Enter City:");
        String city = scanner.nextLine();

        System.out.println("Enter State:");
        String state = scanner.nextLine();

        System.out.println("Enter Pin Code:");
        long pinCode = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        Address address = new Address(0, addressName, flatNo, buildingName, street, city, pinCode,state);
        Contact contact = new Contact(contactId, phone, address);
        contactRepository.updateContact(contact);

        restaurantRepository.updateRestaurant(restaurant);
        System.out.println("Restaurant updated successfully.");
    }

    public void deleteRestaurant() {
        System.out.println("Enter Restaurant ID to delete:");
        long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        restaurantRepository.deleteRestaurant(id);
        System.out.println("Restaurant deleted successfully.");
    }

    public static List<Menu> findRestaurantById(long id) {
        restaurantRepository.findById(id);
        return null;
    }
}