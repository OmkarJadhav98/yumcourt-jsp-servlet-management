package com.yumcourt.service;

import com.yumcourt.model.DeliveryExecutive;
import com.yumcourt.repository.DeliveryExecutiveRepository;

import java.util.List;
import java.util.Scanner;

public class DeliveryExecutiveService {
    private static DeliveryExecutiveRepository deliveryExecutiveRepository;
    private final Scanner scanner;

    public DeliveryExecutiveService(DeliveryExecutiveRepository deliveryExecutiveRepository) {
        this.deliveryExecutiveRepository = deliveryExecutiveRepository;
        this.scanner = new Scanner(System.in);
    }

    public List<DeliveryExecutive> retrieveDeliveryExecutives() {
        return deliveryExecutiveRepository.retrieveDeliveryExecutives();
    }

    public void createDeliveryExecutive(DeliveryExecutive deliveryExecutive) {
        deliveryExecutiveRepository.createDeliveryExecutive(deliveryExecutive);
    }

    public void updateDeliveryExecutive(DeliveryExecutive deliveryExecutive) {
        deliveryExecutiveRepository.updateDeliveryExecutive(deliveryExecutive);
    }

    public void deleteDeliveryExecutive(long id) {
        deliveryExecutiveRepository.deleteDeliveryExecutive(id);
    }

    public static DeliveryExecutive findDeliveryExecutiveById(long id) {
        return deliveryExecutiveRepository.findById(id);
    }
}
