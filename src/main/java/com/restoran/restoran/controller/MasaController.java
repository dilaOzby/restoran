package com.restoran.restoran.controller;

import com.restoran.restoran.model.Masa;
import com.restoran.restoran.model.Siparis;
import com.restoran.restoran.service.MasaService;
import com.restoran.restoran.service.SiparisService;
import com.restoran.restoran.service.YemekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/masalar")
public class MasaController {

    @Autowired
    private MasaService masaService;

    @Autowired
    private SiparisService siparisService;

    @Autowired
    private YemekService yemekService;

    @GetMapping
    public String anaSayfa(Model model) {
        model.addAttribute("salonMasalari", masaService.salonMasalari());
        model.addAttribute("bahceMasalari", masaService.bahceMasalari());
        return "masa/anasayfa";
    }

    @GetMapping("/{id}")
    public String masaDetay(@PathVariable Long id, Model model) {
        Masa masa = masaService.masaBul(id);
        Siparis aktifSiparis = siparisService.aktifSiparisiGetir(masa);
        model.addAttribute("masa", masa);
        model.addAttribute("siparis", aktifSiparis);
        model.addAttribute("yemekler", yemekService.tumYemekleriGetir());
        return "masa/detay";
    }

    @PostMapping("/{id}/siparis-olustur")
    public String siparisOlustur(@PathVariable Long id) {
        Masa masa = masaService.masaBul(id);
        masa.setDolu(true);
        masaService.kaydet(masa);
        siparisService.siparisOlustur(masa);
        return "redirect:/masalar/" + id;
    }

    @PostMapping("/{id}/urun-ekle")
    public String urunEkle(@PathVariable Long id,
                           @RequestParam Long siparisId,
                           @RequestParam Long yemekId) {
        siparisService.urunEkle(siparisId, yemekId);
        return "redirect:/masalar/" + id;
    }

    // YENİ EKLEDİĞİMİZ ÜRÜN İPTAL METODU
    @PostMapping("/{id}/urun-sil")
    public String urunSil(@PathVariable Long id,
                          @RequestParam Long siparisId,
                          @RequestParam Long yemekId) {
        siparisService.urunSil(siparisId, yemekId);
        return "redirect:/masalar/" + id;
    }

    @PostMapping("/{id}/kapat")
    public String masaKapat(@PathVariable Long id,
                            @RequestParam Long siparisId) {
        Masa masa = masaService.masaBul(id);
        masa.setDolu(false);
        masaService.kaydet(masa);
        siparisService.siparisKapat(siparisId);
        return "redirect:/masalar";
    }

    @PostMapping("/urun-ekle-ajax")
    @ResponseBody
    public String urunEkleAjax(@RequestBody java.util.Map<String, Long> body) {
        siparisService.urunEkle(body.get("siparisId"), body.get("yemekId"));
        return "ok";
    }
}