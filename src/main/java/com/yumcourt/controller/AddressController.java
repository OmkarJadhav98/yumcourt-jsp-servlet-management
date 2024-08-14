package com.yumcourt.controller;

import com.yumcourt.model.Address;
import com.yumcourt.repository.AddressRepository;
import com.yumcourt.service.AddressService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/address")
public class AddressController extends HttpServlet {

    private AddressService addressService;

    @Override
    public void init() throws ServletException {
        addressService = new AddressService(new AddressRepository()); // Initialize the AddressService
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            long id = Long.parseLong(idParam);
            Address address = addressService.findAddressById(id);
            if (address != null) {
                response.setContentType("application/json");
                response.getWriter().write(address.toJson().toString());
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\": \"Address not found\"}");
            }
        } else {
            List<Address> addresses = addressService.retrieveAddresses();
            response.setContentType("application/json");
            response.getWriter().write(addresses.toString()); // Serialize the list to JSON
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        long flatNo = Long.parseLong(request.getParameter("flatNo"));
        String buildingName = request.getParameter("buildingName");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        long pinCode = Long.parseLong(request.getParameter("pinCode"));
        String state = request.getParameter("state");

        Address address = new Address(id, name, flatNo, buildingName, street, city, pinCode, state);
        addressService.createAddress(address);

        response.setContentType("application/json");
        response.getWriter().write("{\"message\": \"Address created successfully\"}");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        long flatNo = Long.parseLong(request.getParameter("flatNo"));
        String buildingName = request.getParameter("buildingName");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        long pinCode = Long.parseLong(request.getParameter("pinCode"));
        String state = request.getParameter("state");

        Address address = new Address(id, name, flatNo, buildingName, street, city, pinCode, state);
        addressService.updateAddress(address);

        response.setContentType("application/json");
        response.getWriter().write("{\"message\": \"Address updated successfully\"}");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        addressService.deleteAddress(id);

        response.setContentType("application/json");
        response.getWriter().write("{\"message\": \"Address deleted successfully\"}");
    }
}
