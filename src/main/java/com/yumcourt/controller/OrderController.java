package com.yumcourt.controller;

import com.yumcourt.model.Order;
import com.yumcourt.model.Customer;
import com.yumcourt.model.Menu;
import com.yumcourt.model.DeliveryExecutive;
import com.yumcourt.repository.*;
import com.yumcourt.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/orders/*")
public class OrderController extends HttpServlet {
    private OrderService orderService;
    private CustomerService customerService;
    private MenuService menuService;
    private DeliveryExecutiveService deliveryExecutiveService;

    @Override
    public void init() throws ServletException {
        ContactRepository contactRepository = new ContactRepository();
        CustomerRepository customerRepository = new CustomerRepository(contactRepository);
        MenuRepository menuRepository = new MenuRepository();
        DeliveryExecutiveRepository deliveryExecutiveRepository = new DeliveryExecutiveRepository();

        this.orderService = new OrderService(
                new OrderRepository(customerRepository, menuRepository, deliveryExecutiveRepository),
                customerRepository,
                menuRepository,
                deliveryExecutiveRepository
        );
        this.customerService = new CustomerService(customerRepository,contactRepository);
        this.menuService = new MenuService(menuRepository);
        this.deliveryExecutiveService = new DeliveryExecutiveService(deliveryExecutiveRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            List<Order> orders = orderService.retrieveOrders();
            resp.setContentType("application/json");
            resp.getWriter().write(orders.toString());
        } else {
            try {
                long id = Long.parseLong(pathInfo.substring(1));
                Order order = orderService.findOrderById(id);
                if (order != null) {
                    resp.setContentType("application/json");
                    resp.getWriter().write(order.toJson());
                } else {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Order not found");
                }
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order ID");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long customerId = Long.parseLong(req.getParameter("customerId"));
            long menuId = Long.parseLong(req.getParameter("menuId"));
            long deliveryExecutiveId = Long.parseLong(req.getParameter("deliveryExecutiveId"));
            LocalDateTime timestamp = LocalDateTime.parse(req.getParameter("timestamp"));

            Customer customer = customerService.findCustomerById(customerId);
            Menu menu = menuService.findMenuById(menuId);
            DeliveryExecutive deliveryExecutive = deliveryExecutiveService.findDeliveryExecutiveById(deliveryExecutiveId);

            if (customer == null || menu == null || deliveryExecutive == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid customer, menu, or delivery executive ID");
                return;
            }

            Order order = new Order(0, customer, menu, deliveryExecutive, timestamp);
            orderService.createOrder(order);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (NumberFormatException | NullPointerException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id = Long.parseLong(req.getParameter("id"));
            long customerId = Long.parseLong(req.getParameter("customerId"));
            long menuId = Long.parseLong(req.getParameter("menuId"));
            long deliveryExecutiveId = Long.parseLong(req.getParameter("deliveryExecutiveId"));
            LocalDateTime timestamp = LocalDateTime.parse(req.getParameter("timestamp"));

            Customer customer = customerService.findCustomerById(customerId);
            Menu menu = menuService.findMenuById(menuId);
            DeliveryExecutive deliveryExecutive = deliveryExecutiveService.findDeliveryExecutiveById(deliveryExecutiveId);

            if (customer == null || menu == null || deliveryExecutive == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid customer, menu, or delivery executive ID");
                return;
            }

            Order order = new Order(id, customer, menu, deliveryExecutive, timestamp);
            orderService.updateOrder(order);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (NumberFormatException | NullPointerException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id = Long.parseLong(req.getPathInfo().substring(1));
            orderService.deleteOrder(id);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order ID");
        }
    }
}
