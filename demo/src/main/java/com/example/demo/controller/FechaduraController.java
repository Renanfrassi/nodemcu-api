package com.example.demo.controller;

import com.example.demo.model.Fechadura;
import com.example.demo.service.FechaduraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path = "fechadura")
public class FechaduraController {
    @Autowired
    private FechaduraService fechadura;

    @GetMapping
    public ResponseEntity seachCartao(){
       return fechadura.findAll();
    }

    @PostMapping
    public ResponseEntity addFechadura(@RequestBody Fechadura f){
      return fechadura.addFechadura(f);

    }
    
    @PutMapping
    public ResponseEntity updateFechadura(@RequestBody Fechadura f){
      return fechadura.updateFechadura(f);
    }

    @DeleteMapping
    public String deleteFechadura(@RequestParam Integer id){
       return fechadura.deleteFechadura(id);
    }
}
