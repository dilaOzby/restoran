package com.restoran.restoran.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "siparisler")
public class Siparis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Masa masa;

    private LocalDateTime olusturmaTarihi;
    private boolean aktif;
    private double toplam;

    @ManyToMany
    private List<Yemek> yemekler = new ArrayList<>();
}