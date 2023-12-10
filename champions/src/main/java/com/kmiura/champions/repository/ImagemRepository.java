package com.kmiura.champions.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kmiura.champions.domain.Imagem;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {
    
}
