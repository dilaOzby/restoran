package com.restoran.restoran.controller;

import com.restoran.restoran.model.Yemek;
import com.restoran.restoran.service.YemekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/yemekler")
public class YemekController {

    @Autowired
    private YemekService yemekService;

    @GetMapping
    public String listele(Model model) {
        model.addAttribute("yemekler", yemekService.tumYemekleriGetir());
        return "yemek/liste";
    }

    @GetMapping("/ekle")
    public String ekleForm(Model model) {
        model.addAttribute("yemek", new Yemek());
        return "yemek/form";
    }

    @PostMapping("/ekle")
    public String ekleKaydet(@ModelAttribute Yemek yemek) {
        yemekService.yemekKaydet(yemek);
        return "redirect:/yemekler";
    }

    @GetMapping("/guncelle/{id}")
    public String guncelleForm(@PathVariable Long id, Model model) {
        model.addAttribute("yemek", yemekService.yemekBul(id));
        return "yemek/form";
    }

    @PostMapping("/guncelle/{id}")
    public String guncelleKaydet(@PathVariable Long id, @ModelAttribute Yemek yemek) {
        yemek.setId(id);
        yemekService.yemekKaydet(yemek);
        return "redirect:/yemekler";
    }

    @GetMapping("/sil/{id}")
    public String sil(@PathVariable Long id) {
        yemekService.yemekSil(id);
        return "redirect:/yemekler";
    }
}