package com.restoran.restoran.service;

import com.restoran.restoran.model.Masa;
import com.restoran.restoran.repository.MasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasaService {

    @Autowired
    private MasaRepository masaRepository;

    public List<Masa> salonMasalari() {
        return masaRepository.findByBolge("SALON");
    }

    public List<Masa> bahceMasalari() {
        return masaRepository.findByBolge("BAHCE");
    }

    public Masa masaBul(Long id) {
        return masaRepository.findById(id).orElseThrow();
    }

    public void kaydet(Masa masa) {
        masaRepository.save(masa);
    }
}
