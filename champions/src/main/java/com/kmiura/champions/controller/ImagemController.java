package com.kmiura.champions.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.kmiura.champions.domain.Imagem;
import com.kmiura.champions.enums.ImagemEnum;
import com.kmiura.champions.exception.ApiError;
import com.kmiura.champions.repository.ImagemRepository;

@RestController
@RequestMapping(value = "/images")
public class ImagemController {

    @Autowired
    private ImagemRepository imagemRepository;

    // Metodos GET

    @GetMapping
    public ResponseEntity<?> listar() {
        List<Imagem> imagens = new ArrayList<>();
        try {
            imagens = imagemRepository.findAll();
            if(imagens.isEmpty()){
                return new ResponseEntity<>(new ApiError(HttpStatus.NO_CONTENT.value(), "No image found"), HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(imagens, HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred on the server, try again later"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarId(@PathVariable Long id) {
        Imagem imagem = new Imagem();
        try {
            Optional<Imagem> imagemOptional = imagemRepository.findById(id);
            if(!imagemOptional.isPresent()) {
                return new ResponseEntity<>(new ApiError(HttpStatus.NO_CONTENT.value(), "No image found"), HttpStatus.NO_CONTENT);
            }
            imagem = imagemOptional.get();

            return new ResponseEntity<>(imagem, HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred on the server, try again later"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Metodos POST

    @PostMapping
    public ResponseEntity<?> salvar(@RequestParam MultipartFile imagemUpload) {
        Imagem imagem = new Imagem();
        imagem.setNome(imagemUpload.getOriginalFilename());
        try {
            imagem.setImagem(imagemUpload.getBytes());
            imagemRepository.save(imagem);
            return new ResponseEntity<>(imagem, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred on the server, try again later"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{tipo}")
    public ResponseEntity<?> salvarImagemTipo(@RequestParam MultipartFile imagemUpload, @PathVariable ImagemEnum tipo) {
        Imagem imagem = new Imagem();
        imagem.setNome(imagemUpload.getOriginalFilename());
        imagem.setTipo(tipo);
        try {
            imagem.setImagem(imagemUpload.getBytes());
            imagemRepository.save(imagem);
            return new ResponseEntity<>(imagem, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred on the server, try again later"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Metodos PUT

    @PutMapping("editar/tudo/{id}")
    public ResponseEntity<?> editarTudo(@PathVariable Long id, @RequestBody Imagem imagem) {
        Imagem imagemRetorno = new Imagem();
        try{
            if(!imagemRepository.existsById(id)) {
                return new ResponseEntity<>(new ApiError(HttpStatus.NO_CONTENT.value(), "No image found"), HttpStatus.NO_CONTENT);
            }
            imagem.setId(id);
            imagemRetorno = imagemRepository.save(imagem);
            return new ResponseEntity<>(imagemRetorno, HttpStatus.OK);
        }catch(RuntimeException e){
            e.printStackTrace();
            return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred on the server, try again later"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("editar/parcial/{id}")
    public ResponseEntity<?> editarParcial(@PathVariable Long id, @RequestBody Imagem imagem) {
        Imagem imagemRetorno = new Imagem();
        try{
            Optional<Imagem> imagemOptional = imagemRepository.findById(id);
            if(!imagemOptional.isPresent()) {
                return new ResponseEntity<>(new ApiError(HttpStatus.NO_CONTENT.value(), "No image found"), HttpStatus.NO_CONTENT);
            }
            Imagem imagemEditar = new Imagem();
            imagemEditar = imagemOptional.get();

            imagemEditar.updateFrom(imagem);

            imagemRetorno = imagemRepository.save(imagemEditar);
            return new ResponseEntity<>(imagemRetorno, HttpStatus.OK);
        }catch(RuntimeException e){
            e.printStackTrace();
            return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred on the server, try again later"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Metodos DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        try {
            Optional<Imagem> imagemOptional = imagemRepository.findById(id);
            if(!imagemOptional.isPresent()) {
                return new ResponseEntity<>(new ApiError(HttpStatus.NO_CONTENT.value(), "No image found"), HttpStatus.NO_CONTENT);
            }
            Imagem imagem = imagemOptional.get();

            imagemRepository.delete(imagem);
            return new ResponseEntity<>("Image deleted successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred on the server, try again later"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}