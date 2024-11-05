package com.example.demo.controller;

import com.example.demo.model.DTO.FechaduraDTO;
import com.example.demo.model.Fechadura;
import com.example.demo.service.FechaduraService;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path = "fechadura")
public class FechaduraController {
    @Autowired
    private FechaduraService fechadura;

    @GetMapping
    public ResponseEntity findAll(){
        try {
            return ResponseEntity.ok().body(fechadura.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity addFechadura(@RequestBody FechaduraDTO f){
        try {
            return ResponseEntity.created(URI.create("./fechadura'")).body(fechadura.addFechadura(f));

        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
    
    @PutMapping
    public ResponseEntity updateFechadura(@RequestBody FechaduraDTO f){
        try {
            return ResponseEntity.ok().body(fechadura.updateFechadura(f));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @DeleteMapping
    public ResponseEntity deleteFechadura(@RequestParam Integer idFechadura){
<<<<<<< HEAD
       return fechadura.deleteFechadura(idFechadura);
=======
        try {
            return ResponseEntity.ok().body(fechadura.deleteFechadura(idFechadura));
        }catch (ConstraintViolationException e){
            return ResponseEntity.badRequest().body("Não é possivel excluir a Sala! Ela ja está associada em alguma Permissão!");

        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
>>>>>>> f84a61f0070484aa383bd3b3dca7c5659f386312
    }

}
