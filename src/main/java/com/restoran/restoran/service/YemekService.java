package com.restoran.restoran.service;

import com.restoran.restoran.model.Yemek;
import com.restoran.restoran.repository.YemekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YemekService {

    @Autowired
    private YemekRepository yemekRepository;

    public List<Yemek> tumYemekleriGetir() {
        return yemekRepository.findAll();
    }

    public void yemekKaydet(Yemek yemek) {
        yemekRepository.save(yemek);
    }

    public Yemek yemekBul(Long id) {
        return yemekRepository.findById(id).orElseThrow();
    }

    public void yemekSil(Long id) {
        yemekRepository.deleteById(id);
    }
}