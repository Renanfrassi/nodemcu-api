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

    public String deleteFechadura(Integer id){
        fechadura.deleteById(id);

        return "Deletado com sucesso";
    }

    public ResponseEntity updateFechadura(Fechadura f){

        try{

            if(fechadura.findById(f.getId()).stream().count() == 0){

                return ResponseEntity.badRequest().body("Fechadura não registrada");

            }

            return ResponseEntity.ok().body(fechadura.save(f));

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }
     }

    public Optional<Fechadura> findById(Integer id){
        return fechadura.findById(id);
    }

}
