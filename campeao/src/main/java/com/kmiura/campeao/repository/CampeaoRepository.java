package com.kmiura.campeao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kmiura.campeao.domain.Campeao;

public interface CampeaoRepository extends JpaRepository<Campeao, Long> {
    
}
