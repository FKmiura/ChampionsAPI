package com.kmiura.campeao.dao;

import org.junit.jupiter.api.Test;

import com.kmiura.campeao.domain.Campeao;
import com.kmiura.campeao.exception.ResourceNotFoundException;

public class CampeaoDaoTest {

    @Test
    public void buscar() {
        try {
            CampeaoDao dao = new CampeaoDao();
            Campeao campeao = new Campeao();
            campeao = dao.buscarId(9L);
            if (campeao == null) {
                throw new ResourceNotFoundException("No Campeao found.");
            }
            System.out.println(campeao.getNome());
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

}
