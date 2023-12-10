package com.kmiura.champions.domain;

import com.kmiura.champions.enums.ImagemEnum;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "imagem")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
        if (otherImage.getId() != null) {
            this.setId(otherImage.getId());
        }
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
    
}
