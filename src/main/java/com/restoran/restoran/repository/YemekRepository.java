package com.restoran.restoran.repository;

import com.restoran.restoran.model.Yemek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YemekRepository extends JpaRepository<Yemek, Long> {
}
