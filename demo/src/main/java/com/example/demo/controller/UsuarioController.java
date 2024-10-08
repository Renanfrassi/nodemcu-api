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

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path = "usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity seachUsuario(){
       return usuarioService.findAll();
    }

    @GetMapping("find")
    public ResponseEntity findUsuarioById(@RequestParam Integer id){
        return usuarioService.findUsuarioById(id);
    }

    @PostMapping
    public ResponseEntity addUsuario(@RequestBody Usuario u){
       return usuarioService.addUsuario(u);
    }

    @PostMapping("slot")
    public ResponseEntity addSlot(@RequestBody UsuarioSlotDTO usuarioSlotDTO){
        return usuarioService.addSlot(usuarioSlotDTO);
    }

    @PutMapping("slot")
    public ResponseEntity updateSlot(@RequestBody UsuarioSlotDTO usuarioSlotDTO){
        return usuarioService.updateSlot(usuarioSlotDTO);
    }

    @PutMapping
    public ResponseEntity updateUsuario(@RequestBody Usuario u){
      return usuarioService.updateUsuario(u);
    }

    @DeleteMapping
    public ResponseEntity deleteUsuario(@RequestParam Integer id){
       return usuarioService.deleteUsuarioPosse(id);
    }

    @GetMapping("slot")
    public ResponseEntity findUsuarioSlot(@RequestParam Integer idUsuario, @RequestParam String idCartao, @RequestParam Integer idFechadura){
        FilterDTO filter = new FilterDTO(idCartao, idFechadura, idUsuario);

        return usuarioService.findUsuarioSlot(filter);
    }


}