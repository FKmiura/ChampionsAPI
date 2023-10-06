package com.kmiura.campeao.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
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

    public Campeao() {
    }

    public Campeao(Long id, String nome, String descricao, String funcao, String lane, String dificuldade,
            String passivaNome, String passivaDesc, String qNome, String qDesc, String wNome, String wDesc,
            String eNome, String eDesc, String rNome, String rDesc, String titulo, List<Imagem> imagem,
            List<Tier> tier) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.funcao = funcao;
        this.lane = lane;
        this.dificuldade = dificuldade;
        this.passivaNome = passivaNome;
        this.passivaDesc = passivaDesc;
        this.qNome = qNome;
        this.qDesc = qDesc;
        this.wNome = wNome;
        this.wDesc = wDesc;
        this.eNome = eNome;
        this.eDesc = eDesc;
        this.rNome = rNome;
        this.rDesc = rDesc;
        this.titulo = titulo;
        this.imagem = imagem;
        this.tier = tier;
    }

    public void updateFrom(Campeao otherCampeao) {
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
        if (otherCampeao.getqNome() != null) {
            this.setqNome(otherCampeao.getqNome());
        }
        if (otherCampeao.getqDesc() != null) {
            this.setqDesc(otherCampeao.getqDesc());
        }
        if (otherCampeao.getwNome() != null) {
            this.setwNome(otherCampeao.getwNome());
        }
        if (otherCampeao.getwDesc() != null) {
            this.setwDesc(otherCampeao.getwDesc());
        }
        if (otherCampeao.geteNome() != null) {
            this.seteNome(otherCampeao.geteNome());
        }
        if (otherCampeao.geteDesc() != null) {
            this.seteDesc(otherCampeao.geteDesc());
        }
        if (otherCampeao.getrNome() != null) {
            this.setrNome(otherCampeao.getrNome());
        }
        if (otherCampeao.getrDesc() != null) {
            this.setrDesc(otherCampeao.getrDesc());
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public String getPassivaNome() {
        return passivaNome;
    }

    public void setPassivaNome(String passivaNome) {
        this.passivaNome = passivaNome;
    }

    public String getPassivaDesc() {
        return passivaDesc;
    }

    public void setPassivaDesc(String passivaDesc) {
        this.passivaDesc = passivaDesc;
    }

    public String getqNome() {
        return qNome;
    }

    public void setqNome(String qNome) {
        this.qNome = qNome;
    }

    public String getqDesc() {
        return qDesc;
    }

    public void setqDesc(String qDesc) {
        this.qDesc = qDesc;
    }

    public String getwNome() {
        return wNome;
    }

    public void setwNome(String wNome) {
        this.wNome = wNome;
    }

    public String getwDesc() {
        return wDesc;
    }

    public void setwDesc(String wDesc) {
        this.wDesc = wDesc;
    }

    public String geteNome() {
        return eNome;
    }

    public void seteNome(String eNome) {
        this.eNome = eNome;
    }

    public String geteDesc() {
        return eDesc;
    }

    public void seteDesc(String eDesc) {
        this.eDesc = eDesc;
    }

    public String getrNome() {
        return rNome;
    }

    public void setrNome(String rNome) {
        this.rNome = rNome;
    }

    public String getrDesc() {
        return rDesc;
    }

    public void setrDesc(String rDesc) {
        this.rDesc = rDesc;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Imagem> getImagem() {
        return imagem;
    }

    public void setImagem(List<Imagem> imagem) {
        this.imagem = imagem;
    }

    public List<Tier> getTier() {
        return tier;
    }

    public void setTier(List<Tier> tier) {
        this.tier = tier;
    }

}
