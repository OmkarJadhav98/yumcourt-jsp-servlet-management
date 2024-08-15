package com.yumcourt.controller;

import com.yumcourt.model.DeliveryExecutive;
import com.yumcourt.service.DeliveryExecutiveService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/delivery-executive")
public class DeliveryExecutiveController extends HttpServlet {

    private DeliveryExecutiveService deliveryExecutiveService;

    @Override
    public void init() throws ServletException {
        deliveryExecutiveService = new DeliveryExecutiveService(new com.yumcourt.repository.DeliveryExecutiveRepository());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            long id = Long.parseLong(idParam);
            DeliveryExecutive deliveryExecutive = deliveryExecutiveService.findDeliveryExecutiveById(id);
            if (deliveryExecutive != null) {
                response.setContentType("application/json");
                response.getWriter().write(deliveryExecutive.toString());
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\": \"Delivery Executive not found\"}");
            }
        } else {
            List<DeliveryExecutive> deliveryExecutives = deliveryExecutiveService.retrieveDeliveryExecutives();
            response.setContentType("application/json");
            response.getWriter().write(deliveryExecutives.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        long phoneNo = Long.parseLong(request.getParameter("phoneNo"));
        boolean isAvailable = Boolean.parseBoolean(request.getParameter("isAvailable"));

        DeliveryExecutive deliveryExecutive = new DeliveryExecutive(id, name, phoneNo, isAvailable);
        deliveryExecutiveService.createDeliveryExecutive(deliveryExecutive);

        response.setContentType("application/json");
        response.getWriter().write("{\"message\": \"Delivery Executive created successfully\"}");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        long phoneNo = Long.parseLong(request.getParameter("phoneNo"));
        boolean isAvailable = Boolean.parseBoolean(request.getParameter("isAvailable"));

        DeliveryExecutive deliveryExecutive = new DeliveryExecutive(id, name, phoneNo, isAvailable);
        deliveryExecutiveService.updateDeliveryExecutive(deliveryExecutive);

        response.setContentType("application/json");
        response.getWriter().write("{\"message\": \"Delivery Executive updated successfully\"}");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        deliveryExecutiveService.deleteDeliveryExecutive(id);

        response.setContentType("application/json");
        response.getWriter().write("{\"message\": \"Delivery Executive deleted successfully\"}");
    }
}
