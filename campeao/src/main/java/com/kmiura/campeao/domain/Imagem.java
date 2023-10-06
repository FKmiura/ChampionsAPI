package com.kmiura.campeao.domain;

import com.kmiura.campeao.enuns.ImagemEnum;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nome;
    @Column
    private ImagemEnum tipo;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "blob")
    private byte[] imagem;

    public void updateFrom(Imagem otherImage) {
        if (otherImage.getNome() != null) {
            this.setNome(otherImage.getNome());
        }
        if (otherImage.getTipo() != null) {
            this.setTipo(otherImage.getTipo());
        }
        if (otherImage.getImagem() != null) {
            this.setImagem(otherImage.getImagem());
        }
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public byte[] getImagem() {
        return imagem;  
    }
    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
    public ImagemEnum getTipo() {
        return tipo;
    }
    public void setTipo(ImagemEnum tipo) {
        this.tipo = tipo;
    }
    
}
