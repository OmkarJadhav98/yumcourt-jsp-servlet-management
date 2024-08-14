package com.yumcourt.controller;

import com.yumcourt.model.Contact;
import com.yumcourt.model.Customer;
import com.yumcourt.repository.ContactRepository;
import com.yumcourt.repository.CustomerRepository;
import com.yumcourt.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/customer")
public class CustomerController extends HttpServlet {

    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        ContactRepository contactRepository = new ContactRepository();
        CustomerRepository customerRepository = new CustomerRepository(contactRepository);
        customerService = new CustomerService(customerRepository, contactRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "view":
                viewCustomer(request, response);
                break;
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteCustomer(request, response);
                break;
            default:
                listCustomers(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "create":
                createCustomer(request, response);
                break;
            case "edit":
                updateCustomer(request, response);
                break;
            default:
                listCustomers(request, response);
                break;
        }
    }

    private void listCustomers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customers = customerService.retrieveCustomers();
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/customerList.jsp").forward(request, response);
    }

    private void viewCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        Customer customer = customerService.findCustomerById(id);
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("/customerView.jsp").forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/customerForm.jsp").forward(request, response);
    }

    private void createCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        long contactId = Long.parseLong(request.getParameter("contactId"));

        Contact contact = customerService.findCustomerById(contactId).getContact();
        Customer customer = new Customer(id, name, email, password, contact);
        customerService.createCustomer();
        response.sendRedirect("customer?action=list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        Customer existingCustomer = customerService.findCustomerById(id);
        request.setAttribute("customer", existingCustomer);
        request.getRequestDispatcher("/customerForm.jsp").forward(request, response);
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        long contactId = Long.parseLong(request.getParameter("contactId"));

        Contact contact = customerService.findCustomerById(contactId).getContact();
        Customer customer = new Customer(id, name, email, password, contact);
        customerService.updateCustomer();
        response.sendRedirect("customer?action=list");
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        customerService.deleteCustomer();
        response.sendRedirect("customer?action=list");
    }
}
