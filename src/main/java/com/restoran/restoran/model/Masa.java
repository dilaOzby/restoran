package com.restoran.restoran.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "masalar")
public class Masa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String masaNo;
    private String bolge; // SALON veya BAHCE
    private boolean dolu;
}