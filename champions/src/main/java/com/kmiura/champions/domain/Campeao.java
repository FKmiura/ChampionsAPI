package com.kmiura.champions.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "campeao")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Campeao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nome;
    @Column
    private String descricao;
    @Column
    private String funcao;
    @Column
    private String lane;
    @Column
    private String dificuldade;
    @Column
    private String passivaNome;
    @Column
    private String passivaDesc;
    @Column
    private String qNome;
    @Column
    private String qDesc;
    @Column
    private String wNome;
    @Column
    private String wDesc;
    @Column
    private String eNome;
    @Column
    private String eDesc;
    @Column
    private String rNome;
    @Column
    private String rDesc;
    @Column
    private String titulo;

    @JoinColumn
    @OneToMany(fetch = FetchType.EAGER)
    private List<Imagem> imagem;
    @JoinColumn
    @OneToMany(fetch = FetchType.EAGER)
    private List<Tier> tier;

    public void updateFrom(Campeao otherCampeao) {
        if (otherCampeao.getId() != null) {
            this.setId(otherCampeao.getId());
        }
        if (otherCampeao.getNome() != null) {
            this.setNome(otherCampeao.getNome());
        }
        if (otherCampeao.getDescricao() != null) {
            this.setDescricao(otherCampeao.getDescricao());
        }
        if (otherCampeao.getFuncao() != null) {
            this.setFuncao(otherCampeao.getFuncao());
        }
        if (otherCampeao.getLane() != null) {
            this.setLane(otherCampeao.getLane());
        }
        if (otherCampeao.getDificuldade() != null) {
            this.setDificuldade(otherCampeao.getDificuldade());
        }
        if (otherCampeao.getPassivaNome() != null) {
            this.setPassivaNome(otherCampeao.getPassivaNome());
        }
        if (otherCampeao.getPassivaDesc() != null) {
            this.setPassivaDesc(otherCampeao.getPassivaDesc());
        }
        if (otherCampeao.getQNome() != null) {
            this.setQNome(otherCampeao.getQNome());
        }
        if (otherCampeao.getQDesc() != null) {
            this.setQDesc(otherCampeao.getQDesc());
        }
        if (otherCampeao.getWNome() != null) {
            this.setWNome(otherCampeao.getWNome());
        }
        if (otherCampeao.getWDesc() != null) {
            this.setWDesc(otherCampeao.getWDesc());
        }
        if (otherCampeao.getENome() != null) {
            this.setENome(otherCampeao.getENome());
        }
        if (otherCampeao.getEDesc() != null) {
            this.setEDesc(otherCampeao.getEDesc());
        }
        if (otherCampeao.getRNome() != null) {
            this.setRNome(otherCampeao.getRNome());
        }
        if (otherCampeao.getRDesc() != null) {
            this.setRDesc(otherCampeao.getRDesc());
        }
        if (otherCampeao.getTitulo() != null) {
            this.setTitulo(otherCampeao.getTitulo());
        }
        
        if (otherCampeao.getImagem() != null) {
            for (Imagem newImagem : otherCampeao.getImagem()) {
                if (!this.getImagem().contains(newImagem)) {
                    this.getImagem().add(newImagem);
                }
            }
        }
        if (otherCampeao.getTier() != null) {
            for (Tier newTier : otherCampeao.getTier()) {
                if (!this.getTier().contains(newTier)) {
                    this.getTier().add(newTier);
                }
            }
        }

    }

    

}
