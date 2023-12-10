package com.kmiura.champions.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kmiura.champions.domain.Campeao;

public interface CampeaoRepository extends JpaRepository<Campeao, Long> {
    
}
