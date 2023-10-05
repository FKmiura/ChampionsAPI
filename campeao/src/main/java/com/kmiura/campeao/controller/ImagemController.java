package com.kmiura.campeao.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kmiura.campeao.domain.Imagem;
import com.kmiura.campeao.repository.ImagemRepository;

@RestController
@RequestMapping(value = "/images")
public class ImagemController {

    @Autowired
    ImagemRepository imagemRepository;

    @GetMapping
    public List<Imagem> listar() {
        return imagemRepository.findAll();
    }

    @PostMapping
    public Imagem salvar(@RequestParam MultipartFile imagemUpload) {
            Imagem imagem = new Imagem();
            imagem.setNome(imagemUpload.getOriginalFilename());
            try{
                imagem.setImagem(imagemUpload.getBytes());
                imagemRepository.save(imagem);
            }catch(IOException e){
                e.printStackTrace();
            }
            return imagem;
    }
}