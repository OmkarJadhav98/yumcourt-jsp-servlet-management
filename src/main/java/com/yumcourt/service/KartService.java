package com.yumcourt.service;

import com.yumcourt.model.Kart;
import com.yumcourt.repository.KartRepository;

import java.util.List;

public class KartService {
    private final KartRepository kartRepository;

    public KartService(KartRepository kartRepository) {
        this.kartRepository = kartRepository;
    }

    public List<Kart> retrieveKarts() {
        return kartRepository.retrieveKarts();
    }

    public void createKart(Kart kart) {
        kartRepository.createKart(kart);
    }

    public void updateKart(Kart kart) {
        kartRepository.updateKart(kart);
    }

    public void deleteKart(long id) {
        kartRepository.deleteKart(id);
    }

    public Kart findKartById(long id) {
        return kartRepository.findById(id);
    }
}
