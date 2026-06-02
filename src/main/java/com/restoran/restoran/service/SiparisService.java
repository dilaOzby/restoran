package com.restoran.restoran.service;

import com.restoran.restoran.model.Masa;
import com.restoran.restoran.model.Siparis;
import com.restoran.restoran.model.Yemek;
import com.restoran.restoran.repository.SiparisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SiparisService {

    @Autowired
    private SiparisRepository siparisRepository;

    @Autowired
    private YemekService yemekService;

    public Siparis aktifSiparisiGetir(Masa masa) {
        return siparisRepository.findByMasaAndAktifTrue(masa).orElse(null);
    }

    public Siparis siparisOlustur(Masa masa) {
        Siparis siparis = new Siparis();
        siparis.setMasa(masa);
        siparis.setOlusturmaTarihi(LocalDateTime.now());
        siparis.setAktif(true);
        siparis.setToplam(0);
        return siparisRepository.save(siparis);
    }

    public Siparis urunEkle(Long siparisId, Long yemekId) {
        Siparis siparis = siparisRepository.findById(siparisId).orElseThrow();
        Yemek yemek = yemekService.yemekBul(yemekId);
        siparis.getYemekler().add(yemek);
        siparis.setToplam(siparis.getToplam() + yemek.getFiyat());
        return siparisRepository.save(siparis);
    }

    public Siparis siparisiGetir(Long id) {
        return siparisRepository.findById(id).orElseThrow();
    }

    public List<Siparis> tumSiparisler() {
        return siparisRepository.findAll();
    }

    public void siparisKapat(Long siparisId) {
        Siparis siparis = siparisRepository.findById(siparisId).orElseThrow();
        siparis.setAktif(false);
        siparisRepository.save(siparis);
    }
}