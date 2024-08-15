package com.yumcourt.controller;

import com.yumcourt.model.Customer;
import com.yumcourt.model.Kart;
import com.yumcourt.model.Restaurant;
import com.yumcourt.service.KartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/kart")
public class KartController extends HttpServlet {

    private KartService kartService;

    @Override
    public void init() throws ServletException {
        kartService = new KartService(new com.yumcourt.repository.KartRepository());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            long id = Long.parseLong(idParam);
            Kart kart = kartService.findKartById(id);
            if (kart != null) {
                response.setContentType("application/json");
                response.getWriter().write(kart.toString());
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\": \"Kart not found\"}");
            }
        } else {
            List<Kart> karts = kartService.retrieveKarts();
            response.setContentType("application/json");
            response.getWriter().write(karts.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        double finalPrice = Double.parseDouble(request.getParameter("finalPrice"));
        long customerId = Long.parseLong(request.getParameter("customerId"));
        long restaurantId = Long.parseLong(request.getParameter("restaurantId"));

        // Assuming Customer and Restaurant entities are retrieved from their respective repositories
        Customer customer = new com.yumcourt.repository.CustomerRepository(new com.yumcourt.repository.ContactRepository()).findById(customerId);
        Restaurant restaurant = new com.yumcourt.repository.RestaurantRepository(new com.yumcourt.repository.ContactRepository()).findById(restaurantId);

        if (customer != null && restaurant != null) {
            Kart kart = new Kart(id, finalPrice, customer, restaurant);
            kartService.createKart(kart);
            response.setContentType("application/json");
            response.getWriter().write("{\"message\": \"Kart created successfully\"}");
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Invalid Customer or Restaurant ID\"}");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        double finalPrice = Double.parseDouble(request.getParameter("finalPrice"));
        long customerId = Long.parseLong(request.getParameter("customerId"));
        long restaurantId = Long.parseLong(request.getParameter("restaurantId"));

        // Assuming Customer and Restaurant entities are retrieved from their respective repositories
        Customer customer = new com.yumcourt.repository.CustomerRepository(new com.yumcourt.repository.ContactRepository()).findById(customerId);
        Restaurant restaurant = new com.yumcourt.repository.RestaurantRepository(new com.yumcourt.repository.ContactRepository()).findById(restaurantId);

        if (customer != null && restaurant != null) {
            Kart kart = new Kart(id, finalPrice, customer, restaurant);
            kartService.updateKart(kart);
            response.setContentType("application/json");
            response.getWriter().write("{\"message\": \"Kart updated successfully\"}");
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Invalid Customer or Restaurant ID\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        kartService.deleteKart(id);
        response.setContentType("application/json");
        response.getWriter().write("{\"message\": \"Kart deleted successfully\"}");
    }
}
