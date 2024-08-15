package com.yumcourt.repository;

import com.yumcourt.model.Restaurant;
import com.yumcourt.model.Contact;
import com.yumcourt.model.Menu;
import com.yumcourt.service.ConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantRepository {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantRepository.class);
    private Connection connection;
    private final ContactRepository contactRepository;

    public RestaurantRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    private void initConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = ConnectionService.getConnection();
        }
    }

    public Restaurant findById(long restaurantId) {
        String query = "SELECT * FROM restaurant WHERE id = ?";
        Restaurant restaurant = null;
        try {
            this.initConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, restaurantId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                long contactId = resultSet.getLong("contact_id");

                Contact contact = contactRepository.findById(contactId);
                List<Menu> menus = retrieveMenusByRestaurantId(id);

                restaurant = new Restaurant(id, name, contact, menus);
            }
        } catch (SQLException e) {
            logger.error("SQL error: {}", e.getMessage(), e);
        } finally {
            closeConnection();
        }
        return restaurant;
    }

    public List<Restaurant> retrieveRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        String query = "SELECT * FROM restaurant";

        try {
            this.initConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                long contactId = resultSet.getLong("contact_id");

                Contact contact = contactRepository.findById(contactId);
                List<Menu> menus = retrieveMenusByRestaurantId(id);

                Restaurant restaurant = new Restaurant(id, name, contact, menus);
                restaurants.add(restaurant);
            }
        } catch (SQLException e) {
            logger.error("SQL error: {}", e.getMessage(), e);
        } finally {
            closeConnection();
        }
        return restaurants;
    }

    public void createRestaurant(Restaurant restaurant) {
        String query = "INSERT INTO restaurant (name, contact_id) VALUES (?, ?)";
        try {
            this.initConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, restaurant.getName());
            preparedStatement.setLong(2, restaurant.getContact().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQL error: {}", e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    public void updateRestaurant(Restaurant restaurant) {
        String query = "UPDATE restaurant SET name = ?, contact_id = ? WHERE id = ?";
        try {
            this.initConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, restaurant.getName());
            preparedStatement.setLong(2, restaurant.getContact().getId());
            preparedStatement.setLong(3, restaurant.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQL error: {}", e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    public void deleteRestaurant(long id) {
        String query = "DELETE FROM restaurant WHERE id = ?";
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

    private List<Menu> retrieveMenusByRestaurantId(long restaurantId) {
        List<Menu> menus = new ArrayList<>();
        String query = "SELECT * FROM menu WHERE restaurant_id = ?";

        try {
            this.initConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, restaurantId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long menuId = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                boolean availability = resultSet.getBoolean("availability");

                Menu menu = new Menu(menuId, name, description, price, null, availability);
                menus.add(menu);
            }
        } catch (SQLException e) {
            logger.error("SQL error: {}", e.getMessage(), e);
        } finally {
            closeConnection();
        }
        return menus;
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
