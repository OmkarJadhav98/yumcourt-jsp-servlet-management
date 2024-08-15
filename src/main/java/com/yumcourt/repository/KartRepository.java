package com.yumcourt.repository;

import com.yumcourt.model.Kart;
import com.yumcourt.model.Customer;
import com.yumcourt.model.Restaurant;
import com.yumcourt.service.ConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KartRepository {

    private static final Logger logger = LoggerFactory.getLogger(KartRepository.class);
    private Connection connection;

    private void initConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = ConnectionService.getConnection();
        }
    }

    public List<Kart> retrieveKarts() {
        List<Kart> karts = new ArrayList<>();
        String query = "SELECT * FROM kart";

        try {
            this.initConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                double finalPrice = resultSet.getDouble("final_price");
                int customerId = resultSet.getInt("customer_id");
                long restaurantId = resultSet.getLong("restaurant_id");

                // Fetch Customer and Restaurant
                ContactRepository contactRepository = new ContactRepository();
                CustomerRepository customerRepository = new CustomerRepository(contactRepository);
                Customer customer = customerRepository.findById(customerId);
                RestaurantRepository restaurantRepository = new RestaurantRepository(contactRepository);
                Restaurant restaurant = restaurantRepository.findById(restaurantId);

                Kart kart = new Kart(id, finalPrice, customer, restaurant);
                karts.add(kart);
            }
        } catch (SQLException e) {
            logger.error("SQL error: {}", e.getMessage(), e);
        } finally {
            closeConnection();
        }
        return karts;
    }

    public void createKart(Kart kart) {
        String query = "INSERT INTO kart (final_price, customer_id, restaurant_id) VALUES (?, ?, ?)";
        try {
            this.initConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, kart.getFinalPrice());
            preparedStatement.setLong(2, kart.getCustomer().getId());
            preparedStatement.setLong(3, kart.getRestaurant().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQL error: {}", e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    public void updateKart(Kart kart) {
        String query = "UPDATE kart SET final_price = ?, customer_id = ?, restaurant_id = ? WHERE id = ?";
        try {
            this.initConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, kart.getFinalPrice());
            preparedStatement.setLong(2, kart.getCustomer().getId());
            preparedStatement.setLong(3, kart.getRestaurant().getId());
            preparedStatement.setLong(4, kart.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQL error: {}", e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    public void deleteKart(long id) {
        String query = "DELETE FROM kart WHERE id = ?";
        try {
            this.initConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQL error: {}", e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    public Kart findById(long id) {
        String query = "SELECT * FROM kart WHERE id = ?";
        Kart kart = null;
        try {
            this.initConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                double finalPrice = resultSet.getDouble("final_price");
                int customerId = resultSet.getInt("customer_id");
                long restaurantId = resultSet.getLong("restaurant_id");

                // Fetch Customer and Restaurant
                ContactRepository contactRepository = new ContactRepository();
                CustomerRepository customerRepository = new CustomerRepository(contactRepository);
                Customer customer = customerRepository.findById(customerId);
                RestaurantRepository restaurantRepository = new RestaurantRepository(contactRepository);
                Restaurant restaurant = restaurantRepository.findById(restaurantId);

                kart = new Kart(id, finalPrice, customer, restaurant);
            }
        } catch (SQLException e) {
            logger.error("SQL error: {}", e.getMessage(), e);
        } finally {
            closeConnection();
        }
        return kart;
    }

    private void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Error closing connection: {}", e.getMessage(), e);
            }
        }
    }
}
