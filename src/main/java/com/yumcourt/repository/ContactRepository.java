package com.yumcourt.repository;

import com.yumcourt.model.Contact;
import com.yumcourt.model.Address;
import com.yumcourt.service.ConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository {

    private static final Logger logger = LoggerFactory.getLogger(ContactRepository.class);

    private Connection getConnection() throws SQLException {
        return ConnectionService.getConnection();
    }

    public Contact getContactById(int id) {
        String query = "SELECT * FROM contacts WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int contactId = rs.getInt("id");
                    long phone = rs.getLong("phone");

                    // Retrieve Address fields from the database
                    long flatNo = rs.getLong("flatNo");
                    String buildingName = rs.getString("buildingName");
                    String street = rs.getString("street");
                    String city = rs.getString("city");
                    long pinCode = rs.getLong("pinCode");
                    String state = rs.getString("state");

                    // Create Address instance
                    Address address = new Address(contactId, "", flatNo, buildingName, street, city, pinCode, state);

                    return new Contact(contactId, phone, address);
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving contact with ID {}: {}", id, e.getMessage(), e);
        }
        return null;
    }

    public void createContact(Contact contact) {
        String query = "INSERT INTO contacts (id, phone, flatNo, buildingName, street, city, state, pinCode) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setLong(1, contact.getId());
            pstmt.setLong(2, contact.getPhone());
            pstmt.setLong(3, contact.getAddress().getFlatNo());
            pstmt.setString(4, contact.getAddress().getBuildingName());
            pstmt.setString(5, contact.getAddress().getStreet());
            pstmt.setString(6, contact.getAddress().getCity());
            pstmt.setString(7, contact.getAddress().getState());
            pstmt.setLong(8, contact.getAddress().getPinCode());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error creating contact with ID {}: {}", contact.getId(), e.getMessage(), e);
        }
    }

    public void updateContact(Contact contact) {
        String query = "UPDATE contacts SET phone = ?, flatNo = ?, buildingName = ?, street = ?, city = ?, state = ?, pinCode = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setLong(1, contact.getPhone());
            pstmt.setLong(2, contact.getAddress().getFlatNo());
            pstmt.setString(3, contact.getAddress().getBuildingName());
            pstmt.setString(4, contact.getAddress().getStreet());
            pstmt.setString(5, contact.getAddress().getCity());
            pstmt.setString(6, contact.getAddress().getState());
            pstmt.setLong(7, contact.getAddress().getPinCode());
            pstmt.setLong(8, contact.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating contact with ID {}: {}", contact.getId(), e.getMessage(), e);
        }
    }

    public void deleteContact(long id) {
        String query = "DELETE FROM contacts WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting contact with ID {}: {}", id, e.getMessage(), e);
        }
    }

    public Contact findById(long contactId) {
        String query = "SELECT * FROM contacts WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setLong(1, contactId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    long id = rs.getInt("id");
                    long phone = rs.getLong("phone");

                    // Retrieve Address fields from the database
                    long flatNo = rs.getLong("flatNo");
                    String buildingName = rs.getString("buildingName");
                    String street = rs.getString("street");
                    String city = rs.getString("city");
                    long pinCode = rs.getLong("pinCode");
                    String state = rs.getString("state");

                    // Create Address instance
                    Address address = new Address(id, "", flatNo, buildingName, street, city, pinCode, state);

                    return new Contact(id, phone, address);
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding contact with ID {}: {}", contactId, e.getMessage(), e);
        }
        return null;
    }

    public List<Contact> retrieveContacts() {
        String query = "SELECT * FROM contacts";
        List<Contact> contacts = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("id");
                long phone = rs.getLong("phone");

                // Retrieve Address fields from the database
                long flatNo = rs.getLong("flatNo");
                String buildingName = rs.getString("buildingName");
                String street = rs.getString("street");
                String city = rs.getString("city");
                long pinCode = rs.getLong("pinCode");
                String state = rs.getString("state");

                // Create Address instance
                Address address = new Address(id, "", flatNo, buildingName, street, city, pinCode, state);

                // Create Contact instance and add to list
                Contact contact = new Contact(id, phone, address);
                contacts.add(contact);
            }
        } catch (SQLException e) {
            logger.error("Error retrieving contacts: {}", e.getMessage(), e);
        }
        return contacts;
    }

}
