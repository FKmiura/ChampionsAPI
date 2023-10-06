package com.kmiura.campeao.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kmiura.campeao.dao.ImagemDao;
import com.kmiura.campeao.domain.Imagem;
import com.kmiura.campeao.enuns.ImagemEnum;
import com.kmiura.campeao.exception.InternalServerErrorException;
import com.kmiura.campeao.exception.ResourceNotFoundException;
import com.kmiura.campeao.repository.ImagemRepository;

@RestController
@RequestMapping(value = "/images")
public class ImagemController {

    @Autowired
    ImagemRepository imagemRepository;

    ImagemDao imagemDao = new ImagemDao();

    // Metodos GET

    @GetMapping
    public List<Imagem> listar() {
        List<Imagem> imagens = new ArrayList<>();
        try {
            imagens = imagemDao.listar();
            if(imagens.isEmpty()){
                throw new ResourceNotFoundException("No Image Found");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new InternalServerErrorException("An internal server error occurred.");
        }
        return imagens;
    }

    @GetMapping("/{id}")
    public Imagem buscarId(@PathVariable Long id) {
        Imagem imagem = new Imagem();
        try {
            imagem = imagemDao.buscarId(id);
            if (imagem == null) {
                throw new ResourceNotFoundException("No Imagem found.");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new InternalServerErrorException("An internal server error occurred.");
        }
        return imagem;
    }

    // Metodos POST

    @PostMapping
    public Imagem salvar(@RequestParam MultipartFile imagemUpload) {
        Imagem imagem = new Imagem();
        imagem.setNome(imagemUpload.getOriginalFilename());
        try {
            imagem.setImagem(imagemUpload.getBytes());
            imagemRepository.save(imagem);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagem;
    }

    @PostMapping("/{tipo}")
    public Imagem salvarImagemTipo(@RequestParam MultipartFile imagemUpload, @PathVariable ImagemEnum tipo) {
        Imagem imagem = new Imagem();
        imagem.setNome(imagemUpload.getOriginalFilename());
        imagem.setTipo(tipo);
        try {
            imagem.setImagem(imagemUpload.getBytes());
            imagemRepository.save(imagem);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagem;
    }

    // Metodos PUT

    @PutMapping("/{id}")
    public Imagem editar(@PathVariable Long id, @RequestBody Imagem imagem) {
        Imagem imagemRetorno = new Imagem();
        try{
            Imagem imagemEditar = new Imagem();
            imagemEditar = imagemDao.buscarId(id);

            if(imagemEditar == null){
                throw new ResourceNotFoundException("No Imagem found.");
            }
            
            imagemEditar.updateFrom(imagem);

            imagemRetorno = imagemDao.editar(imagemEditar);
            
        }catch(RuntimeException e){
            e.printStackTrace();
            throw new InternalServerErrorException("An internal server error occurred.");
        }
        return imagemRetorno;
    }

    // Metodos DELETE

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        try {
            Imagem imagem = imagemDao.buscarId(id);
            if(imagem != null){
                imagemDao.excluir(imagem);
            }else{
                throw new ResourceNotFoundException("No Imagem found.");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new InternalServerErrorException("An internal server error occurred.");
        }
    }

}