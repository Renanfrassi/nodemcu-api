package com.example.demo.controller;

import com.example.demo.model.DTO.FilterDTO;
import com.example.demo.model.DTO.UsuarioSlotDTO;
import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;

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

import java.net.URI;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path = "usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity seachUsuario(){
        try{
            return ResponseEntity.ok().body(usuarioService.findAll());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @GetMapping("find")
    public ResponseEntity findUsuarioById(@RequestParam Integer id){
        return usuarioService.findUsuarioById(id);
    }

    @PostMapping
    public ResponseEntity addUsuario(@RequestBody Usuario u){
       try{
           return ResponseEntity.created(URI.create("./usuario")).body(usuarioService.addUsuario(u));

       } catch (Exception e) {
           return ResponseEntity.badRequest().body(e.getMessage());

       }
    }

    @PostMapping("slot")
    public ResponseEntity addSlot(@RequestBody UsuarioSlotDTO usuarioSlotDTO){
        try {
            return ResponseEntity.created(URI.create("./usuario/slot")).body(usuarioService.addSlot(usuarioSlotDTO));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @PutMapping("slot")
    public ResponseEntity updateSlot(@RequestBody UsuarioSlotDTO usuarioSlotDTO){
        try {
            return ResponseEntity.ok().body(usuarioService.updateSlot(usuarioSlotDTO));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @PutMapping
    public ResponseEntity updateUsuario(@RequestBody Usuario u){
        try {
            return ResponseEntity.ok().body(usuarioService.updateUsuario(u));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @DeleteMapping
    public ResponseEntity deleteUsuario(@RequestParam Integer id){
        try {
            return ResponseEntity.ok().body(usuarioService.deleteUsuarioPosse(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @GetMapping("slot")
    public ResponseEntity findUsuarioSlot(@RequestParam Integer idUsuario, @RequestParam String idCartao, @RequestParam Integer idFechadura){
        FilterDTO filter = new FilterDTO(idCartao, idFechadura, idUsuario);
        try {
            return ResponseEntity.ok().body(usuarioService.findUsuarioSlot(filter));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }


}