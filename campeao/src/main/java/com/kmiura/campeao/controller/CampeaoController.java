package com.kmiura.campeao.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kmiura.campeao.dao.CampeaoDao;
import com.kmiura.campeao.domain.Campeao;
import com.kmiura.campeao.exception.InternalServerErrorException;
import com.kmiura.campeao.exception.ResourceNotFoundException;

@RestController
@RequestMapping(value = "/champions")
public class CampeaoController {

    private CampeaoDao campeaoDao = new CampeaoDao();

    // Metodos GET

    @GetMapping
    public List<Campeao> listar() {
        List<Campeao> campeaoes = new ArrayList<>();
        try {
            campeaoes = campeaoDao.listar();
            if (campeaoes.isEmpty()) {
                throw new ResourceNotFoundException("No Campeao found.");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new InternalServerErrorException("An internal server error occurred.");
        }
        return campeaoes;
    }

    @GetMapping("/{id}")
    public Campeao buscarId(@PathVariable Long id) {
        Campeao campeao = new Campeao();
        try {
            campeao = campeaoDao.buscarId(id);
            if (campeao == null) {
                throw new ResourceNotFoundException("No Campeao found.");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new InternalServerErrorException("An internal server error occurred.");
        }
        return campeao;
    }

    @GetMapping("/{id}/images")
    public void buscarIdImagens() {
        try {

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/{id}/images/{tipo}")
    public void buscarIdImagemTipo() {
        try {

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    // Metodos POST

    @PostMapping
    public void salvar() {

    }

    // Metodos PUT

    @PutMapping
    public void editar() {

    }

    @PutMapping
    public void adicionarImagem() {

    }

    // Metodos DELETE

    @DeleteMapping
    public void excluir() {

    }
}
