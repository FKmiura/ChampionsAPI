package com.kmiura.campeao.dao;

import org.junit.jupiter.api.Test;

import com.kmiura.campeao.domain.Campeao;

public class CampeaoDaoTest {

    @Test
    public void salvar(){
        CampeaoDao dao = new CampeaoDao();
        Campeao campeao = new Campeao();
        campeao.setNome("Teste Dao");
        campeao.setDescricao("Esse é um teste do dao");
        dao.salvar(campeao);
    }
    
}
