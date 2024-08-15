package com.yumcourt.repository;

import com.yumcourt.model.Order;
import com.yumcourt.model.Customer;
import com.yumcourt.model.Menu;
import com.yumcourt.model.DeliveryExecutive;
import com.yumcourt.service.ConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    private static final Logger logger = LoggerFactory.getLogger(OrderRepository.class);
    private final CustomerRepository customerRepository;
    private final MenuRepository menuRepository;
    private final DeliveryExecutiveRepository deliveryExecutiveRepository;

    public OrderRepository(CustomerRepository customerRepository, MenuRepository menuRepository, DeliveryExecutiveRepository deliveryExecutiveRepository) {
        this.customerRepository = customerRepository;
        this.menuRepository = menuRepository;
        this.deliveryExecutiveRepository = deliveryExecutiveRepository;
    }

    private Connection getConnection() throws SQLException {
        return ConnectionService.getConnection();
    }

    public List<Order> retrieveOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                long customerId = resultSet.getLong("customer_id");
                long menuId = resultSet.getLong("menu_id");
                long deliveryExecutiveId = resultSet.getLong("delivery_executive_id");
                Timestamp timestamp = resultSet.getTimestamp("timestamp");

                Customer customer = customerRepository.findById(customerId);
                Menu menu = menuRepository.findById(menuId);
                DeliveryExecutive deliveryExecutive = deliveryExecutiveRepository.findById(deliveryExecutiveId);

                Order order = new Order(id, customer, menu, deliveryExecutive, timestamp.toLocalDateTime());
                orders.add(order);
            }
        } catch (SQLException e) {
            logger.error("SQL error: {}", e.getMessage(), e);
        }
        return orders;
    }

    public void createOrder(Order order) {
        String query = "INSERT INTO orders (customer_id, menu_id, delivery_executive_id, timestamp) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, order.getCustomer().getId());
            preparedStatement.setLong(2, order.getMenu().getId());
            preparedStatement.setLong(3, order.getDeliveryExecutive().getId());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(order.getTimestamp()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQL error: {}", e.getMessage(), e);
        }
    }

    public void updateOrder(Order order) {
        String query = "UPDATE orders SET customer_id = ?, menu_id = ?, delivery_executive_id = ?, timestamp = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, order.getCustomer().getId());
            preparedStatement.setLong(2, order.getMenu().getId());
            preparedStatement.setLong(3, order.getDeliveryExecutive().getId());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(order.getTimestamp()));
            preparedStatement.setLong(5, order.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQL error: {}", e.getMessage(), e);
        }
    }

    public void deleteOrder(long id) {
        String query = "DELETE FROM orders WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQL error: {}", e.getMessage(), e);
        }
    }

    public Order findById(long id) {
        Order order = null;
        String query = "SELECT * FROM orders WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    long customerId = resultSet.getLong("customer_id");
                    long menuId = resultSet.getLong("menu_id");
                    long deliveryExecutiveId = resultSet.getLong("delivery_executive_id");
                    Timestamp timestamp = resultSet.getTimestamp("timestamp");

                    Customer customer = customerRepository.findById(customerId);
                    Menu menu = menuRepository.findById(menuId);
                    DeliveryExecutive deliveryExecutive = deliveryExecutiveRepository.findById(deliveryExecutiveId);

                    order = new Order(id, customer, menu, deliveryExecutive, timestamp.toLocalDateTime());
                }
            }
        } catch (SQLException e) {
            logger.error("SQL error: {}", e.getMessage(), e);
        }
        return order;
    }
}
