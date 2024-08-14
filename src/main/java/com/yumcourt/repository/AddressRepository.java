package com.yumcourt.repository;

import com.yumcourt.model.Address;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yumcourt.service.ConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddressRepository {

    private static final Logger logger = LoggerFactory.getLogger(AddressRepository.class);
    private Connection connection = null;

    // Initialize the connection
    private void initConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = ConnectionService.getConnection();
        }
    }

    // Retrieve all addresses
    public List<Address> retrieveAddresses() {
        List<Address> addresses = new ArrayList<>();
        String query = "SELECT * FROM address";

        try {
            initConnection();  // Ensure connection is initialized
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    long flatNo = resultSet.getLong("flatNo");
                    String buildingName = resultSet.getString("buildingName");
                    String street = resultSet.getString("street");
                    String city = resultSet.getString("city");
                    long pinCode = resultSet.getLong("pinCode");
                    String state = resultSet.getString("state");

                    Address address = new Address(id, name, flatNo, buildingName, street, city, pinCode, state);
                    addresses.add(address);
                }
            }
        } catch (SQLException e) {
            logger.error("SQL error: {}", e.getMessage(), e);
        }
        return addresses;
    }

    // Create a new address
    public void createAddress(Address address) {
        String query = "INSERT INTO address (id, name, flatNo, buildingName, street, city, state, pinCode) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            initConnection();  // Ensure connection is initialized
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, address.getId());
                preparedStatement.setString(2, address.getName());
                preparedStatement.setLong(3, address.getFlatNo());
                preparedStatement.setString(4, address.getBuildingName());
                preparedStatement.setString(5, address.getStreet());
                preparedStatement.setString(6, address.getCity());
                preparedStatement.setString(7, address.getState());
                preparedStatement.setLong(8, address.getPinCode());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error("SQL error: {}", e.getMessage(), e);
        }
    }

    // Update an existing address
    public void updateAddress(Address address) {
        String query = "UPDATE address SET name = ?, flatNo = ?, buildingName = ?, street = ?, city = ?, state = ?, pinCode = ? WHERE id = ?";
        try {
            initConnection();  // Ensure connection is initialized
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, address.getName());
                preparedStatement.setLong(2, address.getFlatNo());
                preparedStatement.setString(3, address.getBuildingName());
                preparedStatement.setString(4, address.getStreet());
                preparedStatement.setString(5, address.getCity());
                preparedStatement.setString(6, address.getState());
                preparedStatement.setLong(7, address.getPinCode());
                preparedStatement.setLong(8, address.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error("SQL error: {}", e.getMessage(), e);
        }
    }

    // Delete an address by id
    public void deleteAddress(long id) {
        String query = "DELETE FROM address WHERE id = ?";
        try {
            initConnection();  // Ensure connection is initialized
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error("SQL error: {}", e.getMessage(), e);
        }
    }

    // Find an address by id
    public Address findById(long addressId) {
        String query = "SELECT * FROM address WHERE id = ?";
        Address address = null;
        try {
            initConnection();  // Ensure connection is initialized
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, addressId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        long id = resultSet.getLong("id");
                        String name = resultSet.getString("name");
                        long flatNo = resultSet.getLong("flatNo");
                        String buildingName = resultSet.getString("buildingName");
                        String street = resultSet.getString("street");
                        String city = resultSet.getString("city");
                        long pinCode = resultSet.getLong("pinCode");
                        String state = resultSet.getString("state");

                        address = new Address(id, name, flatNo, buildingName, street, city, pinCode, state );
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("SQL error: {}", e.getMessage(), e);
        }
        return address;
    }
}
