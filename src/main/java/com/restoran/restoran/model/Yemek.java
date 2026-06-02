package com.restoran.restoran.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "yemekler")
public class Yemek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ad;
    private String kategori;
    private double fiyat;
}