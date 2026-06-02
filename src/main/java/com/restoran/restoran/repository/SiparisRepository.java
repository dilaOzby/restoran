package com.restoran.restoran.repository;

import com.restoran.restoran.model.Siparis;
import com.restoran.restoran.model.Masa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SiparisRepository extends JpaRepository<Siparis, Long> {
    Optional<Siparis> findByMasaAndAktifTrue(Masa masa);
}