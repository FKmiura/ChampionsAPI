package com.kmiura.campeao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kmiura.campeao.domain.Imagem;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {
    
}
