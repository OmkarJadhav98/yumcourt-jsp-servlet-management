package com.yumcourt.service;

import com.yumcourt.model.Contact;
import com.yumcourt.model.Customer;
import com.yumcourt.repository.ContactRepository;
import com.yumcourt.repository.CustomerRepository;

import java.util.List;
import java.util.Scanner;

public class CustomerService {
    private static CustomerRepository customerRepository;
    private final ContactRepository contactRepository;
    private final Scanner scanner;

    public CustomerService(CustomerRepository customerRepository, ContactRepository contactRepository) {
        this.customerRepository = customerRepository;
        this.contactRepository = contactRepository;
        this.scanner = new Scanner(System.in);
    }

    public List<Customer> retrieveCustomers() {
        return customerRepository.retrieveCustomers();
    }

    public void createCustomer() {
        System.out.println("Enter Customer ID:");
        long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter Name:");
        String name = scanner.nextLine();

        System.out.println("Enter Email:");
        String email = scanner.nextLine();

        System.out.println("Enter Password:");
        String password = scanner.nextLine();

        System.out.println("Enter Contact ID:");
        long contactId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        Contact contact = contactRepository.findById(contactId); // Ensure this method exists

        Customer customer = new Customer(id, name, email, password, contact);
        customerRepository.createCustomer(customer);
        System.out.println("Customer created successfully.");
    }

    public void updateCustomer() {
        System.out.println("Enter Customer ID to update:");
        long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter New Name:");
        String name = scanner.nextLine();

        System.out.println("Enter New Email:");
        String email = scanner.nextLine();

        System.out.println("Enter New Password:");
        String password = scanner.nextLine();

        System.out.println("Enter New Contact ID:");
        long contactId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        Contact contact = contactRepository.findById(contactId); // Ensure this method exists

        Customer customer = new Customer(id, name, email, password, contact);
        customerRepository.updateCustomer(customer);
        System.out.println("Customer updated successfully.");
    }

    public void deleteCustomer() {
        System.out.println("Enter Customer ID to delete:");
        long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        customerRepository.deleteCustomer(id);
        System.out.println("Customer deleted successfully.");
    }

    public static Customer findCustomerById(long id) {
        return customerRepository.findById(id);
    }
}
