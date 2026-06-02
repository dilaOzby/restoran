package com.restoran.restoran.repository;

import com.restoran.restoran.model.Masa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasaRepository extends JpaRepository<Masa, Long> {
    List<Masa> findByBolge(String bolge);
}