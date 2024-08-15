package com.yumcourt.controller;

import com.yumcourt.model.Restaurant;
import com.yumcourt.model.Contact;
import com.yumcourt.model.Address;
import com.yumcourt.service.RestaurantService;
import com.yumcourt.repository.ContactRepository;
import com.yumcourt.repository.RestaurantRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/restaurants/*")
public class RestaurantController extends HttpServlet {
    private RestaurantService restaurantService;
    private ContactRepository contactRepository;
    private RestaurantRepository restaurantRepository;

    @Override
    public void init() throws ServletException {
        this.contactRepository = new ContactRepository();
        this.restaurantRepository = new RestaurantRepository(contactRepository);
        this.restaurantService = new RestaurantService(restaurantRepository, contactRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            List<Restaurant> restaurants = restaurantService.retrieveRestaurants();
            resp.setContentType("application/json");
            resp.getWriter().write(restaurants.toString());
        } else {
            try {
                long id = Long.parseLong(pathInfo.substring(1));
                Restaurant restaurant = restaurantService.getRestaurantById(id);
                if (restaurant != null) {
                    resp.setContentType("application/json");
                    resp.getWriter().write(restaurant.toJson());
                } else {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Restaurant not found");
                }
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid restaurant ID");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id = Long.parseLong(req.getParameter("id"));
            String name = req.getParameter("name");
            long contactId = Long.parseLong(req.getParameter("contactId"));
            long phone = Long.parseLong(req.getParameter("phone"));
            String addressName = req.getParameter("addressName");
            long flatNo = Long.parseLong(req.getParameter("flatNo"));
            String buildingName = req.getParameter("buildingName");
            String street = req.getParameter("street");
            String city = req.getParameter("city");
            String state = req.getParameter("state");
            long pinCode = Long.parseLong(req.getParameter("pinCode"));

            Address address = new Address(0, addressName, flatNo, buildingName, street, city, pinCode, state);
            Contact contact = new Contact(contactId, phone, address);
            contactRepository.createContact(contact);

            Restaurant restaurant = new Restaurant(id, name, contact, new ArrayList<>());
            restaurantService.createRestaurant();
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id = Long.parseLong(req.getParameter("id"));
            String name = req.getParameter("name");
            long contactId = Long.parseLong(req.getParameter("contactId"));
            long phone = Long.parseLong(req.getParameter("phone"));
            String addressName = req.getParameter("addressName");
            long flatNo = Long.parseLong(req.getParameter("flatNo"));
            String buildingName = req.getParameter("buildingName");
            String street = req.getParameter("street");
            String city = req.getParameter("city");
            String state = req.getParameter("state");
            long pinCode = Long.parseLong(req.getParameter("pinCode"));

            Address address = new Address(0, addressName, flatNo, buildingName, street, city, pinCode, state);
            Contact contact = new Contact(contactId, phone, address);
            contactRepository.updateContact(contact);

            Restaurant restaurant = new Restaurant(id, name, contact, new ArrayList<>());
            restaurantService.updateRestaurant(restaurant);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id = Long.parseLong(req.getPathInfo().substring(1));
            restaurantService.deleteRestaurant();
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid restaurant ID");
        }
    }
}
