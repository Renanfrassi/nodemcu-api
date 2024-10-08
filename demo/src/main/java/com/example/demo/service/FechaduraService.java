package com.example.demo.service;

import com.example.demo.model.Cartao;
import com.example.demo.model.Fechadura;
import com.example.demo.repository.FechaduraRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

@Service
public class FechaduraService {
    @Autowired
    private FechaduraRepository fechadura;

    public ResponseEntity findAll(){
        try{
            return ResponseEntity.ok().body(fechadura.findAll());

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

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

            }

            return ResponseEntity.ok().body(fechadura.save(f));

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }
     }

     public Iterable<Fechadura> getAllFechadura(){
        return fechadura.findAll();
     }

    public Optional<Fechadura> findById(Integer id){
        return fechadura.findById(id);
    }

}
