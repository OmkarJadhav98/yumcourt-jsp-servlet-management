package com.yumcourt.service;

import com.yumcourt.model.Contact;
import com.yumcourt.repository.ContactRepository;

import java.util.List;

public class ContactService {
    private static ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> retrieveContacts() {
        return contactRepository.retrieveContacts();
    }

    public void createContact(Contact contact) {
        contactRepository.createContact(contact);
        System.out.println("Contact created successfully.");
    }

    public void updateContact(Contact contact) {
        contactRepository.updateContact(contact);
        System.out.println("Contact updated successfully.");
    }

    public void deleteContact(long id) {
        contactRepository.deleteContact(id);
        System.out.println("Contact deleted successfully.");
    }

    public static Contact findContactById(long id) {
        return contactRepository.findById(id);
    }
}
