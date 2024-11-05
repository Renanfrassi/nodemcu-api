package com.example.demo.service;

import com.example.demo.model.DTO.FechaduraDTO;
import com.example.demo.model.Fechadura;
import com.example.demo.repository.FechaduraRepository;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

@Service
public class FechaduraService {
    @Autowired
    private FechaduraRepository fechadura;

    public Iterable<Fechadura> findAll(){
            return fechadura.findAll();
    }

<<<<<<< HEAD
    public ResponseEntity addFechadura(Fechadura f){
        try {
            return ResponseEntity.created(URI.create("./fechadura")).body(fechadura.save(f));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity deleteFechadura(Integer id){
        try {
            fechadura.deleteById(id);
            return ResponseEntity.ok().body(fechadura.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity updateFechadura(Fechadura f){

        try{

            if(fechadura.findById(f.getId()).stream().count() == 0){

                return ResponseEntity.badRequest().body("Fechadura não Registrada");
=======
    public FechaduraDTO addFechadura(FechaduraDTO f) throws Exception {
>>>>>>> f84a61f0070484aa383bd3b3dca7c5659f386312

            if(f.getNome() == null){
                throw new Exception("O nome da Fechadura deverá ser preenchido!");
            }
            if(f.getDescricao() == null){
                throw new Exception("A descrição da Fechadura deverá ser preenchida!");
            }

            fechadura.save(new Fechadura(f.getNome(), f.getDescricao()));

            return f;

    }

    public Iterable<Fechadura> deleteFechadura(Integer id) throws Exception, ConstraintViolationException {

            fechadura.deleteById(id);
            return fechadura.findAll();

    }

    public FechaduraDTO updateFechadura(FechaduraDTO fDTO) throws Exception {


            if(fDTO.getNome() == null){
                throw new Exception("O nome da Fechadura deverá ser preenchido!");
            }
            if(fDTO.getDescricao() == null){
                throw new Exception("A descrição da Fechadura deverá ser preenchida!");
            }

            Optional<Fechadura> f = fechadura.findById(fDTO.getId());

            if(f.isEmpty()){
                throw new Exception("Fechadura não Registrada");
            }

            // Atualize os campos da fechadura
            Fechadura fechaduraExistente = f.get();
            fechaduraExistente.setNome(fDTO.getNome());
            fechaduraExistente.setDescricao(fDTO.getDescricao());

            fechadura.save(fechaduraExistente);

            return fDTO;


    }


    public Iterable<Fechadura> getAllFechadura()  throws Exception {
        return fechadura.findAll();
     }

<<<<<<< HEAD
     public Iterable<Fechadura> getAllFechadura(){
        return fechadura.findAll();
     }

    public Optional<Fechadura> findById(Integer id){
=======
    public Optional<Fechadura> findById(Integer id)  throws Exception {
>>>>>>> f84a61f0070484aa383bd3b3dca7c5659f386312
        return fechadura.findById(id);
    }

}
