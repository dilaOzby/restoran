package com.restoran.restoran.service;

import com.restoran.restoran.model.Masa;
import com.restoran.restoran.model.Siparis;
import com.restoran.restoran.model.Yemek;
import com.restoran.restoran.repository.SiparisRepository;
import com.restoran.restoran.repository.YemekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SiparisService {

    @Autowired
    private SiparisRepository siparisRepository;

    @Autowired
    private YemekRepository yemekRepository;

    public Siparis aktifSiparisiGetir(Masa masa) {
        // Senin repository'ndeki findByMasaAndAktifTrue metodunu kullanıyoruz
        return siparisRepository.findByMasaAndAktifTrue(masa).orElse(null);
    }

    public void siparisOlustur(Masa masa) {
        Siparis yeniSiparis = new Siparis();
        yeniSiparis.setMasa(masa);
        yeniSiparis.setAktif(true); // Sipariş ilk açıldığında aktiftir
        yeniSiparis.setToplam(0.0);
        yeniSiparis.setYemekler(new ArrayList<>());
        siparisRepository.save(yeniSiparis);
    }

    public void urunEkle(Long siparisId, Long yemekId) {
        Siparis siparis = siparisRepository.findById(siparisId).orElse(null);
        Yemek yemek = yemekRepository.findById(yemekId).orElse(null);

        if (siparis != null && yemek != null) {
            siparis.getYemekler().add(yemek);
            // Toplam tutarı güncelle
            siparis.setToplam(siparis.getToplam() + yemek.getFiyat());
            siparisRepository.save(siparis);
        }
    }

    public void urunSil(Long siparisId, Long yemekId) {
        Siparis siparis = siparisRepository.findById(siparisId).orElse(null);
        Yemek yemek = yemekRepository.findById(yemekId).orElse(null);

        if (siparis != null && yemek != null) {
            // Adisyondaki yemeklerden sadece İLK eşleşeni siler
            siparis.getYemekler().remove(yemek);

            // Kalan yemeklerin fiyatına göre toplamı yeniden hesapla
            double yeniToplam = siparis.getYemekler().stream().mapToDouble(Yemek::getFiyat).sum();
            siparis.setToplam(yeniToplam);

            siparisRepository.save(siparis);
        }
    }

    public void siparisKapat(Long siparisId) {
        Siparis siparis = siparisRepository.findById(siparisId).orElse(null);
        if (siparis != null) {
            siparis.setAktif(false); // Hesap kapatılınca siparişi pasife (false) çekiyoruz
            siparisRepository.save(siparis);
        }
    }
}