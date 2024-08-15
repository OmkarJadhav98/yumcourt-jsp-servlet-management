package com.yumcourt.service;

import com.yumcourt.model.Order;
import com.yumcourt.model.Customer;
import com.yumcourt.model.Menu;
import com.yumcourt.model.DeliveryExecutive;
import com.yumcourt.repository.OrderRepository;
import com.yumcourt.repository.CustomerRepository;
import com.yumcourt.repository.MenuRepository;
import com.yumcourt.repository.DeliveryExecutiveRepository;

import java.util.List;

public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final MenuRepository menuRepository;
    private final DeliveryExecutiveRepository deliveryExecutiveRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository,
                        MenuRepository menuRepository, DeliveryExecutiveRepository deliveryExecutiveRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.menuRepository = menuRepository;
        this.deliveryExecutiveRepository = deliveryExecutiveRepository;
    }

    public List<Order> retrieveOrders() {
        return orderRepository.retrieveOrders();
    }

    public void createOrder(Order order) {
        orderRepository.createOrder(order);
    }

    public void updateOrder(Order order) {
        orderRepository.updateOrder(order);
    }

    public void deleteOrder(long id) {
        orderRepository.deleteOrder(id);
    }

    public Order findOrderById(long id) {
        orderRepository.findById(id);
        return null;
    }

    public Customer findCustomerById(long id) {
        return customerRepository.findById(id);
    }

    public Menu findMenuById(long id) {
        return menuRepository.findById(id);
    }

    public DeliveryExecutive findDeliveryExecutiveById(long id) {
        return deliveryExecutiveRepository.findById(id);
    }
}
