package com.kmiura.champions.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.kmiura.champions.domain.Campeao;
import com.kmiura.champions.domain.Imagem;
import com.kmiura.champions.enums.ImagemEnum;
import com.kmiura.champions.exception.ApiError;
import com.kmiura.champions.repository.CampeaoRepository;
import com.kmiura.champions.repository.ImagemRepository;

@RestController
@RequestMapping(value = "/champions")
public class CampeaoController {

    @Autowired
    private CampeaoRepository campeaoRepository;
    @Autowired
    private ImagemRepository imagemRepository;

    // Metodos GET

    @GetMapping
    public ResponseEntity<?> listar() {
        List<Campeao> campeaoes = new ArrayList<>();
        try {
            campeaoes = campeaoRepository.findAll();
            if(campeaoes.isEmpty()){
                return new ResponseEntity<>(new ApiError(HttpStatus.NO_CONTENT.value(), "No champion found"), HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(campeaoes, HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred on the server, try again later"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
       
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarId(@PathVariable Long id) {
        Campeao campeao = new Campeao();
        try {
            Optional<Campeao> optionalCampeao = campeaoRepository.findById(id);
            if (!optionalCampeao.isPresent()) {
                return new ResponseEntity<>(new ApiError(HttpStatus.NO_CONTENT.value(), "No champion found"), HttpStatus.NO_CONTENT);
            }
            campeao = optionalCampeao.get();
            return new ResponseEntity<>(campeao, HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred on the server, try again later"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @GetMapping("/{id}/images")
    public ResponseEntity<?> buscarImagensCampeao(@PathVariable Long id) {
        List<Imagem> imagens = new ArrayList<>();
        Campeao campeao = new Campeao();
        try {
            Optional<Campeao> campeaoOptional = campeaoRepository.findById(id);
            if(!campeaoOptional.isPresent()){
                return new ResponseEntity<>(new ApiError(HttpStatus.NO_CONTENT.value(), "No champion found"), HttpStatus.NO_CONTENT);
            }
            campeao = campeaoOptional.get();

            imagens = campeao.getImagem();
            if(imagens.isEmpty()){
                return new ResponseEntity<>(new ApiError(HttpStatus.NO_CONTENT.value(), "No image found"), HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(imagens, HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred on the server, try again later"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/images/{tipo}")
    public ResponseEntity<?> buscarIdImagemTipo(@PathVariable Long id,@PathVariable ImagemEnum tipo) {
        List<Imagem> imagemsCampeao = new ArrayList<>();
        List<Imagem> imagemsTipo = new ArrayList<>();
        Campeao campeao = new Campeao();
        try {
            Optional<Campeao> campeaoOptional = campeaoRepository.findById(id);
            if(!campeaoOptional.isPresent()){
                return new ResponseEntity<>(new ApiError(HttpStatus.NO_CONTENT.value(), "No champion found"), HttpStatus.NO_CONTENT);
            }
            campeao = campeaoOptional.get();

            imagemsCampeao = campeao.getImagem();
            if(imagemsCampeao.isEmpty()){
                return new ResponseEntity<>(new ApiError(HttpStatus.NO_CONTENT.value(), "No image found on the champion"), HttpStatus.NO_CONTENT);
            }
            for(Imagem imagem : imagemsCampeao){
                if(imagem.getTipo() == tipo){
                    imagemsTipo.add(imagem);
                }
            }
            if(imagemsTipo.isEmpty()){
                return new ResponseEntity<>(new ApiError(HttpStatus.NO_CONTENT.value(), "No image found for the type"), HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(imagemsTipo, HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred on the server, try again later"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Metodos POST

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Campeao campeao) {
        try{
            Campeao campeaoSalvo = campeaoRepository.save(campeao);
            return new ResponseEntity<>(campeaoSalvo, HttpStatus.CREATED);
        }catch(RuntimeException e){
            e.printStackTrace();
            return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred on the server, try again later"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Metodos PUT

    @PutMapping("editar/tudo/{id}")
    public ResponseEntity<?> editarTudo(@PathVariable Long id, @RequestBody Campeao campeao) {
        Campeao campeaoRetorno = new Campeao();
        try{

            if (!campeaoRepository.existsById(id)) {
                return new ResponseEntity<>(new ApiError(HttpStatus.NO_CONTENT.value(), "No champion found"), HttpStatus.NO_CONTENT);
            }
            campeao.setId(id);
            campeaoRetorno = campeaoRepository.save(campeao);
            return new ResponseEntity<>(campeaoRetorno, HttpStatus.OK);
        }catch(RuntimeException e){
            e.printStackTrace();
            return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred on the server, try again later"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{idCampeao}/images/{idImagem}")
    public ResponseEntity<?> adicionarImagem(@PathVariable Long idCampeao,@PathVariable Long idImagem) {
        Campeao campeaoRetorno = new Campeao();
        try{

            Optional<Imagem> imagemOptional = imagemRepository.findById(idImagem);
            if(!imagemOptional.isPresent()) {
                return new ResponseEntity<>(new ApiError(HttpStatus.NO_CONTENT.value(), "No image found"), HttpStatus.NO_CONTENT);
            }
            Imagem imagem = imagemOptional.get();

            Optional<Campeao> campeaoOptional = campeaoRepository.findById(idCampeao);
            if(!campeaoOptional.isPresent()){
                return new ResponseEntity<>(new ApiError(HttpStatus.NO_CONTENT.value(), "No champion found"), HttpStatus.NO_CONTENT);
            }
            Campeao campeao = campeaoOptional.get();

            List<Imagem> imagems = campeao.getImagem();
            imagems.add(imagem);
            campeao.setImagem(imagems);
            campeaoRetorno = campeaoRepository.save(campeao);
            return new ResponseEntity<>(campeaoRetorno, HttpStatus.OK);
        }catch(RuntimeException e){
            e.printStackTrace();
            return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred on the server, try again later"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("editar/parcial/{id}")
    public ResponseEntity<?> editarParcial(@PathVariable Long id, @RequestBody Campeao campeao) {
        Campeao campeaoRetorno = new Campeao();
        try{
            Optional<Campeao> campeaoOptional = campeaoRepository.findById(id);
            if(!campeaoOptional.isPresent()){
                return new ResponseEntity<>(new ApiError(HttpStatus.NO_CONTENT.value(), "No champion found"), HttpStatus.NO_CONTENT);
            }
            Campeao campeaoEditar = new Campeao();
            campeaoEditar = campeaoOptional.get();

            campeaoEditar.updateFrom(campeao);
            
            campeaoRetorno = campeaoRepository.save(campeaoEditar);
            return new ResponseEntity<>(campeaoRetorno, HttpStatus.OK);
        }catch(RuntimeException e){
            e.printStackTrace();
            return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred on the server, try again later"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Metodos DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        try {
            Optional<Campeao> campeaoOptional = campeaoRepository.findById(id);
            if(!campeaoOptional.isPresent()) {
                return new ResponseEntity<>(new ApiError(HttpStatus.NO_CONTENT.value(), "No champion found"), HttpStatus.NO_CONTENT);
            }

            Campeao campeao = campeaoOptional.get();
            campeaoRepository.delete(campeao);
            return new ResponseEntity<>("Champion deleted successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred on the server, try again later"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
