package com.yumcourt.controller;

import com.yumcourt.model.Contact;
import com.yumcourt.repository.ContactRepository;
import com.yumcourt.service.ContactService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/contact")
public class ContactController extends HttpServlet {

    private ContactService contactService;

    @Override
    public void init() throws ServletException {
        // Initialize the ContactService with a new instance of ContactRepository
        contactService = new ContactService(new ContactRepository());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "view":
                viewContact(request, response);
                break;
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteContact(request, response);
                break;
            default:
                listContacts(request, response);
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
                createContact(request, response);
                break;
            case "edit":
                updateContact(request, response);
                break;
            default:
                listContacts(request, response);
                break;
        }
    }

    private void listContacts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Contact> contacts = contactService.retrieveContacts();
        request.setAttribute("contacts", contacts);
        request.getRequestDispatcher("/contactList.jsp").forward(request, response);
    }

    private void viewContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        Contact contact = contactService.findContactById(id);
        request.setAttribute("contact", contact);
        request.getRequestDispatcher("/contactView.jsp").forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/contactForm.jsp").forward(request, response);
    }

    private void createContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        long phone = Long.parseLong(request.getParameter("phone"));
        long flatNo = Long.parseLong(request.getParameter("flatNo"));
        String buildingName = request.getParameter("buildingName");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        long pinCode = Long.parseLong(request.getParameter("pinCode"));
        String state = request.getParameter("state");

        Contact contact = new Contact(id, phone, new com.yumcourt.model.Address(id, "", flatNo, buildingName, street, city, pinCode, state));
        contactService.createContact(contact);
        response.sendRedirect("contact?action=list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        Contact existingContact = contactService.findContactById(id);
        request.setAttribute("contact", existingContact);
        request.getRequestDispatcher("/contactForm.jsp").forward(request, response);
    }

    private void updateContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        long phone = Long.parseLong(request.getParameter("phone"));
        long flatNo = Long.parseLong(request.getParameter("flatNo"));
        String buildingName = request.getParameter("buildingName");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        long pinCode = Long.parseLong(request.getParameter("pinCode"));
        String state = request.getParameter("state");

        Contact contact = new Contact(id, phone, new com.yumcourt.model.Address(id, "", flatNo, buildingName, street, city, pinCode, state));
        contactService.updateContact(contact);
        response.sendRedirect("contact?action=list");
    }

    private void deleteContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        contactService.deleteContact(id);
        response.sendRedirect("contact?action=list");
    }
}
