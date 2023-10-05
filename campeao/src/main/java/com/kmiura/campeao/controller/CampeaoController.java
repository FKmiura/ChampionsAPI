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
import com.kmiura.campeao.domain.Imagem;
import com.kmiura.campeao.enuns.ImagemEnum;
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
    public List<Imagem> buscarImagensCampeao(@PathVariable Long id) {
        List<Imagem> imagens = new ArrayList<>();
        Campeao campeao = new Campeao();
        try {
            campeao = campeaoDao.buscarId(id);
            if(campeao == null){
                throw new ResourceNotFoundException("No Campeao found.");
            }
            imagens = campeao.getImagem();
            if(imagens.isEmpty()){
                throw new ResourceNotFoundException("No Images found.");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new InternalServerErrorException("An internal server error occurred.");
        }
        return imagens;
    }

    @GetMapping("/{id}/images/{tipo}")
    public List<Imagem> buscarIdImagemTipo(@PathVariable Long id, ImagemEnum tipo) {
        List<Imagem> imagemsCampeao = new ArrayList<>();
        List<Imagem> imagemsTipo = new ArrayList<>();
        Campeao campeao = new Campeao();
        try {
            campeao = campeaoDao.buscarId(id);
            if(campeao == null){
                throw new ResourceNotFoundException("No Campeao found.");
            }
            imagemsCampeao = campeao.getImagem();
            if(imagemsCampeao.isEmpty()){
                throw new ResourceNotFoundException("No Images found.");
            }
            for(Imagem imagem : imagemsCampeao){
                if(imagem.getTipo() == tipo){
                    imagemsTipo.add(imagem);
                }
            }
            if(imagemsTipo.isEmpty()){
                throw new ResourceNotFoundException("No Type Images found.");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new InternalServerErrorException("An internal server error occurred.");
        }
        return imagemsTipo;
    }

    // Metodos POST

    @PostMapping
    public Campeao salvar(Campeao campeao) {
        try{
            campeaoDao.salvar(campeao);
            return campeao;
        }catch(RuntimeException e){
            e.printStackTrace();
            throw new InternalServerErrorException("An internal server error occurred.");
        }
    }

    // Metodos PUT

    @PutMapping("/{id}")
    public Campeao editar(@PathVariable Campeao campeao) {
        Campeao campeaoRetorno = new Campeao();
        try{
            campeaoRetorno = campeaoDao.merge(campeao);
        }catch(RuntimeException e){
            e.printStackTrace();
            throw new InternalServerErrorException("An internal server error occurred.");
        }
        return campeaoRetorno;
    }

    @PutMapping("/{idCampeao}/images/{idImagem}")
    public Campeao adicionarImagem(@PathVariable Long idCampeao, Long idImagem) {
        Campeao campeaoRetorno = new Campeao();
        try{
            
        }catch(RuntimeException e){
            e.printStackTrace();
            throw new InternalServerErrorException("An internal server error occurred.");
        }
        return campeaoRetorno;
    }

    // Metodos DELETE

    @DeleteMapping
    public void excluir() {

    }
}
