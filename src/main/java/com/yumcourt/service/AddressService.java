package com.yumcourt.service;

import com.yumcourt.model.Address;
import com.yumcourt.repository.AddressRepository;

import java.util.List;

public class AddressService {
    private static AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> retrieveAddresses() {
        return addressRepository.retrieveAddresses();
    }

    public void createAddress(Address address) {
        addressRepository.createAddress(address);
        System.out.println("Address created successfully.");
    }

    public void updateAddress(Address address) {
        addressRepository.updateAddress(address);
        System.out.println("Address updated successfully.");
    }

    public void deleteAddress(long id) {
        addressRepository.deleteAddress(id);
        System.out.println("Address deleted successfully.");
    }

    public static Address findAddressById(long id) {
        return addressRepository.findById(id);
    }
}
