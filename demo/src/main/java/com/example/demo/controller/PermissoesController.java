package com.example.demo.controller;



import com.example.demo.model.DTO.PermissoesDTO;
import com.example.demo.model.Permissoes;
import com.example.demo.service.PermissoesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path = "permissao")
public class PermissoesController {
    @Autowired
    private PermissoesService permissoesService;

    @GetMapping
    public ResponseEntity seachPermissoes(){
       return permissoesService.findAll();
    }

    @PostMapping
    public ResponseEntity addPermissoes(@RequestBody PermissoesDTO p){
       return permissoesService.addPermissoes(p);
    }

    @PutMapping
    public String updatePermissoes(@RequestBody Permissoes p){
      return permissoesService.updatePermissoes(p);
    }

    @DeleteMapping
    public String deletePermissoes(@RequestParam String id){
       return permissoesService.deletePermissoes(id);
    }
    
}